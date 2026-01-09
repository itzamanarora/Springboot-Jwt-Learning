package com.example.jwt.learning.dto.product;

import com.example.jwt.learning.entity.category.Category;
import com.example.jwt.learning.entity.review.Review;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDTO {
    private String uuid;
    private String title;
    private String description;
    private String categoryName;
    private List<Review> reviews;
    private String imageUrl;
    private int stock;
    private double price;
    private Instant createdAt;
}
