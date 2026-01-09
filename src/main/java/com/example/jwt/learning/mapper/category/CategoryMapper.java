package com.example.jwt.learning.mapper.category;

import com.example.jwt.learning.dto.category.CategoryDTO;
import com.example.jwt.learning.dto.category.CreateCategoryDTO;
import com.example.jwt.learning.entity.category.Category;

public class CategoryMapper {

    public static Category toEntity(CreateCategoryDTO createCategoryDTO){
        Category category = new Category();
        category.setName(createCategoryDTO.getName());
        return category;
    }

    public static CategoryDTO toDTO(Category category){
        return CategoryDTO.builder()
                .uuid(category.getUuid())
                .name(category.getName())
                .createdAt(category.getCreatedAt())
                .build();
    }
}
