package com.yaroslavkislyi.shop.mapper;

import com.yaroslavkislyi.shop.dto.ClientDTO;
import com.yaroslavkislyi.shop.entity.Client;
import java.util.List;

public interface ClientMapper {
    ClientDTO toDto(Client client);
    Client toEntity(ClientDTO dto);
    List<ClientDTO> toDtoList(List<Client> clients);
}
