package com.example.controller.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

// 分页查询的request
@Data
public class SectionPageQueryRequest {
    private Integer sectionId;
    private String sectionTitle;
    @NotNull
    private Integer page; // 当前页码
    @NotNull
    private Integer size; // 每页记录数
}


