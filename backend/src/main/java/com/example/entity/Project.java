package com.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName("project")
public class Project {
    @TableId(value = "project_id", type = IdType.AUTO)
    private Integer projectId;
    private String uuid;
    private String projectName;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private Integer valid;
}