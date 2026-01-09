package com.example.jwt.learning.utilis;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageApiResponse<T> {
    private int pageNumber;
    private int pageSize;
    private long count;
    private int totalPages;
    private Boolean isLast;
    private List<T> content;

    public PageApiResponse(Page<T> page) {
        this.pageNumber = page.getNumber();
        this.pageSize = page.getSize();
        this.count = page.getTotalElements();
        this.totalPages = page.getTotalPages();
        this.isLast = page.isLast();
        this.content = page.getContent();
    }
}
