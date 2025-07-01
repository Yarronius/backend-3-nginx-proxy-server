package com.yaroslavkislyi.shop.dao;

import com.yaroslavkislyi.shop.entity.Client;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface ClientDAO {
    Client save(Client client);
    void delete(Client client);
    List<Client> findAllByNameAndSurname(String firstName, String lastName);
    List<Client> findAll(Pageable pageable);
    List<Client> findAll();
    Client findById(Long id);
}