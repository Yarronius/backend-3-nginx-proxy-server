package com.yaroslavkislyi.shop.service;

import com.yaroslavkislyi.shop.dto.ProductDTO;
import java.util.List;

public interface ProductService {
    ProductDTO save(ProductDTO productDTO);
    List<ProductDTO> getAll();
    ProductDTO getById(Long id);
    ProductDTO updateAvailableStockById(Long id, Integer sub);
    void deleteById(Long id);
}