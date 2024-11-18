package com.example.fukushu1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.fukushu1.service.ProductService;

import jakarta.persistence.EntityNotFoundException;

import com.example.fukushu1.model.Product;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping()
    public String listProduct(Model model){
        List<Product>products = productService.findAllProduct();
        model.addAttribute("products1", products);
        return "/products/list";//フォルダーの場所の名前を返す
    }

    @GetMapping("/add")
    public String addproductForm(Model model){
        model.addAttribute("product", new Product());
        return "/products/add";//フォルダーの場所の名前に返す
    }

    @PostMapping("/add")
    public String addproduct(Product product){
        productService.saveProduct(product);
        return "redirect:/products";
    }

    @GetMapping("/edit/{id}")
    //PathVariableはURLに含まれる動的なパスパラメーター(ここだとid)を受け取る
    public String editForm(@PathVariable("id") Long id,Model model){
        Product product = productService.findProductById(id)
        .orElseThrow(() -> new EntityNotFoundException("Not found with id: "+ id));
        model.addAttribute("productA", product);
        return "products/edit";
    }

    @PostMapping("/edit")
    public String editProduct(Product product){
        productService.updateProduct(product);
        return "redirect:/products";
    }
    //商品コードで検索
    @GetMapping("/search")
    public String search(@RequestParam(name = "code",required = false)String code
    ,Model model){
        List<Product> products = productService.findByCode(code);
        model.addAttribute("products", products);
        return "/products/search";
    }
    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id")Long id){
        productService.deleteProductById(id);
        return "redirect:/products";
    }
}
