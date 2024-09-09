package co.com.linktic.linkticproductsapi.adapters.output.dynamo;

import co.com.linktic.linkticproductsapi.domain.models.Product;
import co.com.linktic.linkticproductsapi.domain.ports.CreateProductPort;
import co.com.linktic.linkticproductsapi.domain.ports.ListProductsPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.PutItemRequest;
import software.amazon.awssdk.services.dynamodb.model.ScanRequest;
import software.amazon.awssdk.services.dynamodb.model.ScanResponse;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ProductsRepository implements ListProductsPort, CreateProductPort {

    private final DynamoDbClient dynamoDbClient;

    @Override
    public List<Product> listProducts() {
        ScanResponse scanResponse = dynamoDbClient.scan(ScanRequest.builder().tableName("products").build());
        return scanResponse.items().stream()
                .map(item -> new Product(
                        item.get("product_id").s(),
                        item.get("name").s(),
                        item.get("description").s(),
                        Double.parseDouble(item.get("price").n()),
                        Integer.parseInt(item.get("stock").n()),
                        item.get("category").s(),
                        stringToLocalDateTime(item.get("created_at").s()),
                        stringToLocalDateTime(item.get("updated_at").s())
                ))
                .toList();
    }

    private LocalDateTime stringToLocalDateTime(String dateTimeString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSS");
        return LocalDateTime.parse(dateTimeString, formatter);
    }

    @Override
    public void createProduct(Product product) {
        dynamoDbClient.putItem(PutItemRequest.builder()
                .tableName("products")
                .item(toItemValues(product))
                .build());
    }

    public static Map<String, AttributeValue> toItemValues(Product product) {
        Map<String, AttributeValue> itemValues = new HashMap<>();
        itemValues.put("product_id", AttributeValue.builder().s(UUID.randomUUID().toString()).build());
        itemValues.put("name", AttributeValue.builder().s(product.name()).build());
        itemValues.put("description", AttributeValue.builder().s(product.description()).build());
        itemValues.put("price", AttributeValue.builder().n(Double.toString(product.price())).build());
        itemValues.put("stock", AttributeValue.builder().n(Integer.toString(product.stock())).build());
        itemValues.put("category", AttributeValue.builder().s(product.category()).build());
        itemValues.put("created_at", AttributeValue.builder().s(LocalDateTime.now().toString()).build());
        itemValues.put("updated_at", AttributeValue.builder().s(LocalDateTime.now().toString()).build());

        return itemValues;
    }
}
