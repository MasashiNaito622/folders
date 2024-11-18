package com.example.fukushu1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.fukushu1.service.ShopService;
import com.example.fukushu1.model.Shop;

@Controller
@RequestMapping("/shops")
public class ShopController {
    @Autowired
    private ShopService shopService;

    @GetMapping
    public String listShops(Model model){
        List<Shop>shops = shopService.findAllShop();
        model.addAttribute("shops1",shops);
        return "shops";//ビューの名前を返す 1⃣
    }
    @GetMapping("/add")
    public String addShopForm(Model model){
        model.addAttribute("shop", new Shop());
        return "add-shop";//ユーザー追加フォームのビュー
    }
    @PostMapping("/add")
    public String addShop(Shop shop){
        shopService.saveShop(shop);
        return "redirect:/shops";//1⃣の部分に返す
    }
}
