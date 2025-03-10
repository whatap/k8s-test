package io.whatap.oom;

import java.util.List;

public interface ProductRepository {
    List<Product> findAll();
    Product findById(Long id);
    Product save(Product product);
    boolean delete(Long id);
    boolean deleteAll();
}
