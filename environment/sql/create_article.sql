-- 创建project表
DROP TABLE IF EXISTS `project`;
CREATE TABLE `project`
(
    `project_id`    int(10) NOT NULL AUTO_INCREMENT COMMENT '项目ID',
    `uuid`  varchar(36) NOT NULL COMMENT 'UUID',
    `project_name`  varchar(128) NOT NULL COMMENT '项目名称',
    `description`   varchar(1000) NOT NULL DEFAULT '' COMMENT '项目描述',
    `create_time`   datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`   datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    `valid`         tinyint(1) NOT NULL DEFAULT 1 COMMENT '逻辑删除标记：0=无效（已删除），1=有效',
    PRIMARY KEY (`project_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 修改section表，删除外键约束，增加逻辑删除列valid
CREATE TABLE `section`
(
    `section_id`    int(10) NOT NULL AUTO_INCREMENT COMMENT '章节ID',
    `uuid`  varchar(36) NOT NULL COMMENT 'UUID',
    `project_id`    int(10) NOT NULL DEFAULT 0 COMMENT '关联的项目ID',
    `section_title` varchar(256) NOT NULL COMMENT '章节标题',
    `sort_index`    decimal(10,6) NOT NULL COMMENT '排序索引，6位小数，用于表示章节在项目中的排序位置',
    `create_time`   datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`   datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    `valid`         tinyint(1) NOT NULL DEFAULT 1 COMMENT '逻辑删除标记：0=无效（已删除），1=有效',
    PRIMARY KEY (`section_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 创建paragraph表
DROP TABLE IF EXISTS `paragraph`;
CREATE TABLE `paragraph`
(
    `paragraph_id`   int(10) NOT NULL AUTO_INCREMENT COMMENT '段落ID',
    `uuid`  varchar(36) NOT NULL COMMENT 'UUID',
    `content`        text DEFAULT NULL COMMENT '段落内容',
    `paragraph_type` varchar(20) NOT NULL COMMENT '段落类型，如text表示文本段落，image表示图片段落，formula表示公式',
    `file_id`        int(10) DEFAULT NULL COMMENT '关联的文件ID，仅当类型为image时有效',
    `create_time`    datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`    datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`paragraph_id`) USING BTREE,
    UNIQUE KEY `uniq_paragraph_uuid` (`uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 修改section_paragraph_relation表，增加style_uuid字段用于关联段落格式样式
-- CREATE TABLE `section_paragraph_relation`
-- (
--     `relation_id`    int(10) NOT NULL AUTO_INCREMENT COMMENT '关联ID',
--     `section_id`     int(10) NOT NULL COMMENT '章节ID',
--     `paragraph_id`   int(10) NOT NULL COMMENT '段落ID',
--     `style_uuid`     varchar(36) DEFAULT NULL COMMENT '段落格式样式UUID，关联style表',
--     `section_title`  varchar(256) NOT NULL COMMENT '章节标题（冗余字段）',
--     `paragraph_type` varchar(20) NOT NULL DEFAULT 'text' COMMENT '段落类型（冗余字段），如text表示文本段落，image表示图片段落',
--     `sort_index`     decimal(10,6) NOT NULL COMMENT '排序索引，6位小数，用于表示段落在章节中的排序位置',
--     `create_time`    datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
--     `update_time`    datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
--     PRIMARY KEY (`relation_id`) USING BTREE,
--     FOREIGN KEY (`section_id`) REFERENCES `section`(`section_id`),
--     FOREIGN KEY (`paragraph_id`) REFERENCES `paragraph`(`paragraph_id`),
--     FOREIGN KEY (`style_uuid`) REFERENCES `style`(`style_uuid`) -- 关联style表
-- ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 创建content_item_relation表
DROP TABLE IF EXISTS `content_item_relation`;
CREATE TABLE `content_item_relation`
(
    `relation_id`    int(10) NOT NULL AUTO_INCREMENT COMMENT '关联ID',
    `content_type`   varchar(20) NOT NULL COMMENT '内容项类型（section或paragraph）',
    `content_id`     int(10) NOT NULL COMMENT '内容项的实际ID（对应section_id或paragraph_id）',
    `parent_id`      int(10) DEFAULT NULL COMMENT '父级内容项ID（对应relation_id）',
    `ancestors`      varchar(255) DEFAULT '' COMMENT '祖级列表，存储从顶级内容项到当前内容项的所有上级relation_id，各ID之间用逗号分隔',
    `title`          varchar(256) DEFAULT NULL COMMENT '内容项标题',
    `sort_index`     decimal(10,6) NOT NULL COMMENT '排序索引，6位小数，用于表示内容项的排序位置',
    `create_time`    datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`    datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    `valid`         tinyint(1) NOT NULL DEFAULT 1 COMMENT '逻辑删除标记：0=无效（已删除），1=有效',
    PRIMARY KEY (`relation_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
