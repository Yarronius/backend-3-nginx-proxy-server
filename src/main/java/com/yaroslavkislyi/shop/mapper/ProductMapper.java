package com.yaroslavkislyi.shop.mapper;

import com.yaroslavkislyi.shop.dto.ProductDTO;
import com.yaroslavkislyi.shop.entity.Product;
import java.util.List;

public interface ProductMapper {
    ProductDTO toDto(Product product);
    Product toEntity(ProductDTO dto);
    List<ProductDTO> toDtoList(List<Product> products);
}
