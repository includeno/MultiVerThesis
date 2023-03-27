package com.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName("section")
public class Section {
    @TableId(value = "section_id", type = IdType.AUTO)
    private Integer sectionId;
    private String uuid;
    private Integer projectId;
    private String sectionTitle;
    private BigDecimal sortIndex;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private Integer valid;
}