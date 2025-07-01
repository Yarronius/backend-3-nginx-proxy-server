package com.yaroslavkislyi.shop.repository;

import com.yaroslavkislyi.shop.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
