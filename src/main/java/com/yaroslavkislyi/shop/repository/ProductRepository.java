package com.yaroslavkislyi.shop.repository;

import com.yaroslavkislyi.shop.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
