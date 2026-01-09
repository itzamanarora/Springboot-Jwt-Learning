package com.example.jwt.learning.dto.product;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateProductsDTO {
    @NotBlank(message = "Title is required!")
    private String title;

    @NotBlank(message = "Description is required!")
    private String description;

    @NotBlank(message = "Category UUID is required!")
    private String categoryUuid;

    private String imageUrl;

    @NotBlank(message = "Stock is required!")
    private int Stock;

    @NotBlank(message = "Price is required!")
    private double price;
}
