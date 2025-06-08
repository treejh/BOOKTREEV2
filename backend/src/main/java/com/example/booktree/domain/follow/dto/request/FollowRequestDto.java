package com.example.booktree.domain.follow.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter@Setter@Builder
public class FollowRequestDto {

    private Long followeeId;

}
