package co.com.linktic.linkticproductsapi.domain.usecases;

import co.com.linktic.linkticproductsapi.domain.models.Product;

public interface CreateProductUseCase {

    void createProduct(Product product);
}
