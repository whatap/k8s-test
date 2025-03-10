package io.whatap.oom.config;

import io.whatap.oom.repo.ProductMemoryRepositoryImpl;
import io.whatap.oom.repo.ProductRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductDBConfig {
    @Bean
    public ProductRepository productRepository() {
        return new ProductMemoryRepositoryImpl();
    }
}
