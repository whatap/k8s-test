package io.whatap.oom.controller;

import io.whatap.oom.repo.Product;
import io.whatap.oom.service.ProductService;
import io.whatap.oom.dto.ProductStat;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Product>> getProduct(@PathVariable Long id) {
        Product p = productService.get(id);
        return ResponseEntity.ok()
                .body(ApiResponse.create(p));
    }
    @PostMapping
    public ResponseEntity<ApiResponse<Product>> createProduct(Product product) {
        Product result = productService.create(product);
        return ResponseEntity.ok()
                .body(ApiResponse.create(result));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Boolean>> deleteProduct(@PathVariable Long id) {
        return ResponseEntity.ok()
                .body(ApiResponse.create(productService.delete(id)));
    }
    @PostMapping( "/statistics")
    public ResponseEntity<ApiResponse<List<ProductStat>>> productStatistics() {
        List<ProductStat> result = productService.statistics();
        return ResponseEntity.ok()
                .body(ApiResponse.create(result));
    }

    @PostMapping("/reset")
    public ResponseEntity<ApiResponse<Boolean>> reset() {
        boolean result = productService.reset();
        return ResponseEntity.ok()
                .body(ApiResponse.create(result));
    }
}
