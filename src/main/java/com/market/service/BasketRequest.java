package com.market.service;

import com.market.entity.Product;

import java.util.List;

public record BasketRequest(List<Product> products) {
}
