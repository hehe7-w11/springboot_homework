package com.oocl.springboot_exercise.common;

import java.util.List;

public class PageResult<T> {

    private int page;
    private int size;
    private long total;
    private int totalPages;
    private List<T> content;

    public PageResult(int page, int size, long total, List<T> content) {
        this.page = page;
        this.size = size;
        this.total = total;
        this.content = content;
        this.totalPages = (int) Math.ceil((double) total / size);
    }

    public int getPage() {
        return page;
    }

    public int getSize() {
        return size;
    }

    public long getTotal() {
        return total;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public List<T> getContent() {
        return content;
    }
}
