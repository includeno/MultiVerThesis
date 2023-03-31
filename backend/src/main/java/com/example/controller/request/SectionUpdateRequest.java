package com.example.controller.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

// 更新的request
@Data
public class SectionUpdateRequest {
    @NotNull
    private Integer sectionId;
    private String sectionTitle;
    private BigDecimal sortIndex;
}