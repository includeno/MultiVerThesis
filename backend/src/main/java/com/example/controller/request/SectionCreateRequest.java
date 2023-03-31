package com.example.controller.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

// 新增的request
@Data
public class SectionCreateRequest {
    @NotNull
    private String sectionTitle;
    @NotNull
    private BigDecimal sortIndex;
}