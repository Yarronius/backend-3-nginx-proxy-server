package com.yaroslavkislyi.shop.dao;

import com.yaroslavkislyi.shop.entity.Product;
import java.util.List;

public interface ProductDAO {
    Product save(Product product);
    List<Product> findAll();
    Product findById(Long id);
    void deleteById(Long id);
}
