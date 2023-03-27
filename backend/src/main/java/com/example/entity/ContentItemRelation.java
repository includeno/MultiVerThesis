package com.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
@TableName("content_item_relation")
public class ContentItemRelation {
    @TableId(value = "relation_id", type = IdType.AUTO)
    private Integer relationId;

    @TableField("content_type")
    private String contentType;

    @TableField("content_id")
    private Integer contentId;

    @TableField("parent_id")
    private Integer parentId;

    @TableField("ancestors")
    private String ancestors;

    @TableField("title")
    private String title;

    @TableField("sort_index")
    private BigDecimal sortIndex;

    @TableField("create_time")
    private LocalDateTime createTime;

    @TableField("update_time")
    private LocalDateTime updateTime;

    @TableField("valid")
    private Boolean valid;
}