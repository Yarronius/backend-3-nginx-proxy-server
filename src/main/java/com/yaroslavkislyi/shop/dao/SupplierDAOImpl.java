package com.yaroslavkislyi.shop.dao;

import com.yaroslavkislyi.shop.entity.Supplier;
import com.yaroslavkislyi.shop.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class SupplierDAOImpl implements SupplierDAO {
    @Autowired
    private SupplierRepository supplierRepository;

    @Override
    public Supplier save(Supplier supplier) {
        return supplierRepository.save(supplier);
    }

    @Override
    public Supplier findById(Long id) {
        return supplierRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteById(Long id) {
        supplierRepository.deleteById(id);
    }

    @Override
    public List<Supplier> findAll() {
        return supplierRepository.findAll();
    }
}