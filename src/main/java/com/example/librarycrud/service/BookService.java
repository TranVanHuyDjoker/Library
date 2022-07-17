package com.example.librarycrud.service;



import com.example.librarycrud.model.dto.BookAfterFilter;
import com.example.librarycrud.model.dto.BookDTO;
import com.example.librarycrud.model.request.BookRequest;

import java.util.List;

public interface BookService {
    List<BookDTO> getAllBook();

    BookDTO createBook(BookRequest bookRequest);

    List<BookAfterFilter> listAllBook();

//    List<BookAfterFilter> searchBook(SearchBookForm request);

    BookDTO updateBook(long id, BookRequest bookRequest);

    BookDTO deleteBook(long id);

//    boolean existById(Long id);

    BookDTO getBookById(long id);

    List<BookDTO> getAllBookAvailable();
}
