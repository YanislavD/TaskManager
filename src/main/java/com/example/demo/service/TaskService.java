package com.example.demo.service;

import com.example.demo.controller.dto.CreateTaskRequest;
import com.example.demo.controller.dto.TaskResponse;
import com.example.demo.controller.dto.UpdateTaskRequest;
import com.example.demo.mapper.TaskMapper;
import com.example.demo.model.Task;
import com.example.demo.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    public TaskService(TaskRepository taskRepository, TaskMapper taskMapper) {
        this.taskRepository = taskRepository;
        this.taskMapper = taskMapper;
    }

    public TaskResponse create(CreateTaskRequest createTaskRequest) {

        Task task = Task.builder()
                .title(createTaskRequest.getTitle())
                .description(createTaskRequest.getDescription())
                .localDate(LocalDate.now())
                .build();

        Task saved = taskRepository.save(task);
        return taskMapper.mapToDto(saved);
    }

    public List<TaskResponse> getAllTasks(){
        List<Task> allTasks = taskRepository.findAll();

       return allTasks.stream()
                .map(taskMapper::mapToDto)
                .toList();
    }

    public List<TaskResponse> getAllById(UUID uuid){
         Optional<Task>allTasksById = taskRepository.findById(uuid);

        return allTasksById.stream().map(taskMapper::mapToDto).toList();
    }

    public TaskResponse update(UUID id, UpdateTaskRequest request) {

        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());

        Task updated = taskRepository.save(task);

        return taskMapper.mapToDto(updated);
    }


}
