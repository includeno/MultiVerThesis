package com.example.service.sql;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.entity.StyleAttribute;
import com.example.mapper.StyleAttributeMapper;
import org.springframework.stereotype.Service;

@Service
public class StyleAttributeServiceImpl extends ServiceImpl<StyleAttributeMapper, StyleAttribute> implements StyleAttributeService {
}
