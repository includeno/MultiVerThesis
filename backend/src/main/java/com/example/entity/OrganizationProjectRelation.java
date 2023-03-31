package com.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("organization_project_relation")
public class OrganizationProjectRelation {
    @TableId(type = IdType.AUTO)
    private Integer relationId;
    private Integer organizationId;
    private Integer createBy;
    private Integer projectId;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
