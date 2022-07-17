package com.example.librarycrud.repository;

import com.example.librarycrud.model.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    Author findAuthorById(Long idAuthor);
}
