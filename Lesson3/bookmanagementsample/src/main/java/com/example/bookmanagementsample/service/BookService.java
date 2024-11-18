package com.example.bookmanagementsample.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.bookmanagementsample.repository.BookRepository;
import com.example.bookmanagementsample.model.Book;
//Optional型を使うためのimport
//Optionalってなんだっけ？→値が存在するかどうか確認し、存在したらその値をなかったら
//Nullの代わりに空のOptionalオブジェクトを返す　NullのエラーNullPointexceptionを防ぐ
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public List<Book>findAllBook(){
        return bookRepository.findAll();
    }
    public Book saveBook(Book book){
        return bookRepository.save(book);
    }
    //1件だけ取得する
    public Optional<Book> findBookById(Long id){
        return bookRepository.findById(id);
    }
    public Book updateBook(Book updatedBook) {
        return bookRepository.save(updatedBook);
    }
    public void deleteBookById(Long id) {
        bookRepository.deleteById(id);
    }
    public List<Book> findByAuthor(String author) {
        return bookRepository.findByAuthorSQL(author);
    }
}
