package com.example.booktree.domain.follow.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class FollowCountDto {
    private Long followerCount;
    private Long followingCount;
}
