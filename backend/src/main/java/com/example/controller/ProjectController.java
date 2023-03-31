package com.example.controller;

import com.example.controller.request.ProjectRequest;
import com.example.entity.Project;
import com.example.service.logic.ProjectLogicService;
import com.example.utils.JsonResult;
import com.example.vo.ProjectMetaData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
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
    public Boolean addProject(@Valid @NotNull String projectName, @NotNull Integer userId, @NotNull Integer organizationId) {
        return projectLogicService.addProject(projectName, userId, organizationId);
    }

    @PutMapping("/project")
    public Boolean updateProject(@Valid ProjectRequest projectRequest) {
        return projectLogicService.updateProject(projectRequest.getProjectId(), projectRequest.getProjectName(), projectRequest.getValid());
    }

    @GetMapping("/project/detail")
    public JsonResult<ProjectMetaData> getProjectDetail(@Valid @NotNull String uuid) {
        return JsonResult.ok(projectLogicService.getProjectDetail(uuid));
    }

    @GetMapping("/projects")
    public List<Project> gethProjectsByUserUuid(String userUuid) {
        return projectLogicService.getProjectsByUserUuid(userUuid);
    }
}
