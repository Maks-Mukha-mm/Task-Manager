package com.taskmanager.service;

import com.taskmanager.dto.TaskResponseDto;
import com.taskmanager.entity.Task;
import com.taskmanager.exception.TaskNotFoundException;
import com.taskmanager.mapper.TaskMapper;
import com.taskmanager.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TaskServiceImplTest {

    @Mock TaskRepository repository;
    @Mock TaskMapper mapper;
    @InjectMocks TaskServiceImpl service;

    @Test
    void getById_whenExists_returnsDto() {
        Task task = new Task();
        task.setId(1L);
        TaskResponseDto dto = TaskResponseDto.builder().id(1L).build();

        when(repository.findById(1L)).thenReturn(Optional.of(task));
        when(mapper.toDto(task)).thenReturn(dto);

        TaskResponseDto result = service.getById(1L);
        assertThat(result.getId()).isEqualTo(1L);
    }

    @Test
    void getById_whenMissing_throwsException() {
        when(repository.findById(99L)).thenReturn(Optional.empty());
        assertThatThrownBy(() -> service.getById(99L))
                .isInstanceOf(TaskNotFoundException.class);
    }

    @Test
    void delete_whenMissing_throwsException() {
        when(repository.existsById(99L)).thenReturn(false);
        assertThatThrownBy(() -> service.delete(99L))
                .isInstanceOf(TaskNotFoundException.class);
    }
}
