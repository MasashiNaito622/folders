package com.example.fukushu1.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.fukushu1.repository.CompanyRepository;

import jakarta.persistence.EntityNotFoundException;

import com.example.fukushu1.model.Company;
import com.example.fukushu1.model.Employee;

//Optional型を使うためのimport
//Optionalってなんだっけ？→値が存在するかどうか確認し、存在したらその値をなかったら
//Nullの代わりに空のOptionalオブジェクトを返す　NullのエラーNullPointexceptionを防ぐ
import java.util.Optional;

@Service
public class CompanyService {
    @Autowired
    private CompanyRepository companyRepository;

    public List<Company>findAllCompany(){
        return companyRepository.findAll();
    }
    public Company saveCompany(Company company){
        return companyRepository.save(company);
    }
    //1件だけ取得する
    public Optional<Company> findCompanyById(Long id){
        return companyRepository.findById(id);
    }
    public Company updateCompany(Company updatedCompany) {
        return companyRepository.save(updatedCompany);
    }
    public void deleteCompanyById(Long id) {
        Company company = companyRepository.findById(id)
        .orElseThrow(() -> new EntityNotFoundException("Employee not found with id: " + id));
        company.setDeleted(true);
        companyRepository.save(company);
    }
    public List<Company> findByCompanyName(String name) {
        return companyRepository.findActiveCompanies(name);
    }
}
