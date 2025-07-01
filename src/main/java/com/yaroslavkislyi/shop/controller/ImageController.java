package com.yaroslavkislyi.shop.controller;

import com.yaroslavkislyi.shop.dto.ClientDTO;
import com.yaroslavkislyi.shop.dto.ImageDTO;
import com.yaroslavkislyi.shop.service.ImagesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
@Tag(name = "Images", description = "Операции с мзображениями")
public class ImageController {
    @Autowired
    private ImagesService imagesService;

    @Operation(
            summary = "Добавить изображение в базу данных",
            description = "Добавление изображения в базу данных, принимает массив байт."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Изображение добавлено",
                    content = @Content(schema = @Schema(implementation = ClientDTO.class))),
            @ApiResponse(responseCode = "400", description = "Ошибка в запросе")
    })
    @PostMapping("/images")
    public ResponseEntity<?> addImage(@RequestBody byte[] imageData) {
        if(imageData == null || imageData.length == 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        ImageDTO imageDTO = new ImageDTO();
        imageDTO.setImage(imageData);
        return ResponseEntity.ok(imagesService.save(imageDTO));
    }



    @Operation(
            summary = "Обновить изображение",
            description = "Обновляет изображение в базе данных (принимает массив байт)."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Изображение обновлено",
                    content = @Content(schema = @Schema(implementation = ClientDTO.class))),
            @ApiResponse(responseCode = "404", description = "Изображение не найдено")
    })
    @PutMapping("/images")
    public ResponseEntity<?> updateImage(@Valid @RequestBody ImageDTO image) {
        ImageDTO imageDTO = imagesService.getImageById(image.getId());
        if (imageDTO == null || imageDTO.getImage().length == 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(imagesService.updateImageById(image.getId(), image.getImage()));
    }



    @Operation(
            summary = "Удаление изображения из базы данных",
            description = "Удаляет изображение из базы данных по его идентификатору."
    )
    @DeleteMapping("/images/{id}")
    public ResponseEntity<?> deleteImageById(
            @Parameter(description = "UUID изображения", example = "61d58a2d-0117-459e-b8fe-fb3d0934bcc1")
            @PathVariable UUID id) {
        ImageDTO imageDTO = imagesService.getImageById(id);
        if (imageDTO == null) {
            return ResponseEntity.notFound().build();
        } else {
            imagesService.deleteImageById(id);
            return  ResponseEntity.noContent().build();
        }
    }



    @Operation(
            summary = "Получение изображения по ID продукта",
            description = "Возвращает изображение по ID продукта."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Изображение получено",
                    content = @Content(schema = @Schema(implementation = ClientDTO.class))),
            @ApiResponse(responseCode = "404", description = "Изображение не найдено")
    })
    @GetMapping("/images/product_id/{productId}")
    public ResponseEntity<byte[]> getImageByProductId(@Parameter(description = "ID продукта", example = "1")
                                                          @PathVariable Long productId) {
        byte[] imageData = imagesService.getImageDataByProductId(productId);
        if (imageData == null || imageData.length == 0) {
            return ResponseEntity.notFound().build();
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentLength(imageData.length);
        headers.setContentDisposition(ContentDisposition
                .attachment()
                .filename("product-image.png")
                .build());
        return new ResponseEntity<>(imageData, headers, HttpStatus.OK);
    }



    @Operation(
            summary = "Получение изображения по его ID",
            description = "Возвращает изображение по его ID."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Изображение получено",
                    content = @Content(schema = @Schema(implementation = ClientDTO.class))),
            @ApiResponse(responseCode = "404", description = "Изображение не найдено")
    })
    @GetMapping("/images/uuid/{id}")
    public ResponseEntity<byte[]> getImageById(
            @Parameter(description = "UUID изображения", example = "61d58a2d-0117-459e-b8fe-fb3d0934bcc1")
            @PathVariable UUID id) {
        byte[] imageData = imagesService.getImageDataById(id);

        if (imageData == null || imageData.length == 0) {
            return ResponseEntity.notFound().build();
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentLength(imageData.length);
        headers.setContentDisposition(ContentDisposition
                .attachment()
                .filename("product-image.png")
                .build());

        return new ResponseEntity<>(imageData, headers, HttpStatus.OK);
    }
}
