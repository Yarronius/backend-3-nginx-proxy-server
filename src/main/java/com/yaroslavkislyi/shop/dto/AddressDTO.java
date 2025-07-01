package com.yaroslavkislyi.shop.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class AddressDTO {
    @Schema(description = "Уникальный идентификатор адреса", example = "1")
    private Long id;

    @NotNull(message = "Поле 'country' обязательно для заполнения")
    @NotBlank(message = "Строка с названием страны не может быть пустой")
    @Schema(description = "Страна", example = "Ephiopia")
    private String country;

    @NotNull(message = "Поле 'city' обязательно для заполнения")
    @NotBlank(message = "Строка с названием города не может быть пустой")
    @Schema(description = "Город", example = "Cairo")
    private String city;

    @NotNull(message = "Поле 'street' обязательно для заполнения")
    @NotBlank(message = "Строка с названием улицы не может быть пустой")
    @Schema(description = "Улица", example = "Savelovskaya")
    private String street;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }
}
