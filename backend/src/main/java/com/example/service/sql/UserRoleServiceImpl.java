package com.example.service.sql;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.entity.UserRole;
import com.example.mapper.UserRoleMapper;
import org.springframework.stereotype.Service;

@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {
}
