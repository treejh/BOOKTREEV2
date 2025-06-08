package com.example.booktree.domain.likepost.dto;

import com.example.booktree.domain.user.entity.User;
import lombok.Getter;

@Getter
public class LikeUserListDto {
    private Long id;
    private String username;

    public LikeUserListDto(User user) {
        this.id = user.getId();
        this.username = user.getUsername(); //
    }
}
