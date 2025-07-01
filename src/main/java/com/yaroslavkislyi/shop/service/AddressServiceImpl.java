package com.yaroslavkislyi.shop.service;

import com.yaroslavkislyi.shop.dao.AddressDAO;
import com.yaroslavkislyi.shop.dto.AddressDTO;
import com.yaroslavkislyi.shop.entity.Address;
import com.yaroslavkislyi.shop.mapper.AddressMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressDAO addressDAO;

    @Autowired
    private AddressMapper addressMapper;

    @Override
    public List<AddressDTO> getAll() {
        return addressMapper.toDtoList(addressDAO.getAll());
    }

    @Override
    public AddressDTO save(AddressDTO addressDTO) {
        Address address = addressMapper.toEntity(addressDTO);
        address = addressDAO.save(address);
        return addressMapper.toDto(address);
    }
}