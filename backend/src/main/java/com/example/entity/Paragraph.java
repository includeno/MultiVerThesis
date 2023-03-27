package com.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = false)
@TableName("paragraph")
public class Paragraph {
    @TableId(value = "paragraph_id", type = IdType.AUTO)
    private Integer paragraphId;
    private String uuid;
    private String content;
    private String paragraphType;
    private Integer fileId;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
