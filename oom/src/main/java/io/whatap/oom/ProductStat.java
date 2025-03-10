package io.whatap.oom;

import lombok.Data;

@Data
public class ProductStat {
    private Long id;
    private String name;
    private Long totalPrice;

    public ProductStat() {}

    public ProductStat(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.totalPrice = (long) product.getPrice() * product.getStock();
    }

}
