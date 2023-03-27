package com.example.service.sql;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.entity.Version;
import com.example.mapper.VersionMapper;
import org.springframework.stereotype.Service;

@Service
public class VersionServiceImpl extends ServiceImpl<VersionMapper, Version> implements VersionService {
}
