package com.example.booktree.domain.book.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.booktree.domain.book.dto.request.BookRequestDto;
import com.example.booktree.domain.book.entity.Book;
import com.example.booktree.domain.book.repository.BookRepository;
import com.example.booktree.domain.book.service.BookService;
import com.example.booktree.domain.maincategory.entity.MainCategory;
import com.example.booktree.domain.maincategory.repository.MainCategoryRepository;
import com.example.booktree.domain.user.entity.User;
import com.example.booktree.domain.user.service.UserService;
import com.example.booktree.enums.TransactionStatus;
import com.example.booktree.enums.TransactionType;
import com.example.booktree.global.exception.BusinessLogicException;
import com.example.booktree.global.exception.ExceptionCode;
import com.example.booktree.global.image.service.ImageService;
import com.example.booktree.global.security.jwt.service.TokenService;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;


@ExtendWith(MockitoExtension.class)
public class BookServiceTest {
    @InjectMocks
    private BookService bookService;

    @Mock
    private UserService userService;

    @Mock
    private TokenService tokenService;

    @Mock
    private MainCategoryRepository mainCategoryRepository;

    @Mock
    private ImageService imageService;

    @Mock
    private BookRepository bookRepository;

    @Test
    @DisplayName("사용자가 보유한 책 생성")
    void createBook_shouldCreateBookSuccessfully(){
        //given 테스트에 사용할 더미데이터 생성
        Long userIdFromToken = 1L;
        Long mainCategoryId = 1L;
        User mockUser = new User();
        MainCategory mockCategory = new MainCategory();
        BookRequestDto dto = new BookRequestDto();
        dto.setMainCategoryId(1L);
        dto.setName("테스트 책 제목");
        dto.setAuthor("테스트 저자");
        dto.setTransactionType(TransactionType.SELL);

        // 이미지 파일은 MultipartFile이라서 보통 MockMultipartFile로 만듦
        MockMultipartFile mockImage = new MockMultipartFile(
                "image",
                "test-image.jpg",
                "image/jpeg",
                "dummy image content".getBytes()
        );
        dto.setImage(mockImage);


        // mocking
        when(tokenService.getIdFromToken()).thenReturn(userIdFromToken);
        when(userService.findById(userIdFromToken)).thenReturn(mockUser);
        when(mainCategoryRepository.findById(mainCategoryId)).thenReturn(Optional.of(mockCategory));
        when(imageService.saveUserImage(mockImage)).thenReturn("savedImageUrl");

        //when 테스트 대상 메서드 호출
        Book book = bookService.createBook(dto);

        //then
        assertNotNull(book);
        assertEquals(mockUser, book.getUser());
        assertEquals(mockCategory, book.getMainCategory());
        assertEquals("savedImageUrl", book.getImage());
        assertEquals("테스트 책 제목", book.getName());
        assertEquals("테스트 저자", book.getAuthor());
        assertEquals(TransactionStatus.AVAILABLE, book.getTransactionStatus());
        assertEquals(TransactionType.SELL, book.getTransactionType());

        //정확히 1번 호출되었는지 확인
        verify(bookRepository, times(1)).save(any(Book.class));
    }


    @Test
    @DisplayName("책 ID로 책을 정상적으로 조회한다")
    void getBookById_shouldReturnBook() {
        // given
        Long bookId = 1L;
        Book book = new Book();
        book.setId(bookId);
        book.setName("테스트 책");

        when(bookRepository.findById(bookId)).thenReturn(Optional.of(book));

        // when
        Book result = bookService.getBookById(bookId);

        // then
        assertNotNull(result);
        assertEquals(bookId, result.getId());
        assertEquals("테스트 책", result.getName());
    }

    @Test
    @DisplayName("책 ID로 조회했을 때 존재하지 않으면 예외를 던진다")
    void getBookById_shouldThrowException_whenBookNotFound() {
        // given
        Long bookId = 999L;
        when(bookRepository.findById(bookId)).thenReturn(Optional.empty());

        // when & then
        BusinessLogicException exception = assertThrows(BusinessLogicException.class, () -> {
            bookService.getBookById(bookId);
        });

        assertEquals(ExceptionCode.BOOK_NOT_FOUND, exception.getExceptionCode());
    }





}
