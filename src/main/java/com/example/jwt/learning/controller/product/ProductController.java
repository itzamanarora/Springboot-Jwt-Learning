package com.example.jwt.learning.controller.product;

import com.example.jwt.learning.dto.product.CreateProductsDTO;
import com.example.jwt.learning.dto.product.ProductDTO;
import com.example.jwt.learning.service.product.ProductServices;
import com.example.jwt.learning.utilis.PageApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
@Tag(name = "Product Management", description = "Create, Retrieve, Update and Delete operations can be perform.")
public class ProductController {

    @Autowired
    private ProductServices productServices;

    @PostMapping("/create")
    public ResponseEntity<ProductDTO> createProduct(@RequestBody CreateProductsDTO createProductsDTO){
        return new ResponseEntity<>(productServices.createProduct(createProductsDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<PageApiResponse<ProductDTO>> getProducts(@RequestParam(required = false) String search,
                                                             @RequestParam(defaultValue = "0") int page,
                                                             @RequestParam(defaultValue = "10") int size ) {
        Page<ProductDTO> productDTOPage = productServices.getProducts(search, page, size);
        return ResponseEntity.ok(new PageApiResponse<>(productDTOPage));
    }
}
