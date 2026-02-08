package uz.abu.salary_payment.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.abu.salary_payment.common.DataNotFoundException;
import uz.abu.salary_payment.entity.OperationRate;
import uz.abu.salary_payment.entity.enums.OperationType;
import uz.abu.salary_payment.payload.OperationRateRequest;
import uz.abu.salary_payment.payload.OperationRateResponse;
import uz.abu.salary_payment.repository.OperationRateRepository;
import uz.abu.salary_payment.service.OperationRateService;
import uz.abu.salary_payment.service.ProductService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OperationRateServiceImpl implements OperationRateService {
    private final OperationRateRepository operationRateRepository;
    private final ProductService productService;

    @Override
    public OperationRateResponse addOperationRate(OperationRateRequest operationRateRequest) {
        OperationRate save = operationRateRepository.save(OperationRate.builder()
                .operationType(OperationType.valueOf(operationRateRequest.getOperationType()))
                .product(productService.getProductEntityById(operationRateRequest.getProductId()))
                .pricePerUnit(operationRateRequest.getPricePerUnit())
                .isActive(true)
                .build());
        return OperationRateResponse.from(save);
    }

    @Override
    public OperationRateResponse updateOperationRate(Long id, OperationRateRequest operationRateRequest) {
        OperationRate operationRate = operationRateRepository.findByIdAndIsActiveTrue(id).orElseThrow(() -> new DataNotFoundException("Operation Rate not found"));
        operationRate.setOperationType(OperationType.valueOf(operationRateRequest.getOperationType()));
        operationRate.setProduct(productService.getProductEntityById(operationRateRequest.getProductId()));
        operationRate.setPricePerUnit(operationRateRequest.getPricePerUnit());
        OperationRate save = operationRateRepository.save(operationRate);
        return OperationRateResponse.from(save);
    }

    @Override
    public OperationRateResponse getOperationRateById(Long id) {
        OperationRate operationRate = operationRateRepository.findByIdAndIsActiveTrue(id).orElseThrow(() -> new DataNotFoundException("Operation Rate not found"));
        return OperationRateResponse.from(operationRate);
    }

    @Override
    public List<OperationRateResponse> getAllOperationRate(Integer per_page, Integer page) {
        int offset = (page - 1) * per_page;
        List<OperationRate> operationRates = operationRateRepository.findAll(per_page, offset);
        return operationRates.stream().map(OperationRateResponse::from).toList();
    }

    @Override
    public OperationRateResponse deleteOperationRate(Long id) {
        OperationRate operationRate = operationRateRepository.findByIdAndIsActiveTrue(id).orElseThrow(() -> new DataNotFoundException("Operation Rate not found"));
        operationRate.setIsActive(false);
        OperationRate save = operationRateRepository.save(operationRate);
        return OperationRateResponse.from(save);
    }

    @Override
    public OperationRate getOperationRateEntityById(Long id) {
        return operationRateRepository.findByIdAndIsActiveTrue(id).orElseThrow(() -> new DataNotFoundException("Operation Rate not found"));
    }
}
