package com.example.fukushu1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.fukushu1.model.Product;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long>{
    // 以下全て検索用ロジック
    List<Product>findByCode(String name);//codeはnameに書いてあった
    //findByAuthorの@Queryバージョン
    @Query(value = "SELECT* FROM products WHERE code = :code",nativeQuery = true)
    List<Product>findByCodeSQL(@Param("code")String name);
}
