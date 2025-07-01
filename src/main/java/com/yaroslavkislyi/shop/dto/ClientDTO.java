package com.yaroslavkislyi.shop.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public class ClientDTO {
    @Schema(description = "Уникальный идентификатор клиента", example = "1")
    private Long id;

    @NotNull(message = "Поле 'name' обязательно для заполнения")
    @NotBlank(message = "Строка с именем не может быть пустой")
    @Schema(description = "Имя клиента", example = "Patris")
    private String name;

    @NotNull(message = "Поле 'surname' обязательно для заполнения")
    @NotBlank(message = "Строка с фамилией не может быть пустой")
    @Schema(description = "Фамилия", example = "Lumumba")
    private String surname;

    @NotNull(message = "Поле 'birthday' обязательно для заполнения")
    @Schema(description = "Уникальный идентификатор продукта", example = "2004-01-15")
    private LocalDate birthday;

    @NotEmpty(message = "Поле 'gender' обязательно для заполнения")
    @Schema(description = "Пол клиента (допускаются только 'male' или 'female')", example = "male")
    private String gender;

    @NotNull(message = "Поле 'registrationDate' обязательно для заполнения")
    @Schema(description = "Дата регистрации клиента в базе данных", example = "2004-01-15")
    private LocalDate registrationDate;

    @NotNull(message = "Поле 'addressId' обязательно")
    @Schema(description = "Id адреса клиента")
    private Long addressId;

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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Long getAddressId() {
        return addressId;
    }

    public void setAddressId(Long addressId) {
        this.addressId = addressId;
    }
}
