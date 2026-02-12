package uz.abu.salary_payment.service;

import org.jspecify.annotations.Nullable;
import uz.abu.salary_payment.entity.WorkRecord;
import uz.abu.salary_payment.payload.TotalPayment;
import uz.abu.salary_payment.payload.WorkRecordRequest;
import uz.abu.salary_payment.payload.WorkRecordResponse;

import java.time.LocalDate;
import java.util.List;

public interface WorkRecordService {
    WorkRecordResponse createWorkRecord(WorkRecordRequest workRecordRequest);
    WorkRecordResponse getWorkRecordById(Long id);
    List<WorkRecordResponse> getAllWorkRecords(Integer per_page, Integer page);
    WorkRecordResponse deleteWorkRecord(Long id);
    WorkRecord getWorkRecordEntityById(Long id);
    TotalPayment getTotalPayment(LocalDate startDate);

    List<WorkRecordResponse> getMyWorkRecords(Long workerId);

}
