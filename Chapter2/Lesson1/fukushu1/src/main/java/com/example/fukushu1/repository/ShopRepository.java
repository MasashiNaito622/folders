package com.example.fukushu1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.fukushu1.model.Shop;

@Repository
public interface ShopRepository extends JpaRepository<Shop, Long>{
    //標準的なCRUDメソッドとクエリメソッドが自動的に提供される
}
