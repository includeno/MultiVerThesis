package com.example.service.sql;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.entity.ThemeStyleRelation;
import com.example.mapper.ThemeStyleRelationMapper;
import org.springframework.stereotype.Service;

@Service
public class ThemeStyleRelationServiceImpl extends ServiceImpl<ThemeStyleRelationMapper, ThemeStyleRelation> implements ThemeStyleRelationService {
}
