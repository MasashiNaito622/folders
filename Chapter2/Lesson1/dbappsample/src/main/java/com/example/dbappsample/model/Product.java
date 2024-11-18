package com.example.dbappsample.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "products") // テーブル名は複数形にしましょう
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    // 名前は重複禁止(unique = true)
    // nameカラムはnull禁止(nullable = false)
    @Column(nullable = false, unique = true)
    private String name;
		
    // ここでは@Columnとしているが
    // null禁止も重複禁止もしないなら@Columnアノテーションを省略できる
    @Column
    private int price;

    // 処理の無いコンストラクタは必ず書く
    public Product(){
    }

    // 全パラメータのコンストラクタは必須ではないが書いておく
    public Product(Long id, String name, int price){
        this.id = id;
        this.name = name;
        this.price = price;
    }
    //getter setter

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

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
    
}