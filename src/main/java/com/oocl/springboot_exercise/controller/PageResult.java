package com.oocl.springboot_exercise.controller;
import java.util.List;

/**
 * 分页查询结果封装类
 */
public class PageResult<T> {
    // 当前页码
    private int page;
    // 每页条数
    private int size;
    // 总条数
    private long total;
    // 总页数
    private int totalPages;
    // 当前页数据
    private List<T> content;

    public PageResult(int page, int size, long total, List<T> content) {
        this.page = page;
        this.size = size;
        this.total = total;
        this.content = content;
        // 计算总页数
        this.totalPages = (int) Math.ceil((double) total / size);
    }

    // Getter 方法
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
