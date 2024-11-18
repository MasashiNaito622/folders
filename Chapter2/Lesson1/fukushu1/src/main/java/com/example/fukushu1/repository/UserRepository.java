package com.example.fukushu1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.fukushu1.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // 標準的なCRUDメソッドとクエリメソッドが自動的に提供される
}