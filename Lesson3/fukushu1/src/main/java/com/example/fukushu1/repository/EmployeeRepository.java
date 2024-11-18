package com.example.fukushu1.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.fukushu1.model.Company;
import com.example.fukushu1.model.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long>{
    @Query(value = "SELECT* FROM employees WHERE name = :code",nativeQuery = true)
    List<Employee> findActiveEmployee(@Param("code")String name);
}
