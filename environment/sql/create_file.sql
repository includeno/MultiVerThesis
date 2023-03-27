-- 创建file_storage表，存储文件的详细信息
DROP TABLE IF EXISTS `file_storage`;
CREATE TABLE `file_storage`
(
    `file_id`       int(10) NOT NULL AUTO_INCREMENT COMMENT '文件ID',
    `file_uuid`     varchar(36) NOT NULL COMMENT '文件UUID',
    `file_name`     varchar(256) NOT NULL COMMENT '文件名',
    `file_type`     varchar(50) NOT NULL COMMENT '文件类型',
    `file_size`     bigint(20) NOT NULL DEFAULT 0 COMMENT '文件大小，单位字节',
    `file_url`      varchar(256) NOT NULL COMMENT '文件URL',
    `file_md5`      varchar(32) NOT NULL COMMENT '文件MD5校验值',
    `create_time`   datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`   datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`file_id`) USING BTREE,
    UNIQUE KEY `uniq_file_uuid` (`file_uuid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;