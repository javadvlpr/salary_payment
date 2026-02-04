package uz.abu.salary_payment.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.abu.salary_payment.payload.ProductResponse;
import uz.abu.salary_payment.service.ProductService;

@RestController
@RequestMapping("product/")
@RequiredArgsConstructor
@Tag(name = "Product")
public class ProductController {
    private final ProductService productService;

    @Operation(description = "Add product")
    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    public ResponseEntity<ProductResponse> addProduct(@RequestParam String name) {
        return ResponseEntity.ok(productService.addProduct(name));
    }

    @Operation(description = "Get product by Id")
    @GetMapping("/{productId}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable Long productId) {
        return ResponseEntity.ok(productService.getProductById(productId));
    }

    @Operation(description = "Update product")
    @PatchMapping("/update/{productId}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    public ResponseEntity<ProductResponse> updateProduct(@PathVariable Long productId, @RequestParam String name) {
        return ResponseEntity.ok(productService.updateProduct(productId, name));
    }

    @Operation(description = "Get all products")
    @GetMapping("/all")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    public ResponseEntity<?> getAllProducts(@RequestParam Integer per_page, @RequestParam Integer page) {
        return ResponseEntity.ok(productService.getProducts(per_page, page));
    }

    @Operation(description = "Delete product")
    @PatchMapping("/delete/{productId}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER')")
    public ResponseEntity<ProductResponse> deleteProduct(@PathVariable Long productId) {
        return ResponseEntity.ok(productService.deleteProduct(productId));
    }
}
