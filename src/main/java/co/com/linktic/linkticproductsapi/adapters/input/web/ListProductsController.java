package co.com.linktic.linkticproductsapi.adapters.input.web;

import co.com.linktic.linkticproductsapi.adapters.input.web.dto.ProductDto;
import co.com.linktic.linkticproductsapi.domain.usecases.ListProductsUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class ListProductsController {

    private final ListProductsUseCase listProductsUseCase;

    @GetMapping("/products")
    public ResponseEntity<List<ProductDto>> listProducts() {
        List<ProductDto> productDtos = listProductsUseCase.listProducts()
                .stream().map(product -> new ProductDto(
                        product.productId(),
                        product.name(),
                        product.description(),
                        product.price(),
                        product.stock(),
                        product.category(),
                        product.createdAt(),
                        product.updatedAt()
                )).toList();

        return ResponseEntity.ok(productDtos);
    }
}
