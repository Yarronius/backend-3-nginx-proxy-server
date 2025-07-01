package com.yaroslavkislyi.shop.service;

import com.yaroslavkislyi.shop.dao.ImageDAO;
import com.yaroslavkislyi.shop.dao.ProductDAO;
import com.yaroslavkislyi.shop.dao.SupplierDAO;
import com.yaroslavkislyi.shop.dto.ProductDTO;
import com.yaroslavkislyi.shop.entity.Image;
import com.yaroslavkislyi.shop.entity.Product;
import com.yaroslavkislyi.shop.entity.Supplier;
import com.yaroslavkislyi.shop.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductDAO productDAO;

    @Autowired
    private SupplierDAO supplierDAO;

    @Autowired
    private ImageDAO imageDAO;

    @Autowired
    private ProductMapper productMapper;

    @Override
    public ProductDTO save(ProductDTO productDTO) {
        Supplier supplier = supplierDAO.findById(productDTO.getSupplierId());
        Image image = imageDAO.findImageById(productDTO.getImageId());
        if (supplier == null) {
            throw new IllegalArgumentException("Supplier not found");
        }
        if (image == null) {
            throw new IllegalArgumentException("Image not found");
        }
        Product product = productMapper.toEntity(productDTO);
        product.setSupplier(supplier);
        product.setImage(image);
        product = productDAO.save(product);
        return productMapper.toDto(product);
    }

    @Override
    public List<ProductDTO> getAll() {
        return productMapper.toDtoList(productDAO.findAll());
    }

    @Override
    public ProductDTO getById(Long id) {
        return productMapper.toDto(productDAO.findById(id));
    }

    @Override
    public ProductDTO updateAvailableStockById(Long id, Integer sub) {
        Product product = productDAO.findById(id);
        product.setAvailableStock(product.getAvailableStock() - sub);
        product = productDAO.save(product);
        return productMapper.toDto(product);
    }

    @Override
    public void deleteById(Long id) {
        productDAO.deleteById(id);
    }
}