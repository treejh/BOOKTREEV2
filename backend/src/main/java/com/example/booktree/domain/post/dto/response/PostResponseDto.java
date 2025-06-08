package com.example.booktree.domain.post.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter@Setter
@Builder
public class PostResponseDto {
    private Long postId;
    private String title;
    private Long viewCount;
    private int ranking;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;
    private String imageUrl;
    private double score;

    private String content; // 추가
    private String username; // 추가 (user.getNickname() 또는 user.getName())
    private Long categoryId; // 추가
    private String category; // 추가 (category.getName())

}
