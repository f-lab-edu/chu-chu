package com.example.chuchu.common.global;

import org.springframework.data.domain.Sort;

public class PageRequest {

    private int page;
    private int size;
    private Sort.Direction direction;
    private String sortBy;

    public PageRequest(Integer page, Integer size, Sort.Direction direction, String sortBy) {
        this.page = page <= 0 ? 1 : page;
        this.size = size;
        this.direction = direction;
        this.sortBy = sortBy;
    }

    public org.springframework.data.domain.PageRequest of() {
        return org.springframework.data.domain.PageRequest.of(page - 1, size, direction, sortBy);
    }
}
