package co.com.linktic.linkticproductsapi.domain.usecases;

import co.com.linktic.linkticproductsapi.domain.models.Product;

import java.util.List;

public interface ListProductsUseCase {

    List<Product> listProducts();
}
