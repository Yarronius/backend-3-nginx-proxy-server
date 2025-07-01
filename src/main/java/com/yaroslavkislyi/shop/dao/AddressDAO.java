package com.yaroslavkislyi.shop.dao;

import com.yaroslavkislyi.shop.entity.Address;
import java.util.List;

public interface AddressDAO {
    Address save(Address address);
    List<Address> getAll();
    Address findById(Long id);
}