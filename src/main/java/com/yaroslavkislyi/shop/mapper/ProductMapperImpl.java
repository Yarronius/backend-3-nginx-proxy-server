package com.yaroslavkislyi.shop.mapper;

import com.yaroslavkislyi.shop.dto.ProductDTO;
import com.yaroslavkislyi.shop.entity.Product;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class ProductMapperImpl implements ProductMapper {
    @Override
    public ProductDTO toDto(Product product) {
        ProductDTO dto = new ProductDTO();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setCategory(product.getCategory());
        dto.setPrice(product.getPrice());
        dto.setAvailableStock(product.getAvailableStock());
        dto.setLastUpdateDate(product.getLastUpdateDate());
        dto.setSupplierId(product.getSupplier().getId());
        dto.setImageId(product.getImage().getId());
        return dto;
    }

    @Override
    public Product toEntity(ProductDTO dto) {
        Product product = new Product();
        product.setId(dto.getId());
        product.setName(dto.getName());
        product.setCategory(dto.getCategory());
        product.setPrice(dto.getPrice());
        product.setAvailableStock(dto.getAvailableStock());
        product.setLastUpdateDate(dto.getLastUpdateDate());
        return product;
    }

    @Override
    public List<ProductDTO> toDtoList(List<Product> products) {
        if (products == null) return List.of();
        return products.stream().map(this::toDto).toList();
    }
}
