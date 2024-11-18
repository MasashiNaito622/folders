package com.example.bookmanagementsample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.bookmanagementsample.service.StoreService;
import com.example.bookmanagementsample.service.GenreService;
import com.example.bookmanagementsample.service.BookService;

import jakarta.persistence.EntityNotFoundException;

import com.example.bookmanagementsample.model.Store;
import com.example.bookmanagementsample.model.Book;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.List;

@Controller
@RequestMapping("/stores")
public class StoreController {
    @Autowired
    private StoreService storeService;
    @Autowired
    private BookService bookService;

    @GetMapping
    public String listStores(Model model) {
        List<Store> stores = storeService.findAllStores();
        model.addAttribute("stores", stores);
        return "stores/list";
    }
    @GetMapping("/add")
    public String addStoreForm(Model model) {
        model.addAttribute("store", new Store());
        // Booksデータの全件取得                    エラーのため変えたが↓テキストだとfindAllBooksだった
        model.addAttribute("books", bookService.findAllBook());
        return "stores/add-store";
    }
    @PostMapping("/add")
    public String addStore(Store store, @RequestParam(name = "books") List<Long> books) {
        Set<Book> bookSet = new HashSet<>();
        for (Long bookId : books) {
            bookSet.add(new Book(bookId));
        }
            
        // 上の処理は以下の様に書くとかっこいいです。
        // Set<Book> bookSet = books.stream()
        //     .map(id -> new Book(id))
        //     .collect(Collectors.toSet());
            
        storeService.saveStoreWithBooks(store, bookSet);
        return "redirect:/stores";
    }

    // 書籍の更新フォームを表示
    @GetMapping("/edit/{id}")
    public String showEditStoreForm(@PathVariable(name = "id") Long id, Model model) {
        Store store = storeService.findStoreById(id)
            .orElseThrow(() -> new EntityNotFoundException("Store not found"));
        model.addAttribute("store", store);
        model.addAttribute("allBooks", bookService.findAllBooks());
        return "stores/edit";
    }
    // 書籍の更新処理
    @PostMapping("/edit")
    public String updateStore(Store store, @RequestParam(name = "books") List<Long> books) {
        Set<Book> bookSet = books.stream()
            .map(bookId -> new Book(bookId))
            .collect(Collectors.toSet());
        storeService.updateStoreWithBooks(store, bookSet);
        return "redirect:/stores";
    }
}
