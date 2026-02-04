package uz.abu.salary_payment.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.abu.salary_payment.common.DataNotFoundException;
import uz.abu.salary_payment.entity.Product;
import uz.abu.salary_payment.payload.ProductResponse;
import uz.abu.salary_payment.repository.ProductRepository;
import uz.abu.salary_payment.service.ProductService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    @Override
    public ProductResponse addProduct(String name) {
        Product save = productRepository.save(Product.builder()
                .name(name)
                .isActive(true)
                .build());
        return ProductResponse.from(save);
    }

    @Override
    public ProductResponse updateProduct(Long id, String name) {
        Product product = productRepository.findByIdAndIsActiveTrue(id).orElseThrow(() -> new DataNotFoundException("Product not found"));
        product.setName(name);
        Product save = productRepository.save(product);
        return ProductResponse.from(save);
    }

    @Override
    public ProductResponse getProductById(Long id) {
        Product product = productRepository.findByIdAndIsActiveTrue(id).orElseThrow(() -> new DataNotFoundException("Product not found"));
        return ProductResponse.from(product);
    }

    @Override
    public List<ProductResponse> getProducts(Integer per_page, Integer page) {
        int offset = (page - 1) * per_page;
        List<Product> products = productRepository.findAll(per_page, offset);
        return products.stream().map(ProductResponse::from).toList();
    }

    @Override
    public ProductResponse deleteProduct(Long id) {
        Product productNotFound = productRepository.findByIdAndIsActiveTrue(id).orElseThrow(() -> new DataNotFoundException("Product not found"));
        productNotFound.setIsActive(false);
        Product save = productRepository.save(productNotFound);
        return ProductResponse.from(save);
    }
}
