package com.market.service;

import com.market.client.PlatziStoreClient;
import com.market.entity.Product;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Getter
@Setter
public class ProductService {

    private final PlatziStoreClient platziStoreClient;

    public ProductService(PlatziStoreClient platziStoreClient) {
        this.platziStoreClient = platziStoreClient;
    }

    public List<Product> getAllProducts(){
        var products = platziStoreClient.getProducts();
        return products.stream()
                .map(product -> new Product(product.id(), product.title(), product.price()))
                .toList();
    }

    public Product getProductById(Long id){
        var product = platziStoreClient.getProductById(id);
        return new Product(product.id(), product.title(), product.price());
    }
}
