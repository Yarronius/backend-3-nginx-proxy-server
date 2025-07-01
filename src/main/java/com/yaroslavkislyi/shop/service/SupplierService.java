package com.yaroslavkislyi.shop.service;

import com.yaroslavkislyi.shop.dto.AddressDTO;
import com.yaroslavkislyi.shop.dto.SupplierDTO;
import java.util.List;

public interface SupplierService {
    SupplierDTO save(SupplierDTO supplierDTO);
    AddressDTO updateAddressById(Long id, AddressDTO addressDTO);
    void deleteById(Long id);
    List<SupplierDTO> findAll();
    SupplierDTO findById(Long id);
}
