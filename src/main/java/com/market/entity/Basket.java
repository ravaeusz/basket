package com.market.entity;

import com.market.enums.MethodPayment;
import com.market.enums.State;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Document("basket")
public class Basket {

    @Id
    private Long id;
    private List<Product> products;
    private MethodPayment payment;
    private State state;
    private double total;

    public double getTotalCalc() {
        if (products == null || products.isEmpty()) {
            return total = 0.0;
        }

        return total = products.stream()
                .mapToDouble(Product::getPrice)
                .sum();
    }



}
