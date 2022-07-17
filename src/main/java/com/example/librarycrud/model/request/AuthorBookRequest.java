package com.example.librarycrud.model.request;


import com.example.librarycrud.model.entity.Author;
import com.example.librarycrud.model.entity.Book;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthorBookRequest implements Serializable {
    private Author author;

    private Book book;
}
