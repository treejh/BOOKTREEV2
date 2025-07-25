package com.example.booktree.domain.blog.dto.response;



import com.example.booktree.domain.blog.entity.Blog;
import com.example.booktree.global.utils.ImageUtil;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class BlogResponseDto {

    private String name;
    private String profile;
    private String notice;
    private Long blogId;
    private String ownerUsername; // 추가
    private String ownerImageUrl; //유저 이미지 추가

    public BlogResponseDto(Blog blog){

        this.blogId = blog.getId();
        this.profile= blog.getProfile();
        this.notice = blog.getNotice();
        this.name = blog.getName();
        this.ownerImageUrl = ImageUtil.getValidProfileImage(blog.getUser().getImage());

        this.ownerUsername = blog.getUser().getUsername(); // 추가
    }
}
