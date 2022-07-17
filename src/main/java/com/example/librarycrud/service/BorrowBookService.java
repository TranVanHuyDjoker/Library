package com.example.librarycrud.service;


import com.example.librarycrud.model.dto.BorrowBookDTO;
import com.example.librarycrud.model.dto.BorrowedBook;
import com.example.librarycrud.model.entity.BorrowBook;
import com.example.librarycrud.model.request.BorrowBookRequest;

import java.util.List;

public interface BorrowBookService {
    List<BorrowedBook> getAllBorrowBook(String keyword);

    BorrowBook createReverseBook(BorrowBookRequest borrowBookRequest);

    BorrowBook updateBorrowBook(long id, BorrowBook borrowBook);

    BorrowBookDTO returnBorrowBook(long id);

    BorrowBookDTO getBorrowBookById(long id);

    BorrowBook creatBorrowBook(BorrowBookRequest borrowBookRequest);
}
