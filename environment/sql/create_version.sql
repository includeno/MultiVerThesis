-- 创建version表
DROP TABLE IF EXISTS `version`;
CREATE TABLE `version`
(
    `version_id`     int(10) NOT NULL AUTO_INCREMENT COMMENT '版本ID',
    `version_uuid`   varchar(36) NOT NULL COMMENT '版本UUID',
    `version_number` int(10) NOT NULL DEFAULT 1 COMMENT '版本号（数字），默认1，每次修改加1',
    `version_type`   varchar(20) NOT NULL COMMENT '版本类型，如段落paragraph、章节section等',
    `timestamp`    datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '版本创建时间',
    PRIMARY KEY (`version_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
