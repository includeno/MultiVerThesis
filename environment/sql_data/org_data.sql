-- 插入用户数据
INSERT INTO `user`
(`id`, `uuid`, `username`, `password`, `salt`, `email`, `avatar`, `last_login`, `create_time`, `update_time`, `valid`)
VALUES
    (1, 'user-uuid-1', 'JohnDoe', 'password-hash', 'salt-value', 'johndoe@example.com', 'avatar-url', '2023-03-31 10:00:00', '2023-03-31 10:00:00', '2023-03-31 10:00:00', 1);

-- 插入组织数据
INSERT INTO `organization`
(`organization_id`, `uuid`, `name`, `create_time`, `update_time`, `valid`)
VALUES
    (1, 'org-uuid-1', 'Test Organization', '2023-03-31 10:00:00', '2023-03-31 10:00:00', 1);

-- 插入成员关系数据
INSERT INTO `membership`
(`member_id`, `uuid`, `user_id`, `organization_id`, `member_type`, `unconfirmed`, `deactivated`, `org_member_type`)
VALUES
    (1, 'membership-uuid-1', 1, 1, 'admin', 0, 0, 'admin');

-- 插入工作区与项目关系数据
INSERT INTO `organization_project_relation`
(`relation_id`, `organization_id`, `create_by`, `project_id`, `create_time`, `update_time`)
VALUES
    (1, 1, 1, 1, '2023-03-31 10:00:00', '2023-03-31 10:00:00'),
    (2, 1, 1, 2, '2023-03-31 10:00:00', '2023-03-31 10:00:00'),
    (3, 1, 1, 3, '2023-03-31 10:00:00', '2023-03-31 10:00:00');
