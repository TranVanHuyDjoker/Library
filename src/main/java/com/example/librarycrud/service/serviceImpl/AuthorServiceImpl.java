package com.example.librarycrud.service.serviceImpl;


import com.example.librarycrud.model.dto.AuthorDTO;
import com.example.librarycrud.model.entity.Author;
import com.example.librarycrud.repository.AuthorRepository;
import com.example.librarycrud.service.AuthorService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorServiceImpl implements AuthorService {
    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private ModelMapper mapper;

    @Override
    public List<AuthorDTO> getAllAuthor() {
        return authorRepository.findAll().stream()
                .map(author -> mapper.map(author, AuthorDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public Author creatAuthor(Author author) {
        return authorRepository.save(author);
    }

    @Override
    public Author updateAuthor(long id, Author author) {
        return null;
    }

    @Override
    public void deleteAuthor(long id) {}

    @Override
    public Author getAuthorById() {
        return null;
    }
}
