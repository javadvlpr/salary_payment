package uz.abu.salary_payment.service;

import uz.abu.salary_payment.payload.workerDtos.WorkerCreateResponse;
import uz.abu.salary_payment.payload.workerDtos.WorkerResponse;

import java.util.List;

public interface WorkerService {
    WorkerCreateResponse addWorker(String fullName);
    WorkerResponse getWorkerById(Long id);
    List<WorkerResponse> getWorkers(Integer per_page, Integer page);
    List<WorkerCreateResponse> getWorkersAllInfo(Integer per_page, Integer page);
    WorkerResponse deleteWorker(Long id);
}
