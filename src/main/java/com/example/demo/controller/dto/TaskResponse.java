package com.example.demo.controller.dto;

import lombok.Data;

import java.util.UUID;

@Data

public class TaskResponse {

    private UUID id;
    private String title;


    public TaskResponse(UUID id, String title, String description) {
        this.id=id;
        this.title=title;
    }
}
