package com.example.bookmanagementsample.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.bookmanagementsample.model.Book;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book,Long>{
    //標準的なCRUDメソッドとクエリメソッドが自動的に提供される
    List<Book>findByAuthor(String name);

    // findByAuthorの@Queryバージョン
    @Query(value = "SELECT * FROM books WHERE author = :author", nativeQuery = true)
    List<Book> findByAuthorSQL(@Param("author") String name);
}
