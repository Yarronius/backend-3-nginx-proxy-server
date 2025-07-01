package com.yaroslavkislyi.shop.repository;

import com.yaroslavkislyi.shop.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface ImagesRepository extends JpaRepository<Image, UUID> {

}
