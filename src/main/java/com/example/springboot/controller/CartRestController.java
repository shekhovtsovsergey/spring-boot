package com.example.springboot.controller;


import com.example.springboot.entity.Cart;
import com.example.springboot.service.CartService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("api/v1/cart")
public class CartRestController {


    private final CartService cartService;

    public CartRestController(CartService cartService) {
        this.cartService = cartService;
    }


    @GetMapping("/all")
    public ResponseEntity<List<Cart>> getCartList() {
        List<Cart> carts = cartService.findAll();
        return new ResponseEntity<List<Cart>>(carts, HttpStatus.OK);
    }



    @GetMapping("/{id}")
    public ResponseEntity<Cart> getProduct(@PathVariable("id") Long id) {
        Cart carts = (Cart) cartService.findById(id);
        return new ResponseEntity<Cart>(carts, HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<?> saveCart(@RequestBody Cart cart) {
        Cart saved = cartService.save(cart);
        HttpHeaders httpHeaders = new HttpHeaders();
        return new ResponseEntity<>(httpHeaders,HttpStatus.OK);
    }


    @DeleteMapping
    public void deleteCart(@RequestBody Long id) {
        cartService.deleteById(id);
    }




}
