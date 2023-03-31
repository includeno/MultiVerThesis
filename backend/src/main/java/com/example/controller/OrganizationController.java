package com.example.controller;

import com.example.service.logic.OrganizationAndMemberLogicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrganizationController {

    @Autowired
    OrganizationAndMemberLogicService organizationAndMemberLogicService;

    @GetMapping("/organization")
    public List<OrganizationAndMemberLogicService.OrganizationInfo> getOrganizationsByUserId(Integer userId) {
        return organizationAndMemberLogicService.getOrganizationAndMembersByUserId(userId);
    }
}
