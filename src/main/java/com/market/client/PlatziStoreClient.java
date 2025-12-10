package com.market.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


import java.util.List;

@FeignClient(value = "PlatziStoreClient", url = "https://api.escuelajs.co/api/v1/")
public interface PlatziStoreClient {

    @GetMapping("products")
    List<ProductsResponse> getProducts();

    @GetMapping("products/{id}")
    ProductsResponse getProductById(@PathVariable(value = "id") Long id);
}
