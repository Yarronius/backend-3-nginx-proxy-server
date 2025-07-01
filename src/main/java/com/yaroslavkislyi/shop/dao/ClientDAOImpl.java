package com.yaroslavkislyi.shop.dao;

import com.yaroslavkislyi.shop.entity.Client;
import com.yaroslavkislyi.shop.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class ClientDAOImpl implements ClientDAO {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public Client save(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public void delete(Client client) {
        clientRepository.delete(client);
    }

    @Override
    public List<Client> findAllByNameAndSurname(String name, String surname) {
        return clientRepository.findByNameAndSurname(name, surname);
    }

    @Override
    public List<Client> findAll(Pageable pageable) {
        return clientRepository.findAll(pageable).getContent();
    }

    @Override
    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    @Override
    public Client findById(Long id) {
        return clientRepository.findById(id).orElse(null);
    }
}
