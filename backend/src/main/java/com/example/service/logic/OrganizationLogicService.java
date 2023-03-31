package com.example.service.logic;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.entity.Membership;
import com.example.entity.Organization;
import com.example.service.sql.MembershipService;
import com.example.service.sql.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OrganizationLogicService {
    @Autowired
    private OrganizationService organizationService;

    @Autowired
    private MembershipService membershipService;

    public Boolean addOrganization(String name, Integer userId) {
        Organization organization = new Organization();
        organization.setName(name);
        organization.setUuid(UUID.randomUUID().toString());
        Boolean saveOrganization=organizationService.save(organization);

        Membership membership = new Membership();
        membership.setOrganizationId(organization.getOrganizationId());
        membership.setUserId(userId);
        membership.setUuid(UUID.randomUUID().toString());
        membership.setMemberType("admin");
        Boolean saveMembership=membershipService.save(membership);
        return saveOrganization&& saveMembership;
    }


    public void updateOrganization(Integer organizationId, String name,Integer valid) {
        UpdateWrapper<Organization> updateWrapper = new UpdateWrapper<>();
        int count=0;
        if(name != null){
            updateWrapper.set("name", name);
            count++;
        }
        if(valid != null){
            updateWrapper.set("valid", valid);
            count++;
        }
        if(count==0){
            return;
        }
        updateWrapper.eq("organization_id", organizationId);
        organizationService.update(updateWrapper);
    }

    public Organization getOrganization(Integer organizationId) {
        return organizationService.getById(organizationId);
    }
}
