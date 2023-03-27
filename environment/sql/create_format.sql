-- 创建theme表
DROP TABLE IF EXISTS `theme`;
CREATE TABLE `theme`
(
    `theme_uuid` varchar(36) NOT NULL COMMENT '主题UUID',
    `theme_name` varchar(128) NOT NULL COMMENT '主题名称',
    `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    `valid`       tinyint(1) UNSIGNED ZEROFILL NULL DEFAULT 1 COMMENT '逻辑删除:0=无效,1=有效',
    PRIMARY KEY (`theme_uuid`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 创建style表
DROP TABLE IF EXISTS `style`;
CREATE TABLE `style`
(
    `style_uuid`      varchar(36) NOT NULL COMMENT '样式UUID',
    `style_name`      varchar(128) NOT NULL COMMENT '样式名称',
    `style_type`      varchar(50) NOT NULL COMMENT '样式类型 1=字体,2=颜色,3=背景,4=边框,5=其他',
    `style_value`     varchar(500) NOT NULL COMMENT '样式值',
    `type`           varchar(50) NOT NULL COMMENT '类型 如文本、图片、公式 标题1 标题2',
    `create_time`     datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`     datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    `valid`       tinyint(1) UNSIGNED ZEROFILL NULL DEFAULT 1 COMMENT '逻辑删除:0=无效,1=有效',
    PRIMARY KEY (`style_uuid`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 创建theme_style_relation表，存储theme和style之间的关联关系
DROP TABLE IF EXISTS `theme_style_relation`;
CREATE TABLE `theme_style_relation`
(
    `relation_id` int(10) NOT NULL AUTO_INCREMENT COMMENT '关联ID',
    `theme_uuid`  varchar(36) NOT NULL COMMENT '关联的主题UUID',
    `style_uuid`  varchar(36) NOT NULL COMMENT '关联的样式UUID',
    `style_type`  varchar(50) NOT NULL COMMENT '关联的样式类型',
    `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`relation_id`) USING BTREE,
    UNIQUE KEY `uniq_theme_style` (`theme_uuid`, `style_uuid`),
    UNIQUE KEY `uniq_theme_style_type` (`theme_uuid`, `style_type`),  -- 新增联合唯一键约束
    FOREIGN KEY (`theme_uuid`) REFERENCES `theme`(`theme_uuid`),
    FOREIGN KEY (`style_uuid`) REFERENCES `style`(`style_uuid`),
    INDEX `idx_theme_style` (`theme_uuid`, `style_uuid`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- 创建style_attribute表，存储样式的属性
DROP TABLE IF EXISTS `style_attribute`;
CREATE TABLE `style_attribute`
(
    `attribute_id`    varchar(36) NOT NULL COMMENT '属性ID',
    `style_uuid`      varchar(36) NOT NULL COMMENT '关联的样式UUID',
    `attribute_name`  varchar(50) NOT NULL COMMENT '属性名称，如font、color、background、border等',
    `attribute_value` varchar(500) NOT NULL COMMENT '属性值',
    `create_time`     datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`     datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`attribute_id`) USING BTREE,
    FOREIGN KEY (`style_uuid`) REFERENCES `style`(`style_uuid`),
    INDEX `idx_style_attribute` (`style_uuid`, `attribute_name`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

