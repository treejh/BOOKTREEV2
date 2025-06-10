package com.example.booktree.domain.book.service;


import com.example.booktree.domain.book.dto.request.BookRequestDto;
import com.example.booktree.domain.book.entity.Book;
import com.example.booktree.domain.book.repository.BookRepository;
import com.example.booktree.domain.maincategory.entity.MainCategory;
import com.example.booktree.domain.maincategory.repository.MainCategoryRepository;
import com.example.booktree.domain.maincategory.service.MainCategortService;
import com.example.booktree.domain.user.entity.User;
import com.example.booktree.domain.user.service.UserService;
import com.example.booktree.global.exception.BusinessLogicException;
import com.example.booktree.global.exception.ExceptionCode;
import com.example.booktree.global.image.service.ImageService;
import com.example.booktree.global.security.jwt.service.TokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

    private final TokenService tokenService;
    private final UserService userService;
    private final MainCategoryRepository mainCategoryRepository;
    private final ImageService imageService;

    public Book createBook(BookRequestDto bookRequestDto){
        User loingUser = userService.findById(tokenService.getIdFromToken());
        MainCategory mainCategory = mainCategoryRepository.findById(bookRequestDto.getMainCategoryId())
                .orElseThrow(()-> new BusinessLogicException(ExceptionCode.CATEGORY_NOT_FOUND));
        String image = imageService.saveUserImage(bookRequestDto.getImage());

        Book book = Book.builder()
                .mainCategory(mainCategory)
                .image(image)
                .transactionStatus(bookRequestDto.getTransactionStatus())
                .transactionType(bookRequestDto.getTransactionType())
                .user(loingUser)
                .name(bookRequestDto.getName())
                .author(bookRequestDto.getAuthor())
                .build();
        bookRepository.save(book);
        return book;
    }

    public Book getBookById(Long bookId){
        return bookRepository.findById(bookId)
                .orElseThrow(()->new BusinessLogicException(ExceptionCode.BOOK_NOT_FOUND));
    }


}
