package com.example.booktree.domain.post.entity;

import com.example.booktree.global.auditable.Auditable;
import com.example.booktree.domain.blog.entity.Blog;
import com.example.booktree.domain.category.entity.Category;
import com.example.booktree.global.image.entity.Image;
import com.example.booktree.domain.maincategory.entity.MainCategory;
import com.example.booktree.domain.user.entity.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name="posts")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@ToString
public class Post extends Auditable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @ManyToOne(fetch = FetchType.EAGER) //수정
    @JoinColumn(name = "main_category_id", nullable = false)
    private MainCategory mainCategory;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "blog_id", nullable = false)
    private Blog blog; //블로그아이디

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private User user; //회원아이디

    @NotBlank
    @Column(length = 50, nullable = false)
    private String title;

    // @Lob
    //@NotBlank
    @Column(nullable = false, columnDefinition = "LONGTEXT")
    private String content;

    @Column(length = 100)
    private String author;

    @Column(length = 100)
    private String book;

    @Builder.Default
    @Column(nullable = false, columnDefinition = "BIGINT DEFAULT 0")
    private Long view = 0L; //조회수

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private Category category; //개인 카테고리


    @OneToMany(mappedBy = "post", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER,orphanRemoval = true)
    List<Image> imageList = new ArrayList<>();


    // 게시글 좋아요 필드 추가
    @Builder.Default
    @Column(name = "like_count", nullable = false, columnDefinition = "BIGINT DEFAULT 0")
    private Long likeCount = 0L;

}