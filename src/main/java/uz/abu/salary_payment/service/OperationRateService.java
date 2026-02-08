package uz.abu.salary_payment.service;

import uz.abu.salary_payment.entity.OperationRate;
import uz.abu.salary_payment.payload.OperationRateRequest;
import uz.abu.salary_payment.payload.OperationRateResponse;

import java.util.List;

public interface OperationRateService {
    OperationRateResponse addOperationRate(OperationRateRequest operationRateRequest);
    OperationRateResponse updateOperationRate(Long id,OperationRateRequest operationRateRequest);
    OperationRateResponse getOperationRateById(Long id);
    List<OperationRateResponse> getAllOperationRate(Integer per_page, Integer page);
    OperationRateResponse deleteOperationRate(Long id);
    OperationRate getOperationRateEntityById(Long id);
}
