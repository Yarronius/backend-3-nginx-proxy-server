package com.yaroslavkislyi.shop.controller;

import com.yaroslavkislyi.shop.dto.AddressDTO;
import com.yaroslavkislyi.shop.service.AddressService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
@Tag(name = "Address", description = "Операции с адресами")
public class AddressController {
    @Autowired
    private AddressService addressService;

    @Operation(
            summary = "Получить список всех адресов",
            description = "Возвращает полный список всех адресов в базе данных"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Все адреса в базе данных",
                    content = @Content(schema = @Schema(implementation = AddressDTO.class)))
    })
    @GetMapping("/addresses")
    public ResponseEntity<List<AddressDTO>> getAllAddresses() {
        return ResponseEntity.ok(addressService.getAll());
    }

    @Operation(
            summary = "Создать адрес",
            description = "Добавляет новый адрес в систему",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    description = "JSON объект с данными адреса",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = AddressDTO.class),
                            examples = {
                                    @ExampleObject(
                                            name = "Пример адреса",
                                            summary = "Пример запроса",
                                            description = "Пример корректного JSON-запроса",
                                            value = """
                                                {
                                                    "country": "USA",
                                                    "city": "Detroit",
                                                    "street": "George Floyd's st."
                                                }
                                                    """
                                    )
                            }
                    )
            )
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Адрес добавлен/изменен",
                    content = @Content(schema = @Schema(implementation = AddressDTO.class))),
            @ApiResponse(responseCode = "400", description = "Неверный формат запроса")
    })
    @PostMapping("/addresses")
    public ResponseEntity<?> createAddress(@RequestBody AddressDTO addressDTO, BindingResult result) {
        if (result.hasErrors()) {
            String errorMsg = result.getAllErrors().stream()
                    .map(ObjectError::getDefaultMessage)
                    .collect(Collectors.joining("; "));
            return ResponseEntity.badRequest().body(errorMsg);
        }
        return ResponseEntity.ok(addressService.save(addressDTO));
    }
}