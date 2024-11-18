package com.example.dbappsample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.dbappsample.model.Product;
import com.example.dbappsample.repository.ProductRepository;
import com.example.dbappsample.service.ProductService;
import java.util.List;

@Controller
@RequestMapping("/products")
// public class ProductController {
//     @Autowired
//     private ProductRepository productRepository;
//     @GetMapping("/add")
//     public String addProductForm(Model model) {
//         model.addAttribute("product", new Product());
//         return "add-product";
//     }

//     @PostMapping("/add")
//     public String addProduct(Product product) {
//         // ここにRepositoryのメソッドを呼ぶ処理を書く
//         productRepository.save(product);
//         // 商品登録フォームにリダイレクト
//         return "redirect:/products/add";
//     }

//     @GetMapping
//     public String listProducts(Model model) {
//         // ここのリストにデータが受け取れれば良い
//         List<Product> products = null;
//         model.addAttribute("products", products);
//         return "products";
//     }
public class ProductController {
    // productServiceに書き変え
    @Autowired
    private ProductService productService;

    @GetMapping
    public String listProducts(Model model) {
        // Service側のメソッドを呼ぶ処理に書き変え
        List<Product> products = productService.findAllProducts();
        model.addAttribute("products", products);
        return "products";
    }

    @GetMapping("/add")
    public String addProductForm(Model model) {
        model.addAttribute("product", new Product());
        return "add-product";
    }

    @PostMapping("/add")
    public String addProduct(Product product) {
        // Service側のメソッドを呼ぶ処理に書き変え
        productService.saveProduct(product);
        return "redirect:/products/add";
    }
}
