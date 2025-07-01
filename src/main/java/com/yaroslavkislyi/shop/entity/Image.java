package com.yaroslavkislyi.shop.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name = "images")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private UUID id;

    @Column(name = "image")
    private byte[] data;

    public Image() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] image) {
        this.data = image;
    }

    @Override
    public String toString() {
        return "Images{" +
                "id=" + id +
                '}';
    }
}
