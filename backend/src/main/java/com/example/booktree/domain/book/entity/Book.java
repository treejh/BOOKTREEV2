package com.example.booktree.domain.book.entity;

import com.example.booktree.domain.maincategory.entity.MainCategory;
import com.example.booktree.domain.user.entity.User;
import com.example.booktree.enums.TransactionStatus;
import com.example.booktree.enums.TransactionType;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name="books")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@ToString
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(nullable = false, length = 100)
    private String name;

    @NotBlank
    @Column(nullable = false, length = 100)
    private String author;

    @NotBlank
    @Column
    private String image;


    @Enumerated(EnumType.STRING)
    @Column(name = "transcation_type",nullable = false)
    private TransactionType transactionType; // Enum 타입이 더 적합할 수 있음

    @Enumerated(EnumType.STRING)
    @Column(name="transcation_status",nullable = false)
    private TransactionStatus transactionStatus;


    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER) //수정
    @JoinColumn(name = "main_category_id", nullable = false)
    private MainCategory mainCategory;



}
