package co.com.linktic.linkticproductsapi.adapters.input.web.dto;


import co.com.linktic.linkticproductsapi.domain.models.Product;

import java.time.LocalDateTime;

public record ProductDto(
        String productId,
        String name,
        String description,
        double price,
        int stock,
        String category,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    public Product toModel() {
        return new Product(productId, name, description, price, stock, category, createdAt, updatedAt);
    }
}
