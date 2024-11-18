package com.example.fukushu1.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDate;


@Entity
@Table(name="products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;//商品名

    @Column(nullable = false, unique=true)
    private String code;//商品コード

    @Column(nullable = false)
    private LocalDate releaseDate;//発売日

    @Column(nullable = false)
    private int price;//価格

    @Column(nullable = false)
    private boolean isDeleted;//削除フラグ

    public Product(){

    }

    public Product(Long id, String name, String code, LocalDate releaseDate, int price, boolean isDeleted) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.releaseDate = releaseDate;
        this.price = price;
        this.isDeleted = isDeleted;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }
    

}
