package com.example.dbappsample.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dbappsample.model.Product;
import com.example.dbappsample.repository.ProductRepository;

@Service
public class ProductService {
    // ProductRepositoryはServiceで定義する
    @Autowired
    private ProductRepository productRepository;

    // findAll()はServiceから呼ぶ(メソッド名は何でもよい)
    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }
		
    // save(product)もServiceから呼ぶ(メソッド名は何でもよい)
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }
}