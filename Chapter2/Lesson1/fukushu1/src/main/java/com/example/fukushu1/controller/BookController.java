package com.example.fukushu1.controller;

import org.springframework.stereotype.Controller;

import com.example.fukushu1.service.BookService;
import java.util.List;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.fukushu1.model.Book;

@Controller
@RequestMapping("/books")
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping
    public String listBooks(Model model){
        List<Book>books = bookService.findAllBook();
        model.addAttribute("books",books);
        return "books";//ビュー(books.html)の名前を返す
    }

    @GetMapping("/add")
    public String addBookForm(Model model){
        model.addAttribute("book",new Book());
        return "add-book";//ユーザー追加フォームのビュー
    }
    @PostMapping("/add")
    public String addBook(Book book){
        bookService.saveBook(book);
        return "redirect:/books";//登録完了時には一覧へリダイレクトする
    }
}
