package com.yaroslavkislyi.shop.service;

import com.yaroslavkislyi.shop.dto.AddressDTO;
import com.yaroslavkislyi.shop.dto.ClientDTO;
import java.util.List;

public interface ClientService {
    ClientDTO save(ClientDTO clientDTO);
    void delete(ClientDTO clientDTO);
    List<ClientDTO> getByNameAndSurname(String name, String surname);
    List<ClientDTO> getAll(Integer limit, Integer offset);
    ClientDTO updateAddressById(Long id, AddressDTO addressDTO);
    ClientDTO getById(Long clientId);
}