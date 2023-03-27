package com.example.service.sql;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.entity.FileStorage;
import com.example.mapper.FileStorageMapper;
import org.springframework.stereotype.Service;

@Service
public class FileStorageServiceImpl extends ServiceImpl<FileStorageMapper, FileStorage> implements FileStorageService {
}
