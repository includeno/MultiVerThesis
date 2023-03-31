package com.example.service.logic;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.entity.Membership;
import com.example.entity.Organization;
import com.example.service.sql.MembershipService;
import com.example.service.sql.OrganizationService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrganizationAndMemberLogicService {
    @Autowired
    private OrganizationService organizationService;

    @Autowired
    private MembershipService membershipService;

    public List<OrganizationInfo> getOrganizationAndMembersByUserId(Integer userId) {
        // Step 1: 查询 membership 表，获取该用户关联的所有组织ID
        QueryWrapper<Membership> membershipQueryWrapper = new QueryWrapper<>();
        membershipQueryWrapper.eq("user_id", userId);
        List<Integer> organizationIds = membershipService.list(membershipQueryWrapper)
                .stream()
                .map(Membership::getOrganizationId)
                .collect(Collectors.toList());

        // Step 2: 根据上一步获取的组织ID列表，查询 organization 表，获取组织的基础信息
        QueryWrapper<Organization> organizationQueryWrapper = new QueryWrapper<>();
        organizationQueryWrapper.in("organization_id", organizationIds);
        List<Organization> organizations = organizationService.list(organizationQueryWrapper);

        // Step 3: 使用组织ID列表再次查询 membership 表，获取成员信息
        QueryWrapper<Membership> membersQueryWrapper = new QueryWrapper<>();
        membersQueryWrapper.in("organization_id", organizationIds);
        List<Membership> memberships = membershipService.list(membersQueryWrapper);

        // 将成员信息关联到组织信息中
        List<OrganizationInfo> organizationInfos = organizations.stream().map(organization -> {
            OrganizationInfo organizationInfo = new OrganizationInfo();
            organizationInfo.setOrganization(organization);
            List<Membership> orgMembers = memberships.stream()
                    .filter(member -> member.getOrganizationId().equals(organization.getOrganizationId()))
                    .collect(Collectors.toList());
            organizationInfo.setMembers(orgMembers);
            return organizationInfo;
        }).collect(Collectors.toList());

        return organizationInfos;
    }

    // 定义一个实体类，用于返回组织信息及其成员信息
    @Data
    public static class OrganizationInfo {
        private Organization organization;
        private List<Membership> members;
    }
}
