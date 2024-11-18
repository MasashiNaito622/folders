package com.example.bookmanagementsample.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;


import java.time.LocalDate;
import java.util.Set;

import org.hibernate.annotations.SQLRestriction;

@Entity
@SQLRestriction("is_deleted = false")
@Table(name="books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false, unique=true)
    private String isbn;

    @Column
    private LocalDate publishedDate;

    @Column
    private boolean isDeleted = false;

    @ManyToOne//書籍が１つずつのジャンルを持っているという意味を持たせることができる
    @JoinColumn(name = "genre_id",nullable = false)//joinColumnはManyToOneとセットで使う
    //またgenre_idにnullを許可するという意味
    private Genre genre;//ここの変数名がGenre.javaのmappedBy="genre"と紐づいている

    // mappedByのbooksはテーブル名ではなく、Storeのプロパティ名books
    @ManyToMany(mappedBy = "books")
    private Set<Store> stores;//Book.javaに紐づいている

    public Book(){

    }

    public Book(Long id, String title, String author, String isbn, LocalDate publishedDate,boolean isDeleted,Genre genre) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.publishedDate = publishedDate;
        this.isDeleted = isDeleted;
        this.genre = genre;
    }

    // IDのみでインスタンス化出来るコンストラクタ
    public Book(Long id){
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public LocalDate getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(LocalDate publishedDate) {
        this.publishedDate = publishedDate;
    }

    // boolean型(isXXXX)のGetterはgetXXXXではなくisXXXXが習慣的
    public boolean isDeleted() {
        return isDeleted;
    }

    // boolean型(isXXXX)のSetterはsetIsDeletedではなく、setXXXXが習慣的
    public void setDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }
}
