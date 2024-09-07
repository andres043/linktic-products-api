package co.com.linktic.linkticproductsapi.domain.models;

import java.time.LocalDateTime;

public record Product(
        String productId,
        String name,
        String description,
        double price,
        int stock,
        String category,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {}

