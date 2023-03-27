package com.example.service.sql;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.entity.Section;
import com.example.mapper.SectionMapper;
import org.springframework.stereotype.Service;

@Service
public class SectionServiceImpl extends ServiceImpl<SectionMapper, Section> implements SectionService {
}
