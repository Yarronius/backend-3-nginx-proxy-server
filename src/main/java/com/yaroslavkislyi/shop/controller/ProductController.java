package com.yaroslavkislyi.shop.controller;

import com.yaroslavkislyi.shop.dto.AddressDTO;
import com.yaroslavkislyi.shop.dto.ClientDTO;
import com.yaroslavkislyi.shop.dto.ProductDTO;
import com.yaroslavkislyi.shop.service.ProductService;
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
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
@Tag(name = "Product", description = "Операции с продуктами")
public class ProductController {
    @Autowired
    private ProductService productService;

    @Operation(
            summary = "Создать продукт",
            description = "Добавляет новый продукт в систему",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    description = "JSON объект с данными адреса",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = AddressDTO.class),
                            examples = {
                                    @ExampleObject(
                                            name = "Пример продукта",
                                            summary = "Пример запроса",
                                            description = "Пример корректного JSON-запроса",
                                            value = """
                                               {
                                                     "name": "apple",
                                                     "category": "fruits",
                                                     "price": 120,
                                                     "availableStock": 1000,
                                                     "lastUpdateDate": "2025-06-06",
                                                     "supplierId": 3,
                                                     "imageId": "f6eb1c4a-ae3a-4020-9745-82429b84e474"
                                               }
                                                    """
                                    )
                            }
                    )
            )
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Продукт добавлен",
                    content = @Content(schema = @Schema(implementation = ClientDTO.class))),
            @ApiResponse(responseCode = "404", description = "Ошибка в запросе")
    })
    @PostMapping("/products")
    public ResponseEntity<?> createProduct(@Valid @RequestBody ProductDTO productDTO, BindingResult result) {
        if (result.hasErrors()) {
            String errorMsg = result.getAllErrors().stream()
                    .map(ObjectError::getDefaultMessage)
                    .collect(Collectors.joining("; "));
            return ResponseEntity.badRequest().body(errorMsg);
        }
        return ResponseEntity.ok(productService.save(productDTO));
    }



    @Operation(
            summary = "Уменьшение количества товара",
            description = "Уменьшает количество товара на введённое пользователем значение."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Количество продукта обновлёно",
                    content = @Content(schema = @Schema(implementation = ClientDTO.class))),
            @ApiResponse(responseCode = "404", description = "Продукт не найден")
    })
    @PutMapping("/products/{id}/{sub}")
    public ResponseEntity<ProductDTO> updateProductAmount(
            @Parameter(description = "ID продукта", example = "1")
            @PathVariable Long id,
            @Parameter(description = "значение, на которое уменьшаем количество товара", example = "100")
            @PathVariable Integer sub) {
        ProductDTO productDTO = productService.updateAvailableStockById(id, sub);
        if (productDTO == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(productDTO);
    }



    @Operation(
            summary = "Получение продукта по ID",
            description = "Возвращает подробную информацию о продукте по его идентификатору."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Продукт получен",
                    content = @Content(schema = @Schema(implementation = ClientDTO.class))),
            @ApiResponse(responseCode = "404", description = "Продукт не найден")
    })
    @GetMapping("/products/{id}")
    public ResponseEntity<ProductDTO> getProduct(@Parameter(description = "ID продукта", example = "1")
                                                     @PathVariable Long id) {
        ProductDTO productDTO = productService.getById(id);
        if (productDTO == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(productDTO);
    }



    @Operation(
            summary = "Получение всех продуктов",
            description = "Возвращает подробную информацию о всех продуктах в базе данных."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Все продукты из базы данных",
                    content = @Content(schema = @Schema(implementation = ClientDTO.class)))
    })
    @GetMapping("/products")
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        return ResponseEntity.ok(productService.getAll());
    }



    @Operation(
            summary = "Удаление продукта по ID",
            description = "Удаляет продукт по его идентификатору."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Продукт удалён",
                    content = @Content(schema = @Schema(implementation = ClientDTO.class))),
            @ApiResponse(responseCode = "404", description = "Продукт не найден")
    })
    @DeleteMapping("/products/{id}")
    public ResponseEntity<ProductDTO> deleteProduct(@PathVariable Long id) {
        ProductDTO productDTO = productService.getById(id);
        if(productDTO != null) {
            productService.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}