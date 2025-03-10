package io.whatap.oom;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ProductMemoryRepositoryImpl implements ProductRepository{
    private static final Map<Long, Product> MEMORY = new ConcurrentHashMap<>();

    @Override
    public List<Product> findAll() {
        if(MEMORY.isEmpty()) {
            return List.of();
        }
        return List.copyOf(MEMORY.values());
    }

    @Override
    public Product findById(Long id) {
        if(MEMORY.containsKey(id)) {
            return MEMORY.get(id);
        }
        return null;
    }

    @Override
    public synchronized Product save(Product product) {
        if(product.getId() == null) {
            product.setId(MEMORY.size() + 1L);
            MEMORY.put(product.getId(), product);
        }else {
            MEMORY.put(product.getId(), product);
        }
        return product;
    }

    @Override
    public synchronized boolean delete(Long id) {
        MEMORY.remove(id);
        return true;
    }

    @Override
    public boolean deleteAll() {
        MEMORY.clear();
        return true;
    }
}
