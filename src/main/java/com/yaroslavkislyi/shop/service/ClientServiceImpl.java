package com.yaroslavkislyi.shop.service;

import com.yaroslavkislyi.shop.dao.AddressDAO;
import com.yaroslavkislyi.shop.dao.ClientDAO;
import com.yaroslavkislyi.shop.dto.AddressDTO;
import com.yaroslavkislyi.shop.dto.ClientDTO;
import com.yaroslavkislyi.shop.entity.Client;
import com.yaroslavkislyi.shop.mapper.ClientMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {
    @Autowired
    private ClientDAO clientDAO;

    @Autowired
    private AddressDAO addressDAO;

    @Autowired
    private ClientMapper clientMapper;

    @Override
    public ClientDTO save(ClientDTO clientDTO) {
        Client client = clientMapper.toEntity(clientDTO);
        client.setAddress(addressDAO.findById(clientDTO.getAddressId()));
        client = clientDAO.save(client);
        return clientMapper.toDto(client);
    }

    @Override
    public void delete(ClientDTO clientDTO) {
        Client client = clientMapper.toEntity(clientDTO);
        clientDAO.delete(client);
    }

    @Override
    public List<ClientDTO> getByNameAndSurname(String name, String surname) {
        return clientMapper.toDtoList(clientDAO.findAllByNameAndSurname(name, surname));
    }

    @Override
    public List<ClientDTO> getAll(Integer limit, Integer offset) {
        if (limit != null && offset != null) {
            Pageable pageable = PageRequest.of(offset / limit, limit); // offset как смещение по элементам
            return clientDAO.findAll(pageable)
                    .stream()
                    .map(clientMapper::toDto)
                    .toList();
        } else {
            return clientDAO.findAll()
                    .stream()
                    .map(clientMapper::toDto)
                    .toList();
        }
    }

    @Override
    public ClientDTO updateAddressById(Long id, AddressDTO addressDTO) {
        Client client = clientDAO.findById(id);
        if (client == null) {
            return null;
        }
        client.getAddress().setCountry(addressDTO.getCountry());
        client.getAddress().setCity(addressDTO.getCity());
        client.getAddress().setStreet(addressDTO.getStreet());
        return clientMapper.toDto(clientDAO.save(client));
    }

    @Override
    public ClientDTO getById(Long clientId) {
        return clientMapper.toDto(clientDAO.findById(clientId));
    }
}