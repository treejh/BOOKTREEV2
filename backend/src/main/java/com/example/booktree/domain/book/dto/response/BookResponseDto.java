package com.example.booktree.domain.book.dto.response;

import com.example.booktree.domain.book.entity.Book;
import com.example.booktree.enums.TransactionStatus;
import com.example.booktree.enums.TransactionType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class BookResponseDto {

    @NotNull
    private Long mainCategoryId;

    @NotBlank
    @NotNull
    private String name;

    @NotNull
    private String author;

    @NotNull
    private TransactionStatus transactionStatus;

    @NotNull
    private TransactionType transactionType;

    private String image;

    public BookResponseDto(Book book){
        this.mainCategoryId = book.getMainCategory().getId();
        this.name = book.getName();
        this.author = book.getAuthor();
        this.transactionStatus = book.getTransactionStatus();
        this.transactionType = book.getTransactionType();
        this.image = book.getImage();
    }

}
