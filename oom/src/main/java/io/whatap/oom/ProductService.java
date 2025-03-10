package io.whatap.oom;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProductService {
    private final OomExecutor oomExecutor;
    private final ProductRepository productRepository;

    public Product get(Long id) {
        return productRepository.findById(id);
    }

    public Product create(Product product) {
        return productRepository.save(product);
    }

    public boolean delete(Long id) {
        return productRepository.delete(id);
    }

    /**
     * oom 내는 서비스 로직
     */
    public List<ProductStat> statistics() {
        oomExecutor.execute();
        return productRepository.findAll().stream()
                .map(ProductStat::new)
                .toList();
    }

    public boolean reset() {
        productRepository.deleteAll();
        List<Product> products = ProductInitializer.initData();
        for (Product product : products) {
            productRepository.save(product);
        }
        return true;
    }
}
