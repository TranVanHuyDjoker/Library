package com.example.librarycrud.service.serviceImpl;


import com.example.librarycrud.config.exception.BadRequestException;
import com.example.librarycrud.model.dto.BorrowBookDTO;
import com.example.librarycrud.model.dto.BorrowedBook;
import com.example.librarycrud.model.entity.BorrowBook;
import com.example.librarycrud.model.request.BorrowBookRequest;
import com.example.librarycrud.repository.BookRepository;
import com.example.librarycrud.repository.BorrowBookRepository;
import com.example.librarycrud.repository.MemberRepository;
import com.example.librarycrud.service.BorrowBookService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BorrowBookServiceImpl implements BorrowBookService {
    @Autowired
    private BorrowBookRepository borrowBookRepository;

    @Autowired
    private ModelMapper mapper;


    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Override
    public List<BorrowedBook> getAllBorrowBook(String keyword) {
        List<BorrowedBook> borrowedBookList = borrowBookRepository.searchByTitle(keyword);
        return borrowedBookList;
    }

    @Override
    public BorrowBook createReverseBook(BorrowBookRequest borrowBookRequest) {
//        LocalDate monday = DateUtils.getMonDayOfTheWeek();
//        LocalDate sunday = DateUtils.getSunDayOfTheWeek();
//        Member member = memberRepository.findById(borrowBookRequest.getMemberID()).orElse(null);
//        int numberBorrowBookOfWeek = borrowBookRepository.numberBorrowBookOfWeek(monday, sunday, member);
//        if (numberBorrowBookOfWeek >= 3) {
//            throw new BadRequestException("Bạn đã mượn 3 quyển trong tuần!");
//        }
//
//        Book book = bookRepository.findById(borrowBookRequest.getBookID()).orElse(null);
//
//        BorrowBook borrowBook = new BorrowBook();
//        borrowBook.setBook(book);
//        borrowBook.setMember(member);
//        borrowBook.setCreateDate(borrowBookRequest.getCreateDate());
//        borrowBook.setReturnDate(borrowBookRequest.getReturnDate());
//        borrowBookRepository.save(borrowBook);
//        return borrowBook;
        return  null;
    }

    @Override
    public BorrowBook updateBorrowBook(long id, BorrowBook borrowBook) {
        return null;
    }

    @Override
    public BorrowBookDTO returnBorrowBook(long id) {

        return null;
    }

    @Override
    public BorrowBookDTO getBorrowBookById(long id) {
        BorrowBook borrowBook = borrowBookRepository.findById(id).orElseThrow(() -> new BadRequestException("BorrowBook not found"));
        return mapper.map(borrowBook, BorrowBookDTO.class);
    }

    @Override
    public BorrowBook creatBorrowBook(BorrowBookRequest borrowBookRequest) {
//        LocalDate monday = DateUtils.getMonDayOfTheWeek();
//        LocalDate sunday = DateUtils.getSunDayOfTheWeek();
//        Member member = memberRepository.findById(borrowBookRequest.getMemberID()).orElse(null);
//        int numberBorrowBookOfWeek = borrowBookRepository.numberBorrowBookOfWeek(monday,sunday,member);
//        if(numberBorrowBookOfWeek >= 3){
//            throw new BadRequestException("Bạn đã mượn tối đa 3 quyển sách trong tuần này");
//        }
//        Book book = bookRepository.findById(borrowBookRequest.getBookID()).orElse(null);
//        BorrowBook borrowBook = new BorrowBook();
//        borrowBook.setBook(book);
//        borrowBook.setMember(member);
//        borrowBook.setCreateDate(borrowBookRequest.getCreateDate());
//        borrowBook.setReturnDate(borrowBookRequest.getReturnDate());
//        borrowBookRepository.save(borrowBook);
//        return borrowBook;
        return null;
    }
}
