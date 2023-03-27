package com.example.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
@TableName("version")
public class Version {
    @TableId(value = "version_id")
    private Integer versionId;

    @TableField("version_uuid")
    private String versionUuid;

    @TableField("version_number")
    private Integer versionNumber;

    @TableField("version_type")
    private String versionType;

    @TableField("timestamp")
    private LocalDateTime timestamp;
}
