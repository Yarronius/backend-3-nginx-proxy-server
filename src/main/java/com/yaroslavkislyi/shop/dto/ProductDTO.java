package com.yaroslavkislyi.shop.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.UUID;

public class ProductDTO {
    private Long id;

    @NotNull(message = "Поле 'name' обязательно для заполнения")
    @NotBlank(message = "Строка с именем не может быть пустой")
    @Schema(description = "Название продукта", example = "помидоры")
    private String name;

    @NotNull(message = "Поле 'category' обязательно для заполнения")
    @NotBlank(message = "Строка с категорией не может быть пустой")
    @Schema(description = "Категория продукта", example = "овощи")
    private String category;

    @NotNull(message = "Поле 'price' обязательно для заполнения")
    @Schema(description = "Стоимость продукта", example = "150")
    private Integer price;

    @NotNull(message = "Поле 'availableStock' обязательно для заполнения")
    @Schema(description = "Количество доступного товара", example = "1000")
    private Integer availableStock;

    @NotNull(message = "Поле 'lastUpdateDate' обязательно для заполнения")
    @Schema(description = "Дата последнего обновления", example = "2025-06-12")
    private LocalDate lastUpdateDate;

    @OneToOne
    @NotNull(message = "Поле 'supplierId' обязательно для заполнения")
    @Schema(description = "Имя поставщика продукта")
    private Long supplierId;

    @OneToOne
    @NotNull(message = "Поле 'imageId' обязательно для заполнения")
    @Schema(description = "Изображение продукта")
    private UUID imageId;

    public ProductDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getAvailableStock() {
        return availableStock;
    }

    public void setAvailableStock(Integer availableStock) {
        this.availableStock = availableStock;
    }

    public LocalDate getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(LocalDate lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public UUID getImageId() {
        return imageId;
    }

    public void setImageId(UUID imageId) {
        this.imageId = imageId;
    }
}
