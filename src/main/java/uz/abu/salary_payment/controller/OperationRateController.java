package uz.abu.salary_payment.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.abu.salary_payment.payload.OperationRateRequest;
import uz.abu.salary_payment.payload.OperationRateResponse;
import uz.abu.salary_payment.service.OperationRateService;

import java.util.List;

@RestController
@RequestMapping("/operation-rate")
@RequiredArgsConstructor
@Tag(name = "Operation Rate")
public class OperationRateController {
    private  final OperationRateService operationRateService;

    @Operation(description = "Add Operation Rate")
    @PostMapping("/add")
    public ResponseEntity<OperationRateResponse> addOperationRate(@RequestBody OperationRateRequest operationRateRequest) {
        return ResponseEntity.ok(operationRateService.addOperationRate(operationRateRequest));
    }

    @Operation(description = "Get Operation Rate by Id")
    @GetMapping("/{operationRateId}")
    public ResponseEntity<OperationRateResponse> getOperationRateById(@PathVariable Long operationRateId) {
        return ResponseEntity.ok(operationRateService.getOperationRateById(operationRateId));
    }

    @Operation(description = "Get All Operation Rates")
    @GetMapping("/all")
    public ResponseEntity<List<OperationRateResponse>> getAllOperationRates(@RequestParam Integer per_page, @RequestParam Integer page) {
        return ResponseEntity.ok(operationRateService.getAllOperationRate(per_page, page));
    }

    @Operation(description = "Update Operation Rate")
    @PatchMapping("/update/{operationRateId}")
    public ResponseEntity<OperationRateResponse> updateOperationRate(@PathVariable Long operationRateId, @RequestBody OperationRateRequest operationRateRequest) {
        return ResponseEntity.ok(operationRateService.updateOperationRate(operationRateId, operationRateRequest));
    }

    @Operation(description = "Delete Operation Rate")
    @PatchMapping("/delete/{operationRateId}")
    public ResponseEntity<OperationRateResponse> deleteOperationRate(@PathVariable Long operationRateId) {
        return ResponseEntity.ok(operationRateService.deleteOperationRate(operationRateId));
    }
}
