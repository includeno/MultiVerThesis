-- 向project表插入模拟数据
INSERT INTO `project` (`project_id`,`uuid`, `project_name`, `create_time`, `update_time`, `valid`)
VALUES
    (1,'8f94a8ce-abc0-4d71-b9f8-06f5678c1234', '项目A', '2023-01-01 10:00:00', '2023-01-01 10:00:00', 1),
    (2,'4d6a8b7c-1234-4def-9abc-8a765432fedc', '项目B', '2023-01-10 15:30:00', '2023-01-10 15:30:00', 1),
    (3,'2c3d4e5f-6789-4abc-bdef-9a87654321dc', '项目C', '2023-02-05 09:15:00', '2023-02-05 09:15:00', 1);

-- 向section表插入模拟数据
INSERT INTO `section` (`section_id`, `uuid`, `title`, `content`, `content_type`, `create_time`, `update_time`)
VALUES
    (1, 'v1-uuid', '章节1', NULL, 'text', '2023-01-01 10:00:00', '2023-01-01 10:00:00'),
    (2, 'v2-uuid', '章节1.1', NULL, 'text', '2023-01-01 10:00:00', '2023-01-01 10:00:00'),
    (3, 'v3-uuid', '章节1.1.1', NULL, 'text', '2023-01-01 10:00:00', '2023-01-01 10:00:00'),
    (4, 'v4-uuid', '章节2', NULL, 'text', '2023-01-01 10:00:00', '2023-01-01 10:00:00');

-- 向paragraph表插入模拟数据
INSERT INTO `paragraph` (`paragraph_id`, `uuid`, `title`, `content`, `content_type`, `create_time`, `update_time`)
VALUES
    (1, 'v5-uuid', '段落1.1', '段落1.1的内容', 'text', '2023-01-01 10:00:00', '2023-01-01 10:00:00'),
    (2, 'v6-uuid', '段落1.1.1', '段落1.1.1的内容', 'text', '2023-01-01 10:00:00', '2023-01-01 10:00:00'),
    (3, 'v7-uuid', '段落1.1.1.1', '段落1.1.1.1的内容', 'text', '2023-01-01 10:00:00', '2023-01-01 10:00:00'),
    (4, 'v8-uuid', '段落1.2', '段落1.2的内容', 'text', '2023-01-01 10:00:00', '2023-01-01 10:00:00'),
    (5, 'v9-uuid', '段落2.1', '段落2.1的内容', 'text', '2023-01-01 10:00:00', '2023-01-01 10:00:00');

-- 向version表插入模拟数据
INSERT INTO `version` (`version_uuid`, `project_id`, `version_number`, `version_type`, `timestamp`, `latest_data_id`, `valid`)
VALUES
    ('v1-uuid', 1, 1, 'section', '2023-01-01 10:00:00', (SELECT `section_id` FROM `section` WHERE `uuid` = 'v1-uuid'), 1),
    ('v2-uuid', 1, 1, 'section', '2023-01-01 10:00:00', (SELECT `section_id` FROM `section` WHERE `uuid` = 'v2-uuid'), 1),
    ('v3-uuid', 1, 1, 'section', '2023-01-01 10:00:00', (SELECT `section_id` FROM `section` WHERE `uuid` = 'v3-uuid'), 1),
    ('v4-uuid', 1, 1, 'section', '2023-01-01 10:00:00', (SELECT `section_id` FROM `section` WHERE `uuid` = 'v4-uuid'), 1),
    ('v5-uuid', 1, 1, 'paragraph', '2023-01-01 10:00:00', (SELECT `paragraph_id` FROM `paragraph` WHERE `uuid` = 'v5-uuid'), 1),
    ('v6-uuid', 1, 1, 'paragraph', '2023-01-01 10:00:00', (SELECT `paragraph_id` FROM `paragraph` WHERE `uuid` = 'v6-uuid'), 1),
    ('v7-uuid', 1, 1, 'paragraph', '2023-01-01 10:00:00', (SELECT `paragraph_id` FROM `paragraph` WHERE `uuid` = 'v7-uuid'), 1),
    ('v8-uuid', 1, 1, 'paragraph', '2023-01-01 10:00:00', (SELECT `paragraph_id` FROM `paragraph` WHERE `uuid` = 'v8-uuid'), 1),
    ('v9-uuid', 1, 1, 'paragraph', '2023-01-01 10:00:00', (SELECT `paragraph_id` FROM `paragraph` WHERE `uuid` = 'v9-uuid'), 1);

-- 向content_item_relation表插入模拟数据
INSERT INTO `content_item_relation` (`relation_id`, `content_type`, `uuid`, `parent_id`, `ancestors`, `title`, `sort_index`, `style_uuid`, `create_time`, `update_time`, `valid`)
VALUES
    (1, 'section', 'v1-uuid', NULL, '', '章节1', 1, 'style-uuid', '2023-01-01 10:00:00', '2023-01-01 10:00:00', 1),(2, 'paragraph', 'v5-uuid', 1, '1', '段落1.1', 1.1, 'style-uuid', '2023-01-01 10:00:00', '2023-01-01 10:00:00', 1),
    (3, 'section', 'v2-uuid', 1, '1', '章节1.1', 1.2, 'style-uuid', '2023-01-01 10:00:00', '2023-01-01 10:00:00', 1),
    (4, 'paragraph', 'v6-uuid', 3, '1,3', '段落1.1.1', 1.21, 'style-uuid', '2023-01-01 10:00:00', '2023-01-01 10:00:00', 1),
    (5, 'section', 'v3-uuid', 3, '1,3', '章节1.1.1', 1.22, 'style-uuid', '2023-01-01 10:00:00', '2023-01-01 10:00:00', 1),
    (6, 'paragraph', 'v7-uuid', 5, '1,3,5', '段落1.1.1.1', 1.221, 'style-uuid', '2023-01-01 10:00:00', '2023-01-01 10:00:00', 1),
    (7, 'paragraph', 'v8-uuid', 1, '1', '段落1.2', 1.3, 'style-uuid', '2023-01-01 10:00:00', '2023-01-01 10:00:00', 1),
    (8, 'section', 'v4-uuid', NULL, '', '章节2', 2, 'style-uuid', '2023-01-01 10:00:00', '2023-01-01 10:00:00', 1),
    (9, 'paragraph', 'v9-uuid', 8, '8', '段落2.1', 2.1, 'style-uuid', '2023-01-01 10:00:00', '2023-01-01 10:00:00', 1);


