package uz.abu.salary_payment.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.abu.salary_payment.payload.TotalPayment;
import uz.abu.salary_payment.payload.WorkRecordRequest;
import uz.abu.salary_payment.payload.WorkRecordResponse;
import uz.abu.salary_payment.service.WorkRecordService;

import java.util.List;

@RestController
@RequestMapping("/work-record")
@RequiredArgsConstructor
@Tag(name = "Work Record")
public class WorkRecordController {
    private  final WorkRecordService workRecordService;

    @PostMapping("/add")
    @Operation(description = "Add work record")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    public ResponseEntity<WorkRecordResponse> addWorkRecord(@RequestBody WorkRecordRequest workRecordRequest) {
        return ResponseEntity.ok(workRecordService.createWorkRecord(workRecordRequest));
    }

    @GetMapping("/{workRecordId}")
    @Operation(description = "Get work record by Id")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    public ResponseEntity<WorkRecordResponse> getWorkRecordById(@PathVariable Long workRecordId) {
        return ResponseEntity.ok(workRecordService.getWorkRecordById(workRecordId));
    }
    @GetMapping("/all")
    @Operation(description = "Get all work records")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    public ResponseEntity<List<WorkRecordResponse>> getAllWorkRecords(@RequestParam Integer per_page, @RequestParam Integer page) {
        return ResponseEntity.ok(workRecordService.getAllWorkRecords(per_page, page));
    }

    @PatchMapping("/delete/{workRecordId}")
    @Operation(description = "Delete work record")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    public ResponseEntity<WorkRecordResponse> deleteWorkRecord(@PathVariable Long workRecordId) {
        return ResponseEntity.ok(workRecordService.deleteWorkRecord(workRecordId));
    }

    @GetMapping("/total-payment")
    @Operation(description = "Get total payment")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    public ResponseEntity<TotalPayment> getTotalPayment(@RequestParam String startDate) {
        return ResponseEntity.ok(workRecordService.getTotalPayment(java.time.LocalDate.parse(startDate)));
    }

    @GetMapping("/my-records/{workerId}")
    @Operation(description = "Get my work records")
    public  ResponseEntity<List<WorkRecordResponse>> getMyWorkRecords(@PathVariable Long workerId) {
        return ResponseEntity.ok(workRecordService.getMyWorkRecords(workerId));
    }
    @GetMapping("/my-recods-by-date/{workerId}")
    @Operation(description = "Get my work records by date")
    public  ResponseEntity<List<WorkRecordResponse>> getMyWorkRecordsByDate(@PathVariable Long workerId, @RequestParam String startDate) {
        return ResponseEntity.ok(workRecordService.getMyWorkRecordsByDate(workerId, java.time.LocalDate.parse(startDate)));
     }
}
