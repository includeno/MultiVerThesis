package com.example.service.sql;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.entity.Style;
import com.example.mapper.StyleMapper;
import org.springframework.stereotype.Service;

@Service
public class StyleServiceImpl extends ServiceImpl<StyleMapper, Style> implements StyleService {
}
