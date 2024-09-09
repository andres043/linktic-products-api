package co.com.linktic.linkticproductsapi.adapters.input.web;

import co.com.linktic.linkticproductsapi.adapters.input.web.dto.ProductDto;
import co.com.linktic.linkticproductsapi.domain.usecases.CreateProductUseCase;
import co.com.linktic.linkticproductsapi.domain.usecases.ListProductsUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
@Tag(name = "Products", description = "API for managing products")
public class CreateProductController {

    private final CreateProductUseCase createProductUseCase;

    @PostMapping("/products")
    @Operation(summary = "Create order", description = "Creates a new product.")
    public ResponseEntity<Void> createProduct(@RequestBody ProductDto productDto) {
        createProductUseCase.createProduct(productDto.toModel());
        return ResponseEntity.created(URI.create("/v0/products")).build();
    }
}
