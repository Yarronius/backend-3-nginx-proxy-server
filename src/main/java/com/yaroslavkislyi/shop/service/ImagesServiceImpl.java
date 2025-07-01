package com.yaroslavkislyi.shop.service;

import com.yaroslavkislyi.shop.dao.ImageDAO;
import com.yaroslavkislyi.shop.dao.ProductDAO;
import com.yaroslavkislyi.shop.dto.ImageDTO;
import com.yaroslavkislyi.shop.mapper.ImagesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ImagesServiceImpl implements ImagesService {
    @Autowired
    private ImageDAO imageDAO;

    @Autowired
    private ProductDAO productDAO;

    @Autowired
    private ImagesMapper imagesMapper;

    @Override
    public UUID save(ImageDTO imageDTO) {
        return imageDAO.save(imagesMapper.toEntity(imageDTO)).getId();

    }

    @Override
    public byte[] getImageDataById(UUID id) {
        return imageDAO.getImageDataById(id);
    }

    @Override
    public byte[] getImageDataByProductId(Long productId) {
        UUID imageId = productDAO.findById(productId).getImage().getId();
        return imageDAO.getImageDataById(imageId);
    }

    @Override
    public ImageDTO updateImageById(UUID id, byte[] imageData) {
        return imagesMapper.toDto(imageDAO.updateImageData(id, imageData));
    }

    @Override
    public ImageDTO getImageById(UUID imageId) {
        return imagesMapper.toDto(imageDAO.findImageById(imageId));
    }

    @Override
    public void deleteImageById(UUID imageId) {
        imageDAO.deleteImageById(imageId);
    }
}