package com.example.dbappsample.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.dbappsample.model.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long>{
    // 標準的なCRUDメソッドとクエリメソッドが自動的に提供される
}
