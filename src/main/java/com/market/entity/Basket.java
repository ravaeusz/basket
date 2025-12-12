package com.market.entity;

import com.market.enums.MethodPayment;
import com.market.enums.State;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Document(collection = "market-basket")
public class Basket {

    @Id
    private String id;
    private List<Product> products;
    private MethodPayment payment;
    private State state;
    private BigDecimal total;

    public BigDecimal getTotalCalc() {
        return products.stream()
                .map(p -> BigDecimal.valueOf(p.getPrice())
                        .multiply(BigDecimal.valueOf(p.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }



}
