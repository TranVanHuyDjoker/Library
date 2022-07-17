package com.example.librarycrud.repository;

import com.example.librarycrud.model.entity.AuthorBook;

import com.example.librarycrud.model.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AuthorBookRepository extends JpaRepository<AuthorBook, Long> {
    List<AuthorBook> findByBook(Book book);
}
