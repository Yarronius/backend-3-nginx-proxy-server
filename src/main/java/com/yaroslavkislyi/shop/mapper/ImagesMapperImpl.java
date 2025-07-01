package com.yaroslavkislyi.shop.mapper;

import com.yaroslavkislyi.shop.dto.ImageDTO;
import com.yaroslavkislyi.shop.entity.Image;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ImagesMapperImpl implements ImagesMapper {
    @Override
    public ImageDTO toDto(Image image) {
        ImageDTO imageDTO = new ImageDTO();
        imageDTO.setId(image.getId());
        imageDTO.setImage(image.getData());
        return imageDTO;
    }

    @Override
    public Image toEntity(ImageDTO dto) {
        Image image = new Image();
        image.setId(dto.getId());
        image.setData(dto.getImage());
        return image;
    }

    @Override
    public List<ImageDTO> toDtoList(List<Image> images) {
        return images.stream().map(this::toDto).collect(Collectors.toList());
    }
}
