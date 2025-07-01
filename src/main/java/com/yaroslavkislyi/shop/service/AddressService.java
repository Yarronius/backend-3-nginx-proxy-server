package com.yaroslavkislyi.shop.service;

import com.yaroslavkislyi.shop.dto.AddressDTO;
import java.util.List;

public interface AddressService {
    List<AddressDTO> getAll();
    AddressDTO save(AddressDTO addressDTO);
}