package com.yaroslavkislyi.shop.dao;

import com.yaroslavkislyi.shop.entity.Image;
import java.util.UUID;

public interface ImageDAO {
    byte[] getImageDataById(UUID imageId);
    void deleteImageById(UUID imageId);
    Image updateImageData(UUID imageId, byte[] data);
    Image findImageById(UUID imageId);
    Image save(Image image);
}
