package com.example.jwt.learning.mapper.product;

import com.example.jwt.learning.dto.product.CreateProductsDTO;
import com.example.jwt.learning.dto.product.ProductDTO;
import com.example.jwt.learning.entity.category.Category;
import com.example.jwt.learning.entity.product.Product;
import com.example.jwt.learning.repository.category.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class ProductMapper {

    @Autowired
    private CategoryRepository categoryRepository;

    public static Product toEntity(CreateProductsDTO createProductsDTO) {

        Product product = new Product();
        product.setTitle(createProductsDTO.getTitle());
        product.setDescription(createProductsDTO.getDescription());
        product.setImageUrl(createProductsDTO.getImageUrl());
        product.setStock(createProductsDTO.getStock());
        product.setPrice(createProductsDTO.getPrice());
        return product;
    }

    public static ProductDTO toDTO(Product product){
        return ProductDTO.builder()
                .uuid(product.getUuid())
                .title(product.getTitle())
                .description(product.getDescription())
                .categoryName(product.getCategory().getName())
                .imageUrl(product.getImageUrl())
                .stock(product.getStock())
                .price(product.getPrice())
                .createdAt(product.getCreatedAt())
                .build();
    }
}
