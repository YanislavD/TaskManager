package com.example.demo.mapper;

import com.example.demo.controller.dto.TaskResponse;
import com.example.demo.model.Task;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {

    public TaskResponse mapToDto(Task task) {
        return new TaskResponse(
                task.getId(),
                task.getTitle(),
                task.getDescription()
        );
    }

}
