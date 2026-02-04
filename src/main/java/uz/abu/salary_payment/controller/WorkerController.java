package uz.abu.salary_payment.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.abu.salary_payment.payload.workerDtos.WorkerCreateResponse;
import uz.abu.salary_payment.payload.workerDtos.WorkerResponse;
import uz.abu.salary_payment.service.WorkerService;

import java.util.List;

@RestController
@RequestMapping("/worker")
@RequiredArgsConstructor
@Tag(name = "Worker")
public class WorkerController {
    private final WorkerService workerService;

    @Operation(description = "Add worker")
    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    public ResponseEntity<WorkerCreateResponse> addWorker(@RequestParam String fullName) {
        return ResponseEntity.ok(workerService.addWorker(fullName));
    }

    @Operation(description = "Get worker by Id")
    @GetMapping("/{workerId}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    public ResponseEntity<WorkerResponse> getWorkerById(@PathVariable Long workerId) {
        return ResponseEntity.ok(workerService.getWorkerById(workerId));
    }

    @Operation(description = "Get workers")
    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    public ResponseEntity<List<WorkerResponse>> getWorkers(@RequestParam Integer per_page, @RequestParam Integer page) {
        return ResponseEntity.ok(workerService.getWorkers(per_page, page));
    }
    @Operation(description = "Get workers for admin")
    @GetMapping("/all-info")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<WorkerCreateResponse>> getWorkersForAdmin(@RequestParam Integer per_page, @RequestParam Integer page) {
        return ResponseEntity.ok(workerService.getWorkersAllInfo(per_page, page));
    }

    @Operation(description = "Delete worker")
    @PatchMapping("/delete")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<WorkerResponse> deleteWorker(@RequestParam Long workerId) {
        return ResponseEntity.ok(workerService.deleteWorker(workerId));
    }
}
