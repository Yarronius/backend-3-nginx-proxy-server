package com.yaroslavkislyi.shop.repository;

import com.yaroslavkislyi.shop.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {
}
