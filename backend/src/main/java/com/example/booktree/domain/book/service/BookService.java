package com.example.booktree.domain.book.service;


import com.example.booktree.domain.book.dto.request.BookRequestDto;
import com.example.booktree.domain.book.entity.Book;
import com.example.booktree.domain.book.repository.BookRepository;
import com.example.booktree.domain.maincategory.entity.MainCategory;
import com.example.booktree.domain.maincategory.repository.MainCategoryRepository;
import com.example.booktree.domain.maincategory.service.MainCategortService;
import com.example.booktree.domain.user.entity.User;
import com.example.booktree.domain.user.service.UserService;
import com.example.booktree.enums.TransactionStatus;
import com.example.booktree.global.exception.BusinessLogicException;
import com.example.booktree.global.exception.ExceptionCode;
import com.example.booktree.global.image.service.ImageService;
import com.example.booktree.global.security.jwt.service.TokenService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class BookService {
    private final BookRepository bookRepository;

    private final TokenService tokenService;
    private final UserService userService;
    private final MainCategoryRepository mainCategoryRepository;
    private final ImageService imageService;

    @Transactional
    public Book createBook(BookRequestDto bookRequestDto){
        User loingUser = userService.findById(tokenService.getIdFromToken());
        MainCategory mainCategory = mainCategoryRepository.findById(bookRequestDto.getMainCategoryId())
                .orElseThrow(()-> new BusinessLogicException(ExceptionCode.CATEGORY_NOT_FOUND));
        String image = imageService.saveUserImage(bookRequestDto.getImage());

        Book book = Book.builder()
                .mainCategory(mainCategory)
                .image(image)
                .transactionStatus(TransactionStatus.AVAILABLE)
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


    public Page<Book> getBookList(Pageable pageable){
        User user = userService.findById(tokenService.getIdFromToken());
        return bookRepository.findAllByUser(user,pageable);
    }




}
