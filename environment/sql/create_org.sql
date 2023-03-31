DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`
(
    `id`          int(10)                      NOT NULL AUTO_INCREMENT COMMENT '用户id',
    `uuid`        varchar(36)                  NOT NULL COMMENT '用户uuid',
    `username`    varchar(100)                 NOT NULL DEFAULT '' COMMENT '用户名称',
    `password`    varchar(100)                 NOT NULL DEFAULT '' COMMENT '用户密码',
    `salt`        varchar(64)                  NOT NULL COMMENT '密码加盐',
    `email`       varchar(100)                 NOT NULL DEFAULT '' COMMENT '用户邮箱',
    `avatar`      varchar(256)                 NOT NULL DEFAULT '' COMMENT '用户头像',
    `last_login`  datetime                     NOT NULL COMMENT '用户最后登录时间',
    `create_time` datetime                     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '用户创建时间',
    `update_time` datetime                     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '用户修改时间',
    `valid`       tinyint(1) UNSIGNED ZEROFILL NOT NULL DEFAULT 1 COMMENT '逻辑删除:0=无效,1=有效',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

-- https://zhuanlan.zhihu.com/p/97035035
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role`
(
    `id`          int(10)                      NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `user_id`     int(10)                      NULL DEFAULT NULL COMMENT '用户ID',
    `role_id`     int(10)                      NULL DEFAULT NULL COMMENT '角色ID',
    `create_time` datetime                          DEFAULT NULL COMMENT '创建时间',
    `update_time` datetime                          DEFAULT NULL COMMENT '修改时间',
    `valid`       tinyint(1) UNSIGNED ZEROFILL NULL DEFAULT 1 COMMENT '逻辑删除:0=无效,1=有效',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `user_index` (`user_id`) USING BTREE COMMENT '用户ID Index'
) ENGINE = InnoDB
  CHARSET = utf8mb4 COMMENT = '用户角色表';

-- 创建 organization 表
DROP TABLE IF EXISTS `organization`;
CREATE TABLE `organization`
(
    `organization_id` int(10)      NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `uuid`            varchar(36)  NOT NULL COMMENT '工作区ID',
    `name`            varchar(100) NOT NULL COMMENT '工作区名称',
    `create_time`     datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`     datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    `valid`           tinyint(1)   NOT NULL DEFAULT 1 COMMENT '逻辑删除标记：0=无效（已删除），1=有效',
    PRIMARY KEY (`organization_id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

-- 创建 membership 表
DROP TABLE IF EXISTS `membership`;
CREATE TABLE `membership`
(
    `member_id`       INT(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '成员关系ID',
    `uuid`            varchar(36)      NOT NULL COMMENT '成员关系UUID',
    `user_id`         INT(10) UNSIGNED NOT NULL COMMENT '成员ID',
    `organization_id` INT(10) UNSIGNED NOT NULL COMMENT '工作区ID',
    `member_type`     varchar(20)      NOT NULL COMMENT '成员类型',
    `unconfirmed`     tinyint(1)       NOT NULL DEFAULT 0 COMMENT '是否未确认：0=否，1=是',
    `deactivated`     tinyint(1)       NOT NULL DEFAULT 0 COMMENT '是否已停用：0=否，1=是',
    `org_member_type` varchar(20)               DEFAULT NULL COMMENT '工作区内成员类型',
    PRIMARY KEY (`member_id`) USING BTREE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4;

-- 创建 organization_project_relation 表
DROP TABLE IF EXISTS `organization_project_relation`;
CREATE TABLE `organization_project_relation`
(
    `relation_id`     INT(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '关系ID',
    `organization_id` INT(10) UNSIGNED NOT NULL COMMENT '工作区ID',
    `create_by`       INT(10) UNSIGNED NOT NULL COMMENT '成员ID',
    `project_id`      INT(10) UNSIGNED NOT NULL COMMENT '项目ID',
    `create_time`     DATETIME         NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`     DATETIME         NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`relation_id`),
    UNIQUE KEY `org_project_unique` (`organization_id`, `project_id`)             -- 工作区ID和项目ID的唯一性约束
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='工作区与项目关系表';

