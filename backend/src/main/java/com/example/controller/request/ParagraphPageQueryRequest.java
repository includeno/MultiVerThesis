package com.example.controller.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ParagraphPageQueryRequest {
    @NotNull
    private Integer page;
    @NotNull
    private Integer size;
}
