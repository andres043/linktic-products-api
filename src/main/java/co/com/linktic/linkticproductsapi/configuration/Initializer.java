package co.com.linktic.linkticproductsapi.configuration;

import co.com.linktic.linkticproductsapi.adapters.output.dynamo.ProductsRepository;
import co.com.linktic.linkticproductsapi.domain.usecases.CreateProductUseCase;
import co.com.linktic.linkticproductsapi.domain.usecases.ListProductsUseCase;
import co.com.linktic.linkticproductsapi.domain.usecases.impl.CreateProductUseCaseImpl;
import co.com.linktic.linkticproductsapi.domain.usecases.impl.ListProductsUseCaseImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

import java.net.URI;

@Configuration
public class Initializer {

    @Bean
    public ListProductsUseCase listProductsUseCaseImpl(ProductsRepository productsRepository) {
        return new ListProductsUseCaseImpl(productsRepository);
    }

    @Bean
    public CreateProductUseCase createProductUseCaseImpl(ProductsRepository productsRepository) {
        return new CreateProductUseCaseImpl(productsRepository);
    }

    @Bean
    public DynamoDbClient dynamoDbClient() {
        AwsBasicCredentials credentials = AwsBasicCredentials.create("accessKeyId", "secretAccessKey");
        Region region = Region.US_EAST_1;
        return DynamoDbClient.builder()
                .region(region)
                .endpointOverride(URI.create("http://localhost:4566"))
                .credentialsProvider(StaticCredentialsProvider.create(credentials))
                .build();
    }
}
