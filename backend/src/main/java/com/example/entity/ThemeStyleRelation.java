package com.example.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
@TableName("theme_style_relation")
public class ThemeStyleRelation {
    @TableId(value = "relation_id")
    private Integer relationId;

    @TableField("theme_uuid")
    private String themeUuid;

    @TableField("style_uuid")
    private String styleUuid;

    @TableField("create_time")
    private LocalDateTime createTime;

    @TableField("update_time")
    private LocalDateTime updateTime;
}
