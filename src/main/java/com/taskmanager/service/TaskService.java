package com.taskmanager.service;

import com.taskmanager.dto.TaskRequestDto;
import com.taskmanager.dto.TaskResponseDto;

import java.util.List;

public interface TaskService {
    TaskResponseDto create(TaskRequestDto dto);
    TaskResponseDto getById(Long id);
    List<TaskResponseDto> getAll();
    TaskResponseDto update(Long id, TaskRequestDto dto);
    void delete(Long id);
}
