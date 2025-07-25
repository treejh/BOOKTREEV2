package com.example.booktree.global.image.dto.response;

import com.example.booktree.global.image.entity.Image;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ImageResponseDto {

    private Long postId;
    private String images;

    public void ImageRequestDto(Image image){
        this.postId = image.getId();
        this.images = image.getImageUrl();
    }
    
}