package co.com.linktic.linkticproductsapi.domain.ports;

import co.com.linktic.linkticproductsapi.domain.models.Product;

import java.util.List;

public interface ListProductsPort {

    List<Product> listProducts();
}
