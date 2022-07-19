package com.example.librarycrud.controller;

import com.example.librarycrud.model.dto.BookAfterFilter;
import com.example.librarycrud.model.dto.BookDTO;
import com.example.librarycrud.model.request.BookRequest;
import com.example.librarycrud.service.AuthorService;
import com.example.librarycrud.service.BookService;
import com.example.librarycrud.service.PublisherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping(path = "/api/v1")
public class BookController {
    private final BookService bookService;

    private final PublisherService publisherService;

    private final AuthorService authorService;

    @GetMapping("/books/add")
    public String add(Model model) {
        model.addAttribute("book", new BookRequest());
        model.addAttribute("authors", authorService.getAllAuthor());
        model.addAttribute("publishers", publisherService.getAllPublisher());
        model.addAttribute("creatAt", LocalDate.now());
        return "book/book";
    }

    @GetMapping("/books")
    public String listBook(Model model) {
        List<BookAfterFilter> books = bookService.listAllBook();
        model.addAttribute("books", books);
        return "book/books";
    }

    @PostMapping("/book")
    public ResponseEntity<?> doAdd(@RequestBody @Valid BookRequest bookRequest, BindingResult bindingResult) throws BindException {
        if (bindingResult.hasErrors()) {
            throw new BindException(bindingResult);
        }
        return ResponseEntity.status(HttpStatus.OK).body(bookService.createBook(bookRequest));

    }

    @GetMapping("/books/update")
    public String Update(Model model, @RequestParam Long id) {
        BookDTO book = bookService.getBookById(id);
        model.addAttribute("book", book);
        model.addAttribute("authors", authorService.getAllAuthor());
        model.addAttribute("publishers", publisherService.getAllPublisher());
        return "book/update-book";
    }

    @PostMapping("/books/update")
    public ResponseEntity<?> doUpdate(@RequestParam Long id, @RequestBody @Valid BookRequest bookRequest, BindingResult bindingResult) throws BindException {
        if (bindingResult.hasErrors()) {
            throw new BindException(bindingResult);
        }
        return ResponseEntity.status(HttpStatus.OK).body(bookService.updateBook(id, bookRequest));
    }

    @DeleteMapping("/delete/books/{id}")
    public ResponseEntity<BookDTO> deleteBook(@PathVariable Long id) {
        return ResponseEntity.ok(bookService.deleteBook(id));

    }

}