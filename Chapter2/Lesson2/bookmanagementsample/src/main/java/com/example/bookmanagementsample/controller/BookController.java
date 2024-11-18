package com.example.bookmanagementsample.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.bookmanagementsample.service.BookService;

import jakarta.persistence.EntityNotFoundException;

import com.example.bookmanagementsample.model.Book;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping("/list")
    public String listBook(Model model){
        List<Book>books = bookService.findAllBook();
        model.addAttribute("books1", books);
        return "books/list";//ビュー(books/list.html)の名前を返す
    }
    @GetMapping("/add")
    public String addBookForm(Model model){
        model.addAttribute("book", new Book());
        return "books/add";
    }
    @PostMapping("/add")
    public String addBook(Book book){
        bookService.saveBook(book);
        return "redirect:/books/list";
    }

    @GetMapping("/edit/{id}")
    public String editBookForm(@PathVariable("id") Long id, Model model) {
        // 以下でもいいがテキストで簡潔かつ高度な書き方に変更
        // Optional<Book> optionalBook = bookService.findBookById(id);
        // if (optionalBook.isPresent()) {
        //     // 値が存在する場合の処理
        //     Book book = optionalBook.get();
        //     model.addAttribute("book", book);
        //     return "books/edit";
        // } else {
        //     // 値が存在しない場合の処理
        //     //何もしない
        //     // エラー画面へ飛ばしたり、エラーの記録をしたり
        // }
        // return "redirect:/books";

        //以下変更後
        Book book = bookService.findBookById(id)
        .orElseThrow(() -> new EntityNotFoundException("Book not found with id: " + id));
        model.addAttribute("book", book);
        return "books/edit";
    }
    @PostMapping("/edit")
    public String editBook(Book book) {
        bookService.updateBook(book);
        return "redirect:/books/list";
    }
    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable("id") Long id) {
        bookService.deleteBookById(id);
        return "redirect:/books/list";
    }
    @GetMapping("/search")
    public String search(
            @RequestParam(name = "author", required = false) String author,
            Model model) {
        List<Book> books = bookService.findByAuthor(author);
        model.addAttribute("books", books);
        return "/books/search";
    }

}
