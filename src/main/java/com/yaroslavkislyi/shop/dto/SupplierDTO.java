package com.yaroslavkislyi.shop.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class SupplierDTO {
    private Long id;

    @NotNull(message = "Поле 'name' обязательно для заполнения")
    @NotBlank(message = "Строка с именем поставщика не может быть пустой")
    @Schema(description = "Имя поставщика", example = "Miratorg")
    private String name;

    @NotNull(message = "Поле 'address' обязательно для заполнения")
    @Schema(description = "Адрес поставщика")
    private Long addressId;

    @NotNull(message = "Поле 'phone_number' обязательно для заполнения")
    @NotBlank(message = "Строка с номером телефона не может быть пустой")
    @Schema(description = "Телефонный номер поставщика", example = "+7 645 689 12 87")
    private String phoneNumber;

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

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
