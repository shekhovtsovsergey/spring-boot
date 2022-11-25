package com.example.springboot.service;

import com.example.springboot.dao.OrderDao;
import com.example.springboot.dao.ProductDao;
import com.example.springboot.dao.UserDao;
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
public class ProductService {

    private final ProductDao productDao;
    private final OrderDao orderDao;
    private final UserDao userDao;

    public Product save(Product product) {
        if (product.getId() != null) {
            Optional<Product> productFromDbOptional = productDao.findById(product.getId());
            if (productFromDbOptional.isPresent()) {
                Product productFromDb = productFromDbOptional.get();
                productFromDb.setTitle(product.getTitle());
                productFromDb.setCost(product.getCost());
                return productDao.save(productFromDb);
            }
        }
        return productDao.save(product);
    }

    @Transactional(readOnly = true)
    public Product findById(Long id) {
        return productDao.findById(id).orElse(null);
    }

    @Transactional(readOnly = true)
    public List<Product> findAll() {
        System.out.println(orderDao.findAll());
        return productDao.findAll();

    }

    public void deleteById(Long id) {
        try {
            productDao.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            log.error("There isn't product with id {}", id);
        }

    }



}
