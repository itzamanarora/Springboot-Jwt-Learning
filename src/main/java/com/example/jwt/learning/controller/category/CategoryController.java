package com.example.jwt.learning.controller.category;

import com.example.jwt.learning.dto.category.CategoryDTO;
import com.example.jwt.learning.dto.category.CreateCategoryDTO;
import com.example.jwt.learning.service.category.CategoryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/category")
@Tag(name = "Category Management", description = "Create, Retrieve, Update and Delete operations can be perform.")
public class CategoryController {

    @Autowired
    public CategoryService categoryService;

    @PostMapping("/create")
    public ResponseEntity<CategoryDTO> createCategory(@RequestBody CreateCategoryDTO createCategoryDTO){
        return new ResponseEntity<>(categoryService.saveCategory(createCategoryDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> getCategory(@RequestParam(required = false) String search) {
        return ResponseEntity.ok(categoryService.getUsers(search));
    }
}
