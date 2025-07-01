package com.yaroslavkislyi.shop.mapper;

import com.yaroslavkislyi.shop.dto.ImageDTO;
import com.yaroslavkislyi.shop.entity.Image;
import java.util.List;

public interface ImagesMapper {
    ImageDTO toDto(Image image);
    Image toEntity(ImageDTO dto);
    List<ImageDTO> toDtoList(List<Image> images);
}
