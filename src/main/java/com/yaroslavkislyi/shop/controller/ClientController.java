package com.yaroslavkislyi.shop.controller;

import com.yaroslavkislyi.shop.dto.AddressDTO;
import com.yaroslavkislyi.shop.dto.ClientDTO;
import com.yaroslavkislyi.shop.service.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
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
@Tag(name = "Client", description = "Операции с клиентами")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @Operation(
            summary = "Создать клиента",
            description = "Добавляет нового клиента в систему",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    required = true,
                    description = "JSON объект с данными адреса",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = AddressDTO.class),
                            examples = {
                                    @ExampleObject(
                                            name = "Пример клиента",
                                            summary = "Пример запроса",
                                            description = "Пример корректного JSON-запроса",
                                            value = """
                                           {
                                                "name": "Jessica",
                                                "surname": "Smith",
                                                "birthday": "2004-01-15",
                                                "gender": "female",
                                                "registrationDate": "2025-06-06",
                                                "addressId": 15
                                           }
                                                    """
                                    )
                            }
                    )
            )
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Клиент добавлен",
                    content = @Content(schema = @Schema(implementation = ClientDTO.class))),
            @ApiResponse(responseCode = "400", description = "Неверный формат запроса")
    })
    @PostMapping("/clients")
    public ResponseEntity<?> createClient(@Valid @RequestBody ClientDTO clientDTO,
                                          BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            String errorMsg = bindingResult.getAllErrors().stream()
                    .map(ObjectError::getDefaultMessage)
                    .collect(Collectors.joining("; "));
            return ResponseEntity.badRequest().body(errorMsg);
        }
        return ResponseEntity.ok(clientService.save(clientDTO));
    }



    @Operation(
            summary = "Получение клиента по ID",
            description = "Возвращает подробную информацию о клиенте по его идентификатору."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Клиент удалён",
                    content = @Content(schema = @Schema(implementation = ClientDTO.class))),
            @ApiResponse(responseCode = "404", description = "Клиент не найден")
    })
    @DeleteMapping("/clients/{id}")
    public ResponseEntity<?> deleteClient(@Parameter(description = "ID клиента", example = "1")
                                              @PathVariable Long id) {
        ClientDTO clientDTO = clientService.getById(id);
        if (clientDTO != null) {
            clientService.delete(clientDTO);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }



    @Operation(
            summary = "Получение клиента по имени и фамилии",
            description = "Возвращает всех клиентов с данным именем и фамилией"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Продукт найден",
                    content = @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = ClientDTO.class))
                    )),
            @ApiResponse(responseCode = "404", description = "Продукт не найден")
    })
    @GetMapping("/clients/name&surname/{name}/{surname}")
    public ResponseEntity<List<ClientDTO>> getClientByNameAndSurname(
            @Parameter(description = "имя клиента", example = "Jessica") @PathVariable String name,
            @Parameter(description = "фамилия клиента", example = "Smith") @PathVariable String surname) {
        return ResponseEntity.ok(clientService.getByNameAndSurname(name, surname));
    }



    @Operation(
            summary = "Получение всех клиентов",
            description = "Возвращает подробную информацию о всех клиентах, имеющихся в базе данных. " +
                    "Jпционально принимает параметры пагинации в строке запроса: limit и offset). " +
                    "В случае отсутствия этих параметров возвращает весь список."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Все клиенты из базы данных",
                    content = @Content(schema = @Schema(implementation = ClientDTO.class)))
    })
    @GetMapping("/clients")
    public ResponseEntity<List<ClientDTO>> getAllClients(
            @Parameter(description = "лимит выборки", example = "5") @RequestParam(required = false) Integer limit,
            @Parameter(description = "пропуск строк", example = "3") @RequestParam(required = false) Integer offset) {
        return ResponseEntity.ok(clientService.getAll(limit, offset));
    }



    @Operation(
            summary = "Обновить адрес клиента",
            description = "Обновляет адрес клиента по ID",
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
                                                     "street": "Savelovskaya"
                                                }
                                                    """
                                    )
                            }
                    )
            )
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Клиент обновлён",
                    content = @Content(schema = @Schema(implementation = ClientDTO.class))),
            @ApiResponse(responseCode = "404", description = "Клиент не найден")
    })
    @PutMapping("/clients/{id}")
    public ResponseEntity<?> updateClientAddress(
            @Parameter(description = "ID клиента", example = "1") @PathVariable Long id, @RequestBody AddressDTO addressDTO) {
        if (clientService.updateAddressById(id, addressDTO) == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Клиент не найден");
        }
        return ResponseEntity.ok(clientService.getById(id));}
}
