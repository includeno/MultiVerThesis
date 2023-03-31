package com.example.controller;

import com.example.entity.Organization;
import com.example.service.logic.OrganizationAndMemberLogicService;
import com.example.service.logic.OrganizationLogicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrganizationController {

    @Autowired
    OrganizationAndMemberLogicService organizationAndMemberLogicService;

    @Autowired
    OrganizationLogicService organizationLogicService;

    @GetMapping("/organizations/user")
    public List<OrganizationAndMemberLogicService.OrganizationInfo> getOrganizationsByUserId(Integer userId) {
        return organizationAndMemberLogicService.getOrganizationAndMembersByUserId(userId);
    }

    @GetMapping("/organization")
    public Organization getOrganization(Integer organizationId) {
        return organizationLogicService.getOrganization(organizationId);
    }

    @PostMapping("/organization")
    public Boolean addOrganization(String name, Integer userId) {
        return organizationLogicService.addOrganization(name, userId);
    }

    @PutMapping("/organization")
    public void updateOrganization(Integer organizationId, String name,Integer valid) {
        organizationLogicService.updateOrganization(organizationId, name, valid);
    }
}
