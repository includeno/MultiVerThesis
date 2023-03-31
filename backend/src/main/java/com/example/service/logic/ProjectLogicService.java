package com.example.service.logic;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.entity.*;
import com.example.enums.VersionType;
import com.example.service.sql.*;
import com.example.utils.Element;
import com.example.utils.JsonDataGenerator;
import com.example.vo.ProjectMetaData;
import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ProjectLogicService {
    @Autowired
    private ProjectService projectService;

    @Autowired
    private SectionLogicService sectionLogicService;

    @Autowired
    private ParagraphLogicService paragraphLogicService;

    @Autowired
    private VersionService versionService;

    @Autowired
    private ContentItemRelationService contentItemRelationService;

    @Autowired
    private UserService userService;

    @Autowired
    private MembershipService membershipService;

    @Autowired
    private OrganizationService organizationService;

    @Autowired
    private OrganizationProjectRelationService organizationProjectRelationService;

    // 添加项目
    public boolean addProject(Project project) {
        return projectService.save(project);
    }

    // 修改项目
    public boolean updateProject(Integer projectId, String projectName) {
        // 创建更新条件
        UpdateWrapper<Project> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("project_id", projectId);
        updateWrapper.set("project_name", projectName);

        // 使用Mybatis-plus Service进行部分字段的更新
        return projectService.update(updateWrapper);
    }

    // 删除项目（逻辑删除）
    public boolean deleteProjectById(int projectId) {
        Project project = new Project();
        project.setProjectId(projectId);
        project.setValid(0); // 将逻辑删除标记设置为0（已删除）
        return projectService.updateById(project);
    }

    // 分页查询项目
    public Page<Project> getProjectsByPage(int page, int size) {
        Page<Project> projectPage = new Page<>(page, size);
        return projectService.page(projectPage);
    }

    // 根据项目ID查询项目
    public Project getProjectById(int projectId) {
        return projectService.getById(projectId);
    }

    // 根据项目UUID查询项目
    public Project getProjectByUuid(String uuid) {
        QueryWrapper<Project> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("uuid", uuid);
        return projectService.getOne(queryWrapper);
    }

    // 根据用户UUID查询项目
    public List<Project> getProjectsByUserUuid(String userUuid) {
        User user = userService.getOne(new QueryWrapper<User>().eq("uuid", userUuid));
        if (user == null) {
            return Collections.emptyList();
        }

        QueryWrapper<Project> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_uuid", userUuid);

        // Step 1: 查询 membership 表，获取该用户关联的所有组织ID
        QueryWrapper<Membership> membershipQueryWrapper = new QueryWrapper<>();
        membershipQueryWrapper.eq("user_id", user.getId());
        List<Integer> organizationIds = membershipService.list(membershipQueryWrapper)
                .stream()
                .map(Membership::getOrganizationId)
                .collect(Collectors.toList());

        // Step 2: 根据上一步获取的组织ID列表，查询 organization 表，获取组织的基础信息
        QueryWrapper<Organization> organizationQueryWrapper = new QueryWrapper<>();
        organizationQueryWrapper.in("organization_id", organizationIds);
        List<Organization> organizations = organizationService.list(organizationQueryWrapper);

        // Step 3: 根据上一步获取的组织列表，获取组织的关联项目列表
        QueryWrapper<OrganizationProjectRelation> organizationProjectRelationQueryWrapper = new QueryWrapper<>();
        organizationProjectRelationQueryWrapper.in("organization_id", organizationIds);
        List<Project> projects = organizationProjectRelationService.list(organizationProjectRelationQueryWrapper)
                .stream()
                .map(organizationProjectRelation -> {
                    Project project = projectService.getById(organizationProjectRelation.getProjectId());
                    return project;
                })
                .collect(Collectors.toList());
        return projects;
    }

    // 根据versions查询项目详情
    public List<Element> getProjectDetailByVersions(List<Version> versions) {
        List<Element> elements = new ArrayList<>();//返回的元素列表
        List<Version> sectionVersions = versions.stream().filter(version -> version.getVersionType().equals(VersionType.SECTION.getCode())).collect(Collectors.toList());
        log.info("sectionVersions: {}", sectionVersions);
        List<Version> paragraphVersions = versions.stream().filter(version -> version.getVersionType().equals(VersionType.PARAGRAPH.getCode())).collect(Collectors.toList());
        log.info("paragraphVersions: {}", paragraphVersions);
        List<Section> sections = sectionLogicService.getSectionsByIds(sectionVersions.stream().map(version -> version.getVersionUuid()).collect(Collectors.toList()));
        log.info("sections: {}", sections);
        List<Paragraph> paragraphs = paragraphLogicService.getParagraphsByIds(paragraphVersions.stream().map(version -> version.getVersionUuid()).collect(Collectors.toList()));
        log.info("paragraphs: {}", paragraphs);

        QueryWrapper<ContentItemRelation> contentItemRelationQueryWrapper = new QueryWrapper<>();
        contentItemRelationQueryWrapper.in("uuid", versions.stream().map(version -> version.getVersionUuid()).collect(Collectors.toList()));
        List<ContentItemRelation> conentItems = contentItemRelationService.list(contentItemRelationQueryWrapper);
        //parent
        Map<Integer, ContentItemRelation> parentContentItemRelationMap = conentItems.stream().filter(contentItemRelation -> contentItemRelation.getParentId() == null).collect(Collectors.toMap(
                contentItemRelation -> contentItemRelation.getRelationId(),
                contentItemRelation -> contentItemRelation
        ));

        //Map
        Map<Integer, ContentItemRelation> contentItemRelationMap = conentItems.stream().collect(Collectors.toMap(
                contentItemRelation -> contentItemRelation.getRelationId(),
                contentItemRelation -> contentItemRelation
        ));
        Map<String, ContentItemRelation> uuidContentItemRelationMap = conentItems.stream().collect(Collectors.toMap(
                contentItemRelation -> contentItemRelation.getUuid(),
                contentItemRelation -> contentItemRelation
        ));

        // 创建章节映射关系
        Map<String, Element> sectionMap = sections.stream().collect(Collectors.toMap(
                section -> section.getUuid(),
                section -> Element.createSection(section.getUuid(), section.getTitle(), "section", section.getContentType(),
                        section.getContent(), uuidContentItemRelationMap.get(section.getUuid()).getSortIndex(), new ArrayList<>())
        ));
        log.info("sectionMap: {}", new Gson().toJson(sectionMap));

        // 创建段落映射关系
        Map<String, Element> paragraphMap = paragraphs.stream().collect(Collectors.toMap(
                paragraph -> paragraph.getUuid(),
                paragraph -> Element.createParagraph(paragraph.getUuid(), paragraph.getTitle(), "paragraph", paragraph.getContentType(),
                        paragraph.getContent(), uuidContentItemRelationMap.get(paragraph.getUuid()).getSortIndex(), null))
        );
        log.info("paragraphMap: {}", new Gson().toJson(paragraphMap));

        // 构建内容项关系
        conentItems.forEach(relation -> {
            Element currentElement = sectionMap.get(relation.getUuid());
            if (currentElement == null) {
                currentElement = paragraphMap.get(relation.getUuid());
            }
            if (parentContentItemRelationMap.containsKey(relation.getRelationId())) {
                elements.add(currentElement);
            } else {
                ContentItemRelation parentRelation = contentItemRelationMap.get(relation.getParentId());
                Element parentElement = sectionMap.get(parentRelation.getUuid());
                if (parentElement != null) {
                    parentElement.getContents().add(currentElement);
                }
            }
        });

        List<Element> allElements = new ArrayList<>();
        Collections.sort(elements, JsonDataGenerator.elementComparator);
        for (Element element : elements) {
            JsonDataGenerator.collectAllElements(element, allElements);
        }
        JsonDataGenerator.assignId(allElements);

        return elements;
    }

    // 根据项目ID查询项目详情
    public ProjectMetaData getProjectDetail(String uuid) {
        ProjectMetaData projectMetaData = new ProjectMetaData();

        QueryWrapper<Project> projectQueryWrapper = new QueryWrapper<>();
        projectQueryWrapper.eq("uuid", uuid);
        Project project = null;
        try {
            project = projectService.getOne(projectQueryWrapper);
            log.info("project: {}", project);
        } catch (Exception ex) {
            log.error("getProjectDetail error: {}", ex.getMessage());
            return new ProjectMetaData();
        }
        projectMetaData.setProjectName(project.getProjectName());
        projectMetaData.setId(project.getProjectId());
        projectMetaData.setUuid(project.getUuid());

        QueryWrapper<Version> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("project_id", project.getUuid());
        List<Version> versions = versionService.list(queryWrapper);
        log.info("versions: {}", versions);

        List<Element> elements = getProjectDetailByVersions(versions);
        log.info("elements: {}", elements);
        projectMetaData.setElements(elements);
        return projectMetaData;
    }
}
