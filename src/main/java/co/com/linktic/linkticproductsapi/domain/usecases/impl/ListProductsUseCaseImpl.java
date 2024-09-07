package co.com.linktic.linkticproductsapi.domain.usecases.impl;

import co.com.linktic.linkticproductsapi.domain.models.Product;
import co.com.linktic.linkticproductsapi.domain.ports.ListProductsPort;
import co.com.linktic.linkticproductsapi.domain.usecases.ListProductsUseCase;

import java.util.List;

public class ListProductsUseCaseImpl implements ListProductsUseCase {

    private final ListProductsPort listProductsPort;

    public ListProductsUseCaseImpl(ListProductsPort listProductsPort) {
        this.listProductsPort = listProductsPort;
    }

    @Override
    public List<Product> listProducts() {
        List<Product> products = listProductsPort.listProducts();
        return products.stream()
                .map(product -> new Product(product.productId(), product.name(), product.description(), product.price(), product.stock(), product.category(), product.createdAt(), product.updatedAt()))
                .toList();
    }
}