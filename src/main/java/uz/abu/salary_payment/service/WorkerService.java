package uz.abu.salary_payment.service;

import uz.abu.salary_payment.payload.workerDtos.WorkerResponse;

import java.util.List;

public interface WorkerService {
    WorkerResponse addWorker(String fullName);
    WorkerResponse getWorkerById(Long id);
    List<WorkerResponse> getWorkers();
    WorkerResponse deleteWorker(Long id);
}
