package com.example.controller;

import com.example.controller.request.ProjectRequest;
import com.example.entity.Project;
import com.example.service.logic.ProjectLogicService;
import com.example.vo.ProjectMetaData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ProjectController {
    @Autowired
    ProjectLogicService projectLogicService;

    @GetMapping("/project")
    public Project getProject(@Valid @NotNull Integer id) {
        return projectLogicService.getProjectById(id);
    }

    @PostMapping("/project")
    public Boolean addProject(String projectName) {
        Project project = new Project();
        project.setProjectName(projectName);
        return projectLogicService.addProject(project);
    }

    @PutMapping("/project")
    public Boolean updateProject(@Valid ProjectRequest projectRequest) {
        return projectLogicService.updateProject(projectRequest.getProjectId(), projectRequest.getProjectName());
    }

    @GetMapping("/project/detail")
    public ProjectMetaData getProjectDetail(@Valid @NotNull String uuid) {
        return projectLogicService.getProjectDetail(uuid);
    }

    @GetMapping("/projects")
    public List<Project> gethProjectsByUserUuid(String userUuid) {
        return projectLogicService.getProjectsByUserUuid(userUuid);
    }
}
