package uz.abu.salary_payment.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.abu.salary_payment.entity.User;
import uz.abu.salary_payment.entity.Worker;
import uz.abu.salary_payment.entity.enums.UserRole;
import uz.abu.salary_payment.payload.workerDtos.WorkerResponse;
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
    @Override
    public WorkerResponse addWorker(String fullName) {
        Worker worker = workerRepository.save(Worker.builder()
                .fullName(fullName)
                .isActive(true)
                .build());
        Map<String, String> stringStringMap = userService.generateUsernameAndPassword(fullName);
        User user = User.builder()
                .username(stringStringMap.get("username"))
                .password(stringStringMap.get("password"))
                .role(UserRole.WORKER)
                .worker(worker)
                .build();
        User save = userService.save(user);
        return WorkerResponse.from(worker);
    }

    @Override
    public WorkerResponse getWorkerById(Long id) {
        Worker worker = workerRepository.findById(id).orElseThrow(() -> new RuntimeException("Worker not found"));
        return WorkerResponse.from(worker);
    }

    @Override
    public List<WorkerResponse> getWorkers() {
        List<Worker> workers = workerRepository.findAllByActiveTrue();
        return workers.stream().map(WorkerResponse::from).toList();
    }

    @Override
    public WorkerResponse deleteWorker(Long id) {
        Worker worker = workerRepository.findById(id).orElseThrow(() -> new RuntimeException("Worker not found"));
        worker.setActive(false);
        return WorkerResponse.from(workerRepository.save(worker));
    }
}
