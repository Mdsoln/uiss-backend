package com.uiss.home.models;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PageResponse<T> {
    private final List<T> content;
    private final int totalPages;
    private final long totalElements;

    public PageResponse(List<T> content, int totalPages, long totalElements, long elements) {
        this.content = content;
        this.totalPages = totalPages;
        this.totalElements = totalElements;

    }
}
