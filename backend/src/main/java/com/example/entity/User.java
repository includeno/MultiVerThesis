package com.example.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class User {

    @TableId(type = IdType.AUTO)
    Integer id;
    String username;
    String password;
    String salt;
    String email;
    String avatar;//头像
    LocalDateTime lastLogin;//用户最后登录时间

    @TableField(fill = FieldFill.INSERT)
    LocalDateTime createTime;
    @TableField(fill = FieldFill.INSERT_UPDATE)
    LocalDateTime updateTime;
    @TableLogic
    Integer valid;
}
