package com.example.controller.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ProjectRequest {
    @NotNull
    private Integer projectId;
    @NotNull
    private String projectName;
}
