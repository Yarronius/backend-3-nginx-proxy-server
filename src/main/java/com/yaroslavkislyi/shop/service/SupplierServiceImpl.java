package com.yaroslavkislyi.shop.service;

import com.yaroslavkislyi.shop.dao.AddressDAO;
import com.yaroslavkislyi.shop.dao.SupplierDAO;
import com.yaroslavkislyi.shop.dto.AddressDTO;
import com.yaroslavkislyi.shop.dto.SupplierDTO;
import com.yaroslavkislyi.shop.entity.Address;
import com.yaroslavkislyi.shop.entity.Supplier;
import com.yaroslavkislyi.shop.mapper.AddressMapper;
import com.yaroslavkislyi.shop.mapper.SupplierMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SupplierServiceImpl implements SupplierService {
    @Autowired
    private SupplierDAO supplierDAO;

    @Autowired
    private AddressDAO addressDAO;

    @Autowired
    private SupplierMapper supplierMapper;

    @Autowired
    private AddressMapper addressMapper;

    @Override
    public SupplierDTO save(SupplierDTO supplierDTO) {
        Supplier supplier = supplierMapper.toEntity(supplierDTO);
        Address address = addressDAO.findById(supplierDTO.getAddressId());
        supplier.setAddress(address);
        return supplierMapper.toDto(supplierDAO.save(supplier));
    }

    @Override
    public AddressDTO updateAddressById(Long id, AddressDTO addressDTO) {
        Supplier supplier = supplierDAO.findById(id);
        if (supplier == null) {
            throw new IllegalArgumentException("Supplier with id " + id + " not found");
        }

        Address address = addressDAO.findById(supplier.getAddress().getId());
        address.setCountry(addressDTO.getCountry());
        address.setCity(addressDTO.getCity());
        address.setStreet(addressDTO.getStreet());
        addressDAO.save(address);

        return addressMapper.toDto(address);
    }

    @Override
    public void deleteById(Long id) {
        supplierDAO.deleteById(id);
    }

    @Override
    public List<SupplierDTO> findAll() {
        return supplierMapper.toDtoList(supplierDAO.findAll());
    }

    @Override
    public SupplierDTO findById(Long id) {
        return supplierMapper.toDto(supplierDAO.findById(id));
    }
}