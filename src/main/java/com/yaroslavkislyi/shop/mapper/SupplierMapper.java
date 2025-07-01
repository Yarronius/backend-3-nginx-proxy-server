package com.yaroslavkislyi.shop.mapper;

import com.yaroslavkislyi.shop.dto.SupplierDTO;
import com.yaroslavkislyi.shop.entity.Supplier;
import java.util.List;

public interface SupplierMapper {
    SupplierDTO toDto(Supplier supplier);
    Supplier toEntity(SupplierDTO dto);
    List<SupplierDTO> toDtoList(List<Supplier> suppliers);
}
