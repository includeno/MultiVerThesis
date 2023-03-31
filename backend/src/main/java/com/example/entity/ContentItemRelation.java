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

    private String contentType;
    private String uuid;
    private Integer parentId;
    private String ancestors;
    private String title;
    private BigDecimal sortIndex;
    private String styleUuid;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private Integer valid;
}