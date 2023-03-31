package com.example.service.sql;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.entity.Membership;
import com.example.mapper.MembershipMapper;
import org.springframework.stereotype.Service;

@Service
public class MembershipServiceImpl extends ServiceImpl<MembershipMapper, Membership> implements MembershipService {
}
