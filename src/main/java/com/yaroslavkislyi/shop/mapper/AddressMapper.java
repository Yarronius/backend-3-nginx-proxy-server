package com.yaroslavkislyi.shop.mapper;

import com.yaroslavkislyi.shop.dto.AddressDTO;
import com.yaroslavkislyi.shop.entity.Address;
import java.util.List;

public interface AddressMapper {
    AddressDTO toDto(Address address);
    Address toEntity(AddressDTO dto);
    List<AddressDTO> toDtoList(List<Address> address);
}
