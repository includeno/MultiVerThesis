package com.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("style_attribute")
public class StyleAttribute {
    @TableId(type = IdType.ASSIGN_UUID)
    private String attributeId;
    private String styleUuid;
    private String attributeName;
    private String attributeValue;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
