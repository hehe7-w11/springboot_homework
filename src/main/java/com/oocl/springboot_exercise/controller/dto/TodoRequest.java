package com.oocl.springboot_exercise.controller.dto;

import jakarta.validation.constraints.NotNull;

public class TodoRequest {
    @NotNull(message = "Title cannot be null")
    private String title;
    private Boolean completed;

    public TodoRequest() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }
}
