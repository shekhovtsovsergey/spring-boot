package com.example.springboot.service;


import com.example.springboot.dao.CartDao;
import com.example.springboot.dao.ProductDao;
import com.example.springboot.entity.Cart;
import com.example.springboot.entity.Product;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CartService {

    private final CartDao cartDao;
    private final ProductDao productDao;

    public Cart save(Cart cart) {
        if (cart.getId() != null) {
            Optional<Cart> productFromDbOptional = cartDao.findById(cart.getId());
            if (productFromDbOptional.isPresent()) {
                Cart cartFromDb = productFromDbOptional.get();
                cartFromDb.setTitle(cart.getTitle());
                cartFromDb.setCost(cart.getCost());
                return cartDao.save(cartFromDb);
            }
        }
        return cartDao.save(cart);
    }

    @Transactional(readOnly = true)
    public Cart findById(Long id) {
        return cartDao.findById(id).orElse(null);
    }


    @Transactional(readOnly = true)
    public List<Cart> findAll() {
        return cartDao.findAll();
    }



    public void deleteById(Long id) {
        try {
            cartDao.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            log.error("There isn't product with id {}", id);
        }
    }


    public Cart addById(Long id) {
        Optional<Product> product = productDao.findById(id);
        Cart cart = new Cart();
        cart.setTitle(product.get().getTitle());
        cart.setCost(product.get().getCost());
        return cartDao.save(cart);
        }





}
