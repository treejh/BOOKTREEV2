package com.example.booktree.domain.book.controller;


import com.example.booktree.domain.book.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BookController {

    public final BookService bookService;

}
