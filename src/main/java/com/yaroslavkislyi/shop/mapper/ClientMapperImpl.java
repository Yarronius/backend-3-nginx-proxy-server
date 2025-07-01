package com.yaroslavkislyi.shop.mapper;

import com.yaroslavkislyi.shop.dto.AddressDTO;
import com.yaroslavkislyi.shop.dto.ClientDTO;
import com.yaroslavkislyi.shop.entity.Client;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class ClientMapperImpl implements ClientMapper {

    public ClientDTO toDto(Client client) {
        if (client == null) return null;

        ClientDTO dto = new ClientDTO();
        dto.setId(client.getId());
        dto.setName(client.getName());
        dto.setSurname(client.getSurname());
        dto.setBirthday(client.getBirthday());
        dto.setGender(client.getGender());
        dto.setRegistrationDate(client.getRegistrationDate());

        AddressDTO address = new AddressDTO();
        address.setId(client.getAddress().getId());
        address.setCountry(client.getAddress().getCountry());
        address.setCity(client.getAddress().getCity());
        address.setStreet(client.getAddress().getStreet());

        dto.setAddressId(address.getId());

        return dto;
    }

    public Client toEntity(ClientDTO dto) {
        if (dto == null) return null;

        Client client = new Client();
        client.setId(dto.getId());
        client.setName(dto.getName());
        client.setSurname(dto.getSurname());
        client.setBirthday(dto.getBirthday());
        client.setGender(dto.getGender());
        client.setRegistrationDate(dto.getRegistrationDate());

        return client;
    }

    public List<ClientDTO> toDtoList(List<Client> clients) {
        if (clients == null) return List.of();
        return clients.stream().map(this::toDto).toList();
    }
}
