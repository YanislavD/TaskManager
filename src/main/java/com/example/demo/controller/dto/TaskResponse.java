package com.example.demo.controller.dto;

import com.example.demo.model.Priority;
import com.example.demo.model.TaskStatus;
import lombok.Data;

import java.util.UUID;

@Data

public class TaskResponse {

    private UUID id;
    private String title;
    private String description;
    private TaskStatus taskStatus;
    private Priority priority;


    public TaskResponse(UUID id, String title, String description, TaskStatus taskStatus, Priority priority) {
        this.id=id;
        this.title=title;
        this.description=description;
        this.taskStatus=taskStatus;
        this.priority=priority;
    }
}
