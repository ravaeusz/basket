package com.market.service;

import com.market.client.PlatziStoreClient;
import com.market.client.ProductsResponse;
import com.market.entity.Basket;
import com.market.entity.Product;
import com.market.enums.MethodPayment;
import com.market.enums.State;
import com.market.repository.BasketRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static com.market.enums.State.CLOSE;

@Service
public class BasketService {

    private final BasketRepository basketRepository;
    private final PlatziStoreClient platziStoreClient;

    public BasketService(BasketRepository basketRepository, PlatziStoreClient platziStoreClient) {
        this.basketRepository = basketRepository;
        this.platziStoreClient = platziStoreClient;
    }

    public List<Basket> getBasket(){
        return basketRepository.findAll();
    }

    public Basket createBasket(BasketRequest request) {
        List<Product> products = request.products().stream()
                .map(p -> {
                    ProductsResponse productsResponse = platziStoreClient.getProductById(p.getId());
                    return new Product(
                            productsResponse.id(),
                            productsResponse.title(),
                            productsResponse.price(),
                            p.getQuantity()
                    );
                })
                .toList();
        Basket basket = new Basket();
        basket.setProducts(products);
        basket.setState(State.OPEN);
        basket.setTotal(basket.getTotalCalc());

        return basketRepository.save(basket);

    }

    public Optional<Basket> checkout(String id, MethodPayment payment) {
        return basketRepository.findById(id)
                .map(basket ->{
                    basket.setPayment(payment);
                    basket.setState(State.CLOSE);
                    basket.setTotal(basket.getTotalCalc());
                    return basketRepository.save(basket);
                });
    }

    public Optional<Basket>  getBasket(String id){
        return basketRepository.findById(id)
                .map(basket -> {
                    basket.getTotalCalc();
                    return basket;
                });
    }

    public void deleteBasket(String id){
        basketRepository.deleteById(id);
    }
}
