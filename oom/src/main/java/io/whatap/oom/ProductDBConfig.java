package io.whatap.oom;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductDBConfig {
    @Bean
    public ProductRepository productRepository() {
        return new ProductMemoryRepositoryImpl();
    }
}
