package com.example.jwt.learning.service.category;

import com.example.jwt.learning.dto.category.CategoryDTO;
import com.example.jwt.learning.dto.category.CreateCategoryDTO;
import com.example.jwt.learning.entity.category.Category;
import com.example.jwt.learning.mapper.category.CategoryMapper;
import com.example.jwt.learning.repository.category.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    public CategoryRepository categoryRepository;

    public CategoryDTO saveCategory(CreateCategoryDTO createCategoryDTO){
        Category category = CategoryMapper.toEntity(createCategoryDTO);
        Category savedCategory = categoryRepository.save(category);
        return CategoryMapper.toDTO(savedCategory);
    }

    public List<CategoryDTO> getUsers(String search) {
        if (search != null && !search.isBlank()) {
            Category category = categoryRepository.findByName(search)
                    .orElseThrow(() ->new RuntimeException("Category Not Found!"));
            return List.of(CategoryMapper.toDTO(category));
        }

        List<Category> allCategories = categoryRepository.findAll();
        return allCategories.stream()
                .map(CategoryMapper::toDTO)
                .toList();
    }
}
