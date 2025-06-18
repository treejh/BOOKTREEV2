package com.example.booktree.domain.book.controller;


import com.example.booktree.domain.book.dto.request.BookRequestDto;
import com.example.booktree.domain.book.dto.request.BookUpdateRequestDto;
import com.example.booktree.domain.book.dto.response.BookResponseDto;
import com.example.booktree.domain.book.entity.Book;
import com.example.booktree.domain.book.service.BookService;
import com.example.booktree.global.utils.dto.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
@Tag(name = "책 관리", description = "유저 등록, 수정, 조회, 삭제 및 페이징 조회")
@RequestMapping("/api/v2/book")
public class BookController {

    public final BookService bookService;

    @PostMapping
    @Operation(
            summary = "책 생성 ",
            description = "유저가 보유한 책을 생성합니다. "
    )
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity createBook(@Valid @RequestBody BookRequestDto bookRequestDto) {
        bookService.createBook(bookRequestDto);
        ApiResponse apiResponse = ApiResponse.of(HttpStatus.OK.value(),"책 생성 성공");
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping("/{bookId}")
    @Operation(
            summary = "책 단권 조회 ",
            description = "유저가 보유한 책을 조회 합니다. "
    )
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity getBookById(@Valid @PathVariable Long bookId) {
        BookResponseDto bookResponseDto = new BookResponseDto(bookService.getBookById(bookId));
        ApiResponse apiResponse = ApiResponse.of(HttpStatus.OK.value(),"책 조회 성공",bookResponseDto);
        return ResponseEntity.ok(apiResponse);
    }

    @GetMapping
    @Operation(
            summary = "사용자가 보유한 모든 책 조회  ",
            description = "유저가 보유한 모든 책을 조회 합니다. "
    )
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity getBookListById(        @RequestParam(defaultValue = "1") int page,
                                                  @RequestParam(defaultValue = "10") int size) {
        // ✅ 생성일 기준 내림차순 정렬
        Pageable pageable = PageRequest.of(page-1, size, Sort.by(Sort.Direction.DESC, "createdAt"));
        Page<Book> bookList = bookService.getBookList(pageable);
        Page<BookResponseDto> bookResponseDto = bookList.map(BookResponseDto::new);
        ApiResponse apiResponse = ApiResponse.of(HttpStatus.OK.value(),"모든 책 조회 성공",bookResponseDto);
        return ResponseEntity.ok(apiResponse);
    }












}
