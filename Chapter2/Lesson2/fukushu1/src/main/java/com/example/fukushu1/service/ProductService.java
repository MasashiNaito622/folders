package com.example.fukushu1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.fukushu1.repository.ProductRepository;
import com.example.fukushu1.model.Product;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> findAllProduct(){
        return productRepository.findAll();
    }
    public Product saveProduct(Product product){
        return productRepository.save(product);
    }
    //一件だけ取得する　-----ここから-----
    public Optional<Product> findProductById(Long id){
        return productRepository.findById(id);
    }
    public  Product updateProduct(Product updateProduct){
        return productRepository.save(updateProduct);
    }//-----ここまで-----

    //所品コード(code)で検索する-----ここから-----
    public List<Product> findByCode(String code){
        return productRepository.findByCodeSQL(code);
    }//-----ここまで-----

    //選んだものを削除する
    public void deleteProductById(Long id){
        productRepository.deleteById(id);
    }
}
