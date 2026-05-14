package com.example.demo.controller;

import com.example.demo.controller.dto.CreateTaskRequest;
import com.example.demo.controller.dto.TaskResponse;
import com.example.demo.controller.dto.UpdateTaskRequest;
import com.example.demo.service.TaskService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequestMapping
@Controller
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @PostMapping
    public ResponseEntity<TaskResponse> createTask(@Valid @RequestBody CreateTaskRequest request) {

        TaskResponse response = taskService.create(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    @GetMapping
    public ResponseEntity<List<TaskResponse>> getAll() {
        return ResponseEntity.ok(taskService.getAllTasks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<TaskResponse>> getAllById(@PathVariable UUID id){
        return ResponseEntity.ok(taskService.getAllById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskResponse> updateTask(@PathVariable UUID id, @RequestBody UpdateTaskRequest updateTaskDto){
        return ResponseEntity.ok(taskService.update(id, updateTaskDto));
    }

}
