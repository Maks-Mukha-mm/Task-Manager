package com.taskmanager.dto;

import com.taskmanager.entity.TaskStatus;

import java.time.LocalDateTime;

public class TaskResponseDto {

    private Long id;
    private String title;
    private String description;
    private TaskStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private final TaskResponseDto dto = new TaskResponseDto();

        public Builder id(Long id) { dto.id = id; return this; }
        public Builder title(String title) { dto.title = title; return this; }
        public Builder description(String description) { dto.description = description; return this; }
        public Builder status(TaskStatus status) { dto.status = status; return this; }
        public Builder createdAt(LocalDateTime createdAt) { dto.createdAt = createdAt; return this; }
        public Builder updatedAt(LocalDateTime updatedAt) { dto.updatedAt = updatedAt; return this; }
        public TaskResponseDto build() { return dto; }
    }

    public Long getId() { return id; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public TaskStatus getStatus() { return status; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
}
