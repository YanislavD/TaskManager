package com.example.demo.controller.dto;


import com.example.demo.model.Priority;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateTaskRequest {

    @NotBlank
    @NotNull
    private String title;

    @NotNull
    private String description;

    @NotNull
    private Priority priority;
}
