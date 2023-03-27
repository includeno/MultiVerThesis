package com.example.service.sql;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.entity.Paragraph;
import com.example.mapper.ParagraphMapper;
import org.springframework.stereotype.Service;

@Service
public class ParagraphServiceImpl extends ServiceImpl<ParagraphMapper, Paragraph> implements ParagraphService {
}
