package com.example.booktree.domain.book.service;


import com.example.booktree.domain.book.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookService {
    public final BookRepository bookRepository;


}
