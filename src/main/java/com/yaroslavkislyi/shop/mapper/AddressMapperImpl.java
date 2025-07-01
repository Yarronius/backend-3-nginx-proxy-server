package com.yaroslavkislyi.shop.mapper;

import com.yaroslavkislyi.shop.dto.AddressDTO;
import com.yaroslavkislyi.shop.entity.Address;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class AddressMapperImpl implements AddressMapper {
    @Override
    public AddressDTO toDto(Address address) {
        if (address == null) return null;

        AddressDTO dto = new AddressDTO();
        dto.setId(address.getId());
        dto.setCity(address.getCity());
        dto.setCountry(address.getCountry());
        dto.setStreet(address.getStreet());
        return dto;
    }

    @Override
    public Address toEntity(AddressDTO dto) {
        if (dto == null) return null;

        Address address = new Address();
        address.setId(dto.getId());
        address.setCity(dto.getCity());
        address.setCountry(dto.getCountry());
        address.setStreet(dto.getStreet());
        return address;
    }

    @Override
    public List<AddressDTO> toDtoList(List<Address> address) {
        if (address == null) return List.of();
        return address.stream().map(this::toDto).collect(Collectors.toList());
    }
}
