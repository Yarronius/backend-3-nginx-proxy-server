package com.yaroslavkislyi.shop.mapper;

import com.yaroslavkislyi.shop.dto.SupplierDTO;
import com.yaroslavkislyi.shop.entity.Supplier;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class SupplierMapperImpl implements SupplierMapper {
    @Override
    public SupplierDTO toDto(Supplier supplier) {
        if (supplier == null) return null;

        SupplierDTO dto = new SupplierDTO();
        dto.setId(supplier.getId());
        dto.setName(supplier.getName());
        dto.setPhoneNumber(supplier.getPhone_number());
        dto.setAddressId(supplier.getAddress().getId());

        return dto;
    }

    @Override
    public Supplier toEntity(SupplierDTO dto) {
        if (dto == null) return null;

        Supplier supplier = new Supplier();
        supplier.setId(dto.getId());
        supplier.setName(dto.getName());
        supplier.setPhone_number(dto.getPhoneNumber());
        return supplier;
    }

    @Override
    public List<SupplierDTO> toDtoList(List<Supplier> suppliers) {
        return suppliers.stream().map(this::toDto).collect(Collectors.toList());
    }
}
