-- 创建version表
DROP TABLE IF EXISTS `version`;
CREATE TABLE `version`
(
    `version_id`     int(10) NOT NULL AUTO_INCREMENT COMMENT '版本ID',
    `version_uuid`   varchar(36) NOT NULL COMMENT '版本UUID',
    `version_number` int(10) NOT NULL DEFAULT 1 COMMENT '版本号（数字），默认1，每次修改加1',
    `version_type`   varchar(20) NOT NULL COMMENT '版本类型，如段落paragraph、章节section等',
    `timestamp`    datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '版本修改时间',
    `latest_data_id`        int(10) NOT NULL COMMENT '最新数据ID 根据版本类型查询对应表',
    `valid`         tinyint(1) NOT NULL DEFAULT 1 COMMENT '逻辑删除标记：0=无效（已删除），1=有效',
    PRIMARY KEY (`version_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
