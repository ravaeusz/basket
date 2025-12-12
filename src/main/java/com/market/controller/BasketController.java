package com.market.controller;

import com.market.entity.Basket;
import com.market.entity.Product;
import com.market.enums.MethodPayment;
import com.market.service.BasketRequest;
import com.market.service.BasketService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/basket")
public class BasketController {

    private final BasketService basketService;
    public BasketController(BasketService basketService) {
        this.basketService = basketService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Basket>> getBaskets(){
        return ResponseEntity.status(HttpStatus.FOUND)
                .body(basketService.getBasket());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Basket>> getBasketById(@PathVariable String id){
        return ResponseEntity.status(HttpStatus.FOUND)
                .body(basketService.getBasket(id));
    }

    @PostMapping("/post")
    public ResponseEntity<Basket> createBasket(@RequestBody BasketRequest request){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(basketService.createBasket(request));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteBasket(@PathVariable String id){
        basketService.deleteBasket(id);
        return ResponseEntity.ok()
                .body("Basket id: "+id+" excluido com sucesso!");
    }

    @PostMapping("/checkout/{id}")
    public ResponseEntity<Optional<Basket>> checkout(@PathVariable String id, @RequestBody MethodPayment payment){
        return ResponseEntity.ok()
                .body(basketService.checkout(id,payment));
    }

    @PostMapping("/add-product/{id}")
    public ResponseEntity<Basket> addProduct (@PathVariable String id, @RequestBody BasketRequest product){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(basketService.addProcuct(id,product));
    }

}
