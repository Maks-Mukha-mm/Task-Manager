package com.taskmanager.dto;

import com.taskmanager.entity.TaskStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class TaskRequestDto {

    @NotBlank(message = "Title must not be blank")
    @Size(max = 100, message = "Title must be 100 chars max")
    private String title;

    @Size(max = 1000, message = "Description must be 1000 chars max")
    private String description;

    private TaskStatus status;

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public TaskStatus getStatus() { return status; }
    public void setStatus(TaskStatus status) { this.status = status; }
}
