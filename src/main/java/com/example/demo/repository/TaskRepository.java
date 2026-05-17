package com.example.demo.repository;

import com.example.demo.model.Task;
import com.example.demo.model.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TaskRepository extends JpaRepository<Task, UUID> {

    List<Task> findAll ();

    List<Task> findByTaskStatusAndTitleContainingIgnoreCase(TaskStatus status, String title);

    List<Task> findByTaskStatus(TaskStatus status);

    List<Task> findByTitleContainingIgnoreCase(String title);
}
