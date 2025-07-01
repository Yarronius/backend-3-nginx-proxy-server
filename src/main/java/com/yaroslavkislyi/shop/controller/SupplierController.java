package com.yaroslavkislyi.shop.controller;

import com.yaroslavkislyi.shop.dto.AddressDTO;
import com.yaroslavkislyi.shop.dto.ClientDTO;
import com.yaroslavkislyi.shop.dto.SupplierDTO;
import com.yaroslavkislyi.shop.service.SupplierService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
@Tag(name = "Suuplier", description = "Операции с поставщиками")
public class SupplierController {
    @Autowired
    private SupplierService supplierService;

    @Operation(
            summary = "Создать поставщика",
            description = "Добавляет нового поставщика в систему",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    description = "JSON объект с данными адреса",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = AddressDTO.class),
                            examples = {
                                    @ExampleObject(
                                            name = "Пример поставщика",
                                            summary = "Пример запроса",
                                            description = "Пример корректного JSON-запроса",
                                            value = """
                                                   {
                                                       "id": 3,
                                                       "name": "VegMax",
                                                       "addressId": 15,
                                                       "phone_number": "9-568-256-23-23"
                                                   }
                                                    """
                                    )
                            }
                    )
            )
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Поставщик добавлен",
                    content = @Content(schema = @Schema(implementation = ClientDTO.class))),
            @ApiResponse(responseCode = "404", description = "Ошибка в запросе")
    })
    @PostMapping("/suppliers")
    public ResponseEntity<?> createSupplier(@Valid @RequestBody SupplierDTO supplierDTO, BindingResult result) {
        if (result.hasErrors()) {
            String errorMsg = result.getAllErrors().stream()
                    .map(ObjectError::getDefaultMessage)
                    .collect(Collectors.joining("; "));
            return ResponseEntity.badRequest().body(errorMsg);
        }
        return ResponseEntity.ok(supplierService.save(supplierDTO));
    }



    @Operation(
            summary = "Обновить адрес поставщика",
            description = "Обновляет адрес поставщика в систему",
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
                                                        "id": null,
                                                        "country": "USA",
                                                        "city": "Detroit",
                                                        "street": "Savelovskaya"
                                                   }
                                                    """
                                    )
                            }
                    )
            )
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Поставщик обновлён",
                    content = @Content(schema = @Schema(implementation = ClientDTO.class))),
            @ApiResponse(responseCode = "404", description = "Поставщик не найден")
    })
    @PutMapping("/suppliers/{id}")
    public ResponseEntity<?> updateSupplierAddress(@Parameter(description = "ID поставщика", example = "1")
                                                                 @PathVariable Long id,
                                                             @Valid @RequestBody AddressDTO addressDTO) {
        SupplierDTO supplierDTO = supplierService.findById(id);
        if (supplierDTO == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        addressDTO = supplierService.updateAddressById(id, addressDTO);
        return ResponseEntity.ok(addressDTO);
    }



    @Operation(
            summary = "Удаление поставщика по ID",
            description = "Удаляет поставщика по его идентификатору."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Поставщик удалён",
                    content = @Content(schema = @Schema(implementation = ClientDTO.class))),
            @ApiResponse(responseCode = "404", description = "Поставщик не найден")
    })
    @DeleteMapping("/suppliers/{id}")
    public ResponseEntity<SupplierDTO> deleteSupplier(@Parameter(description = "ID поставщика", example = "61")
                                                          @PathVariable Long id) {
        SupplierDTO supplierDTO = supplierService.findById(id);
        if (supplierDTO != null) {
            supplierService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }



    @Operation(
            summary = "Получение всех поставщиков",
            description = "Возвращает всех поставщиков, имеющихся в базе данных."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Все поставщики из базы данных",
                    content = @Content(schema = @Schema(implementation = ClientDTO.class)))
    })
    @GetMapping("/suppliers")
    public ResponseEntity<List<SupplierDTO>> getAllSuppliers() {
        return ResponseEntity.ok(supplierService.findAll());
    }



    @Operation(
            summary = "Получить продукт по ID",
            description = "Возвращает подробную информацию о продукте по его идентификатору."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Поставщик получен",
                    content = @Content(schema = @Schema(implementation = ClientDTO.class))),
            @ApiResponse(responseCode = "404", description = "Поставщик не найден")
    })
    @GetMapping("/suppliers/{id}")
    public ResponseEntity<SupplierDTO> getSupplierById(@Parameter(description = "ID поставщика", example = "1")
                                                           @PathVariable Long id) {
        SupplierDTO supplierDTO = supplierService.findById(id);
        if (supplierDTO == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(supplierDTO);
    }
}