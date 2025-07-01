package com.yaroslavkislyi.shop.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;

public class ImageDTO {
    @Schema(description = "Уникальный идентификатор изображения", example = "61d58a2d-0117-459e-b8fe-fb3d0934bcc1")
    private UUID id;

    @NotNull(message = "Поле обязательно для заполнения")
    @Schema(description = "Изображение в виде массива байт")
    private byte[] image;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
