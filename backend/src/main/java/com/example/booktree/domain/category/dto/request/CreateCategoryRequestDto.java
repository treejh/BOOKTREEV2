package com.example.booktree.domain.category.dto.request;

import com.example.booktree.domain.category.entity.Category;
import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class CreateCategoryRequestDto {
    private String categoryName;

    public Category toEntity() {
        Category category = new Category();
        category.setName(categoryName);
        return category;
    }
}
