package uz.abu.salary_payment.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
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
    @PutMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<WorkerResponse> addWorker(@RequestParam String fullName) {
        return ResponseEntity.ok(workerService.addWorker(fullName));
    }

    @Operation(description = "Get worker by Id")
    @PutMapping("/{workerId}")
    public ResponseEntity<WorkerResponse> getWorkerById(@PathVariable Long workerId) {
        return ResponseEntity.ok(workerService.getWorkerById(workerId));
    }

    @Operation(description = "Get workers")
    @PutMapping("/all")
    public ResponseEntity<List<WorkerResponse>> getWorkers() {
        return ResponseEntity.ok(workerService.getWorkers());
    }

    @Operation(description = "Delete worker")
    @PutMapping("/delete")
    public ResponseEntity<WorkerResponse> deleteWorker(@RequestParam Long workerId) {
        return ResponseEntity.ok(workerService.deleteWorker(workerId));
    }
}
