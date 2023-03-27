package com.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("version")
public class Version {
    @TableId(type = IdType.AUTO)
    private Integer versionId;
    private String versionUuid;
    private Integer versionNumber;
    private String versionType;
    private LocalDateTime timestamp;
    private Integer latestDataId;
    private Integer valid;
}
