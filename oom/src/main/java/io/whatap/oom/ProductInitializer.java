package io.whatap.oom;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class ProductInitializer {
    private final ProductRepository productRepository;

    @PostConstruct
    public void init() {
        List<Product> products = initData();
        for (Product product : products) {
            productRepository.save(product);
        }
    }

    public static List<Product> initData() {
        return List.of(
                new Product(1L, "사탕", "달고 맛잇는 사탕", 3000, 150000),
                new Product(2L, "맥스봉", "소세지", 2500, 20302),
                new Product(3L, "쿠키", "쿠키런", 4500, 15582),
                new Product(4L, "젤리", "구미구미", 2000, 123),
                new Product(5L, "포카칩", "와사삭", 3000, 5919),
                new Product(6L, "레몬", "으", 1500, 22222),
                new Product(7L, "육개장", "으어", 10000, 6),
                new Product(8L, "공책", "사각사각", 1500, 1414),
                new Product(9L, "펜", "스윽", 2500, 7979)
        );
    }
}
