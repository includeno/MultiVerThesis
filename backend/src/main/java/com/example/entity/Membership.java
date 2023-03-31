package com.example.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("membership")
public class Membership {
    @TableId(type = IdType.AUTO)
    private Integer memberId;
    private String uuid;
    private Integer userId;
    private Integer organizationId;
    private String memberType;
    private Boolean unconfirmed;
    private Boolean valid;
    private String orgMemberType;
}
