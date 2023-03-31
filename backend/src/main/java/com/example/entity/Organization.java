package com.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("organization")
public class Organization {
    @TableId(type = IdType.AUTO)
    private Integer organizationId;
    private String uuid;
    private String name;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private Boolean valid;
}