package com.yaroslavkislyi.shop.service;

import com.yaroslavkislyi.shop.dto.ImageDTO;
import java.util.UUID;

public interface ImagesService {
    UUID save(ImageDTO imageDTO);
    byte[] getImageDataById(UUID Id);
    byte[] getImageDataByProductId(Long productId);
    ImageDTO updateImageById(UUID Id, byte[] imageData);
    ImageDTO getImageById(UUID Id);
    void deleteImageById(UUID Id);
}
