package com.example.demo.service;

import com.example.demo.controller.dto.CreateTaskRequest;
import com.example.demo.controller.dto.TaskResponse;
import com.example.demo.controller.dto.UpdateTaskRequest;
import com.example.demo.mapper.TaskMapper;
import com.example.demo.model.Priority;
import com.example.demo.model.Task;
import com.example.demo.model.TaskStatus;
import com.example.demo.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
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
                .taskStatus(TaskStatus.TODO)
                .priority(createTaskRequest.getPriority())
                .localDate(LocalDate.now())
                .build();

        Task saved = taskRepository.save(task);
        return taskMapper.mapToDto(saved);
    }

    public List<TaskResponse> getAll(
            TaskStatus status,
            String title
    ) {

        List<Task> tasks;

        if (status != null && title != null) {
            tasks = taskRepository
                    .findByTaskStatusAndTitleContainingIgnoreCase(status, title);
        } else if (status != null) {
            tasks = taskRepository.findByTaskStatus(status);
        } else if (title != null) {
            tasks = taskRepository.findByTitleContainingIgnoreCase(title);
        } else {
            tasks = taskRepository.findAll();
        }

        return tasks.stream()
                .map(taskMapper::mapToDto)
                .toList();
    }

    public TaskResponse getById(UUID uuid){
         Task task = taskRepository.getReferenceById(uuid);

        return taskMapper.mapToDto(task);
    }

    public TaskResponse update(UUID id, UpdateTaskRequest request) {

        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setTaskStatus(request.getTaskStatus());
        task.setPriority(request.getPriority());

        Task updated = taskRepository.save(task);

        return taskMapper.mapToDto(updated);
    }


}
