package com.example.jwt.learning.service.product;

import com.example.jwt.learning.dto.product.CreateProductsDTO;
import com.example.jwt.learning.dto.product.ProductDTO;
import com.example.jwt.learning.entity.category.Category;
import com.example.jwt.learning.entity.product.Product;
import com.example.jwt.learning.mapper.product.ProductMapper;
import com.example.jwt.learning.repository.category.CategoryRepository;
import com.example.jwt.learning.repository.product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductServices {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Transactional
    public ProductDTO createProduct(CreateProductsDTO createProductsDTO) {
        Product product = ProductMapper.toEntity(createProductsDTO);
        Category category = categoryRepository.findById(createProductsDTO.getCategoryUuid())
                .orElseThrow(() -> new RuntimeException("Category Not Found!")
        );
        product.setCategory(category);
        Product savedProduct = productRepository.save(product);
        return ProductMapper.toDTO(savedProduct);
    }

    @Transactional(readOnly = true)
    public Page<ProductDTO> getProducts(String search, int page, int size) {

        Pageable pageable = PageRequest.of(page, size, Sort.by("createdAt").descending());

        Page<Product> productPage;

        if (search != null && !search.isBlank()) {
            productPage = productRepository.findByTitleContainingIgnoreCase(search, pageable);
        } else {
            productPage = productRepository.findAll(pageable);
        }

        return productPage.map(ProductMapper::toDTO);
    }
}
