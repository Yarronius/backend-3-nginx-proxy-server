package com.yaroslavkislyi.shop.dao;

import com.yaroslavkislyi.shop.entity.Supplier;
import java.util.List;

public interface SupplierDAO {
    Supplier save(Supplier supplier);
    Supplier findById(Long id);
    void deleteById(Long id);
    List<Supplier> findAll();
}
