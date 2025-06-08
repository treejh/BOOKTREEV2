package com.example.booktree.domain.user.dto.response;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserImageResponseDto {

    private String imageUrl;


    public UserImageResponseDto(String imageUrl){
      this.imageUrl=imageUrl;
    }

}
