package com.example.service.sql;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.entity.OrganizationProjectRelation;
import com.example.mapper.OrganizationProjectRelationMapper;
import org.springframework.stereotype.Service;

@Service
public class OrganizationProjectRelationServiceImpl extends ServiceImpl<OrganizationProjectRelationMapper, OrganizationProjectRelation> implements OrganizationProjectRelationService {
}
