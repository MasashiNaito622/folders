package com.example.fukushu1.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.fukushu1.model.Company;


@Repository
public interface CompanyRepository extends JpaRepository<Company,Long>{
    // @Query("SELECT c FROM Company c WHERE c.isDeleted = false")
    // List<Company> findActiveCompanies(@Param("name") String name);

    @Query(value = "SELECT* FROM companys WHERE company_name = :code",nativeQuery = true)
    List<Company> findActiveCompanies(@Param("code")String name);
}
