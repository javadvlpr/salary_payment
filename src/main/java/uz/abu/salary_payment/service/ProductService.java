package uz.abu.salary_payment.service;

import uz.abu.salary_payment.payload.ProductResponse;

import java.util.List;

public interface ProductService {
    ProductResponse addProduct(String name);
    ProductResponse updateProduct(Long id, String name);
    ProductResponse getProductById(Long id);
    List<ProductResponse> getProducts(Integer per_page, Integer page);
    ProductResponse deleteProduct(Long id);
}
