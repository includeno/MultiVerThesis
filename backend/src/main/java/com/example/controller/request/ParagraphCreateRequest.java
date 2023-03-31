package com.example.controller.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ParagraphCreateRequest {
    @NotNull
    private String content;
    @NotNull
    private String contentType;
    private Integer fileId;
}