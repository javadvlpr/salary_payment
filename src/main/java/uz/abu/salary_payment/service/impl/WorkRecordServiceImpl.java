package uz.abu.salary_payment.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.abu.salary_payment.entity.OperationRate;
import uz.abu.salary_payment.entity.WorkRecord;
import uz.abu.salary_payment.entity.Worker;
import uz.abu.salary_payment.entity.enums.OperationType;
import uz.abu.salary_payment.payload.TotalPayment;
import uz.abu.salary_payment.payload.WorkRecordRequest;
import uz.abu.salary_payment.payload.WorkRecordResponse;
import uz.abu.salary_payment.repository.WorkRecordRepository;
import uz.abu.salary_payment.service.OperationRateService;
import uz.abu.salary_payment.service.WorkRecordService;
import uz.abu.salary_payment.service.WorkerService;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkRecordServiceImpl implements WorkRecordService {
    private final WorkRecordRepository workRecordRepository;
    private final WorkerService workerService;
    private final OperationRateService operationRateService;

    @Override
    public WorkRecordResponse createWorkRecord(WorkRecordRequest workRecordRequest) {
        Worker workerEntityById = workerService.getWorkerEntityById(workRecordRequest.getWorkerId());
        OperationRate operationRateEntityById = operationRateService.getOperationRateEntityById(workRecordRequest.getOperationRateId());
        WorkRecord save = workRecordRepository.save(WorkRecord.builder()
                .worker(workerEntityById)
                .operationRate(operationRateEntityById)
                .quantity(workRecordRequest.getQuantity())
                .isActive(true)
                .build());
        return WorkRecordResponse.from(save);
    }

    @Override
    public WorkRecordResponse getWorkRecordById(Long id) {
        WorkRecord workRecord = workRecordRepository.finByIdAndIsActiveTrue(id).orElseThrow(() -> new RuntimeException("Work record not found"));
        return WorkRecordResponse.from(workRecord);
    }

    @Override
    public List<WorkRecordResponse> getAllWorkRecords(Integer per_page, Integer page) {
        int offset = page * per_page;
        List<WorkRecord> all = workRecordRepository.findAll(per_page, offset);
        return all.stream().map(WorkRecordResponse::from).toList();
    }

    @Override
    public WorkRecordResponse deleteWorkRecord(Long id) {
        WorkRecord workRecord = workRecordRepository.finByIdAndIsActiveTrue(id).orElseThrow(() -> new RuntimeException("Work record not found"));
        workRecord.setIsActive(false);
        WorkRecord save = workRecordRepository.save(workRecord);
        return WorkRecordResponse.from(save);
    }

    @Override
    public WorkRecord getWorkRecordEntityById(Long id) {
        return workRecordRepository.finByIdAndIsActiveTrue(id).orElseThrow(() -> new RuntimeException("Work record not found"));
    }

    @Override
    public TotalPayment getTotalPayment(LocalDate startDate) {
        List<WorkRecord> birChok = workRecordRepository.findAllByCreatedAtAfterAndIsActiveTrue(OperationType.BIR_CHOK.name(), startDate);
        List<WorkRecord> averlo = workRecordRepository.findAllByCreatedAtAfterAndIsActiveTrue(OperationType.AVERLO.name(), startDate);
        List<WorkRecord> bichuv = workRecordRepository.findAllByCreatedAtAfterAndIsActiveTrue(OperationType.BICHUV.name(), startDate);
        List<WorkRecord> vishivka = workRecordRepository.findAllByCreatedAtAfterAndIsActiveTrue(OperationType.VISHIVKA.name(), startDate);
        List<WorkRecord> all = workRecordRepository.findAllByCreatedAtAfterAndIsActiveTrue(startDate);

        Integer birchokAmount = birChok.stream().map(workRecord -> workRecord.getQuantity() * workRecord.getOperationRate().getPricePerUnit()).reduce(0, Integer::sum);
        Integer averloAmount = averlo.stream().map(workRecord -> workRecord.getQuantity() * workRecord.getOperationRate().getPricePerUnit()).reduce(0, Integer::sum);
        Integer bichuvAmount = bichuv.stream().map(workRecord -> workRecord.getQuantity() * workRecord.getOperationRate().getPricePerUnit()).reduce(0, Integer::sum);
        Integer vishivkaAmount = vishivka.stream().map(workRecord -> workRecord.getQuantity() * workRecord.getOperationRate().getPricePerUnit()).reduce(0, Integer::sum);
        Integer totalAmount = all.stream().map(workRecord -> workRecord.getQuantity() * workRecord.getOperationRate().getPricePerUnit()).reduce(0, Integer::sum);
        return TotalPayment.builder()
                .totalAmount(totalAmount)
                .birchokAmount(birchokAmount)
                .averloAmount(averloAmount)
                .bichuvAmount(bichuvAmount)
                .vishivkaAmount(vishivkaAmount)
                .build();
    }

    @Override
    public List<WorkRecordResponse> getMyWorkRecords(Long workerId) {
        List<WorkRecord> allByWorkerIdAndIsActiveTrue = workRecordRepository.findAllByWorkerIdAndIsActiveTrue(workerId);
        return allByWorkerIdAndIsActiveTrue.stream().map(WorkRecordResponse::from).toList();
    }
}
