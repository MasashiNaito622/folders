package com.example.fukushu3.repository;

import com.example.fukushu3.model.Shop;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ShopRepository extends JpaRepository<Shop,Long> {
    // 以下全て検索用ロジック
    List<Shop>findByName(String name);//codeはnameに書いてあった
    //findByAuthorの@Queryバージョン -----店名での前方一致検索-----
    @Query(value = "SELECT * FROM shops WHERE name LIKE CONCAT(:name, '%')", nativeQuery = true)
    List<Shop> findByNameSQL(@Param("name") String name);
    // @Query(value = "SELECT* FROM products WHERE code = :code",nativeQuery = true)
    // List<Product>findByCodeSQL(@Param("code")String name);
}
