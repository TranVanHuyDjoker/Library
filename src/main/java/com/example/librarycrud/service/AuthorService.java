package com.example.librarycrud.service;



import com.example.librarycrud.model.dto.AuthorDTO;
import com.example.librarycrud.model.entity.Author;

import java.util.List;

public interface AuthorService {
    List<AuthorDTO> getAllAuthor();

    Author creatAuthor(Author author);

    Author updateAuthor(long id,Author author);

    void deleteAuthor(long id);

    Author getAuthorById();
}
