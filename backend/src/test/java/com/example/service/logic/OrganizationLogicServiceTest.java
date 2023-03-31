package com.example.service.logic;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OrganizationLogicServiceTest {
    @Autowired
    OrganizationLogicService organizationLogicService;

    @Rollback
    @Test
    public void test() {
        String name = "test";
        Integer userId = 1;
        Boolean res = organizationLogicService.addOrganization(name, userId);
        assertTrue(res);
    }
}