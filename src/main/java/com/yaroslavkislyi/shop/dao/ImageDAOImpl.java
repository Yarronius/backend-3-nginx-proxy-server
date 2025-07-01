package com.yaroslavkislyi.shop.dao;

import com.yaroslavkislyi.shop.entity.Image;
import com.yaroslavkislyi.shop.repository.ImagesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public class ImageDAOImpl implements ImageDAO {

    @Autowired
    private ImagesRepository imagesRepository;

    @Override
    public byte[] getImageDataById(UUID imageId) {
        Image image = imagesRepository.findById(imageId).orElse(null);
        return image != null ? image.getData() : null;
    }

    @Override
    public void deleteImageById(UUID imageId) {
        imagesRepository.deleteById(imageId);
    }

    @Override
    public Image updateImageData(UUID imageId, byte[] data) {
        Image image = imagesRepository.findById(imageId).orElse(null);
        if (image != null )image.setData(data);
        return image == null ? null : imagesRepository.save(image);
    }

    @Override
    public Image findImageById(UUID imageId) {
        return imagesRepository.findById(imageId).orElse(null);
    }

    @Override
    public Image save(Image image) {
        return imagesRepository.save(image);
    }
}
