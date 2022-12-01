package com.example.springboot.controller;


import com.example.springboot.entity.Product;
import com.example.springboot.service.ProductService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/v1/product")
public class ProductRestController {


    private final ProductService productService;

    public ProductRestController(ProductService productService) {
        this.productService = productService;
    }


    @GetMapping("/all")
    public ResponseEntity<List<Product>> getProductList() {
        List<Product> products = productService.findAll();
        return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
    }



    @GetMapping("/{id}")
    public ResponseEntity<List<Product>> getProduct(@PathVariable("id") Long id) {
        List<Product> products = (List<Product>) productService.findById(id);
        return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<?> saveProduct(@RequestBody Product product) {
        Product saved = productService.save(product);
        HttpHeaders httpHeaders = new HttpHeaders();
        return new ResponseEntity<>(httpHeaders,HttpStatus.OK);
    }


    @DeleteMapping
    public void deleteProduct(@RequestBody Long id) {
        productService.deleteById(id);
    }



}
