package com.example.service.sql;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.entity.ContentItemRelation;
import com.example.mapper.ContentItemRelationMapper;
import org.springframework.stereotype.Service;

@Service
public class ContentItemRelationServiceImpl extends ServiceImpl<ContentItemRelationMapper, ContentItemRelation> implements ContentItemRelationService {
}
