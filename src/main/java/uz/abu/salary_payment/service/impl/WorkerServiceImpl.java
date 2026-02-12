package uz.abu.salary_payment.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.abu.salary_payment.common.DataNotFoundException;
import uz.abu.salary_payment.entity.User;
import uz.abu.salary_payment.entity.Worker;
import uz.abu.salary_payment.entity.enums.UserRole;
import uz.abu.salary_payment.payload.WorkerCreateResponse;
import uz.abu.salary_payment.payload.WorkerResponse;
import uz.abu.salary_payment.repository.WorkerRepository;
import uz.abu.salary_payment.service.UserService;
import uz.abu.salary_payment.service.WorkerService;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class WorkerServiceImpl implements WorkerService {
    private final WorkerRepository workerRepository;
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    @Override
    public WorkerCreateResponse addWorker(String fullName) {
        Worker worker = workerRepository.save(Worker.builder()
                .fullName(fullName)
                .isActive(true)
                .build());
        Map<String, String> stringStringMap = userService.generateUsernameAndPassword(fullName);
        String password = stringStringMap.get("password");
        User user = User.builder()
                .username(stringStringMap.get("username"))
                .password(password)
                .role(UserRole.WORKER)
                .worker(worker)
                .build();
        User save = userService.save(user);
        return WorkerCreateResponse.builder()
                .fullName(fullName)
                .username(save.getUsername())
                .password(password)
                .isActive(worker.getIsActive())
                .createdAt(worker.getCreatedAt())
                .build();
    }

    @Override
    public WorkerResponse getWorkerById(Long id) {
        Worker worker = workerRepository.findByIdAndIsActiveTrue(id).orElseThrow(() -> new DataNotFoundException("Worker not found"));
        return WorkerResponse.from(worker);
    }

    @Override
    public List<WorkerResponse> getWorkers(Integer per_page, Integer page) {
        int offset = page * per_page;
        List<Worker> workers = workerRepository.findAll(per_page, offset);
        return workers.stream().map(WorkerResponse::from).toList();
    }

    @Override
    public List<WorkerCreateResponse> getWorkersAllInfo(Integer per_page, Integer page) {
        int offset = page * per_page;
        List<User> allUsersEntity = userService.getAllUsersEntity(per_page, offset);
        return allUsersEntity.stream()
                .map(user -> WorkerCreateResponse.builder()
                        .id(user.getWorker().getId())
                        .fullName(user.getWorker().getFullName())
                        .username(user.getUsername())
                        .password(user.getPassword())
                        .isActive(user.getWorker().getIsActive())
                        .createdAt(user.getWorker().getCreatedAt())
                        .build())
                .toList();
    }

    @Override
    public WorkerResponse deleteWorker(Long id) {
        Worker worker = workerRepository.findById(id).orElseThrow(() -> new DataNotFoundException("Worker not found"));
        worker.setIsActive(false);
        User userByWorkerId = userService.getUserByWorkerId(worker.getId());
        userService.deleteUser(userByWorkerId.getId());
        return WorkerResponse.from(workerRepository.save(worker));
    }

    @Override
    public Worker getWorkerEntityById(Long id) {
        return workerRepository.findByIdAndIsActiveTrue(id).orElseThrow(() -> new DataNotFoundException("Worker not found"));
    }
}
