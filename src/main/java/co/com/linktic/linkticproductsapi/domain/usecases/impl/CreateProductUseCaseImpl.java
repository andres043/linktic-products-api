package co.com.linktic.linkticproductsapi.domain.usecases.impl;

import co.com.linktic.linkticproductsapi.domain.models.Product;
import co.com.linktic.linkticproductsapi.domain.ports.CreateProductPort;
import co.com.linktic.linkticproductsapi.domain.ports.ListProductsPort;
import co.com.linktic.linkticproductsapi.domain.usecases.CreateProductUseCase;

public class CreateProductUseCaseImpl implements CreateProductUseCase {

    private final CreateProductPort createProductPort;

    public CreateProductUseCaseImpl(CreateProductPort createProductPort) {
        this.createProductPort = createProductPort;
    }

    @Override
    public void createProduct(Product product) {
        createProductPort.createProduct(product);
    }
}