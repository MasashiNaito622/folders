package com.example.fukushu3.controller;

import com.example.fukushu3.model.Shop;
import com.example.fukushu3.service.ShopService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import jakarta.persistence.EntityNotFoundException;

@Controller
@RequestMapping("/shops")
public class ShopController {
    @Autowired
    private ShopService shopService;

    @GetMapping()
    public String listShop(Model model){
        List<Shop>shops = shopService.findAllShop();
        model.addAttribute("shops1", shops);
        return "/shops/list";//フォルダの場所の名前を返す
    }

    @GetMapping("/add")
    public String addShopForm(Model model){
        model.addAttribute("shops1", new Shop());
        return "/shops/add";
    }

    @PostMapping("/add")
    public String addshop(Shop shop){
        shopService.saveShop(shop);
        return "redirect:/shops";
    }

    @GetMapping("/edit/{id}")
    //PathVariableはURLに含まれる動的なパスパラメーター(ここだとid)を受け取る
    public String editForm(@PathVariable("id") Long id,Model model){
        Shop shop = shopService.findShopById(id)
        .orElseThrow(() -> new EntityNotFoundException("Not found with id: "+ id));
        model.addAttribute("shopA", shop);
        return "shops/edit";
    }

    @PostMapping("/edit")
    public String editShop(Shop shop){
        shopService.updateShop(shop);
        return "redirect:/shops";
    }

    //店舗名で検索
    @GetMapping("/search")
    public String search(@RequestParam(name = "name",required = false)String name
    ,Model model){
        List<Shop> shops = shopService.findByName(name);
        model.addAttribute("shops", shops);
        return "/shops/search";
    }
    @GetMapping("/delete/{id}")
    public String deleteShops(@PathVariable("id")Long id){
        shopService.deleteShopById(id);
        return "redirect:/shops";
    }
}
