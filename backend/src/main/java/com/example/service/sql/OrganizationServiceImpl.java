package com.example.service.sql;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.entity.Organization;
import com.example.mapper.OrganizationMapper;
import org.springframework.stereotype.Service;

@Service
public class OrganizationServiceImpl extends ServiceImpl<OrganizationMapper, Organization> implements OrganizationService {
}
