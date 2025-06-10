package com.example.booktree.domain.book.dto.request;

import com.example.booktree.enums.TransactionStatus;
import com.example.booktree.enums.TransactionType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class BookRequestDto {

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

    private MultipartFile image;

}
