package com.example.fukushu3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.fukushu3.repository.ShopRepository;
import com.example.fukushu3.model.Shop;

import java.util.List;
import java.util.Optional;

@Service
public class ShopService {
    @Autowired
    private ShopRepository shopRepository;

    public List<Shop>findAllShop(){
        return shopRepository.findAll();
    }
    public Shop saveShop(Shop shop){
        return shopRepository.save(shop);
    }
    //一件だけ取得する　-----ここから-----
    public Optional<Shop> findShopById(Long id){
        return shopRepository.findById(id);
    }
    public Shop updateShop(Shop updateShop){
        return shopRepository.save(updateShop);
    }//-----ここまで-----

    //店名(name)で検索する-----ここから-----
    public List<Shop> findByName(String name){
        return shopRepository.findByNameSQL(name);
    }//-----ここまで-----

    //選んだものを削除する
    public void deleteShopById(Long id){
        shopRepository.deleteById(id);
    }
}
