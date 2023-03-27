package com.example.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
@TableName("style")
public class Style {
    @TableId(value = "style_uuid")
    private String styleUuid;

    @TableField("style_name")
    private String styleName;

    @TableField("style_type")
    private String styleType;

    @TableField("style_value")
    private String styleValue;

    @TableField("type")
    private String type;

    @TableField("create_time")
    private LocalDateTime createTime;

    @TableField("update_time")
    private LocalDateTime updateTime;

    @TableField("valid")
    private Boolean valid;
}
