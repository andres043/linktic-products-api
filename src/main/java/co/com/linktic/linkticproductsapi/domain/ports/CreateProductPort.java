package co.com.linktic.linkticproductsapi.domain.ports;

import co.com.linktic.linkticproductsapi.domain.models.Product;

public interface CreateProductPort {

    void createProduct(Product product);
}
