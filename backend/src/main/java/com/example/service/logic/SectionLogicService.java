package com.example.service.logic;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.entity.Section;
import com.example.entity.Version;
import com.example.enums.VersionType;
import com.example.service.sql.SectionService;
import com.example.service.sql.VersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class SectionLogicService {
    @Autowired
    private SectionService sectionService;

    @Autowired
    private VersionService versionService;

    @Transactional
    // 新增章节
    public boolean createSection(Section section) {
        String uuid = UUID.randomUUID().toString();
        section.setUuid(uuid);

        // 保存章节
        boolean sectionSaved = sectionService.save(section);

        // 创建新版本
        Version newVersion = new Version();
        newVersion.setVersionUuid(uuid);
        newVersion.setVersionNumber(1);
        newVersion.setVersionType(VersionType.SECTION.getCode());
        newVersion.setLatestDataId(section.getSectionId());
        newVersion.setValid(1);
        boolean versionSaved = versionService.save(newVersion);

        // 返回保存结果
        return sectionSaved && versionSaved;
    }

    // 更新章节
    @Transactional
    public boolean updateSection(Section section) {
        // 保存章节
        boolean sectionSaved = sectionService.save(section);

        // 获取当前版本号
        Version currentVersion = versionService.getOne(new QueryWrapper<Version>()
                .eq("version_uuid", section.getUuid()));
        int currentVersionNumber = currentVersion.getVersionNumber();

        // 更新版本信息
        currentVersion.setVersionNumber(currentVersionNumber + 1);
        currentVersion.setLatestDataId(section.getSectionId());
        boolean versionSaved = versionService.updateById(currentVersion);

        // 返回更新结果
        return sectionSaved && versionSaved;
    }

    // 根据章节ID查询
    public Section getSectionById(Integer sectionId) {
        return sectionService.getById(sectionId);
    }

    // 根据版本UUID查询
    public List<Section> getSectionByUuid(String uuid) {
        QueryWrapper<Section> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("uuid", uuid);
        return sectionService.list(queryWrapper);
    }

    // 分页查询章节
    public IPage<Section> getSectionsByPage(Integer page, Integer size) {
        Page<Section> pageParam = new Page<>(page, size);
        return sectionService.page(pageParam);
    }

    public List<Section> getSectionsByIds(List<String> uuids) {
        return sectionService.list(new QueryWrapper<Section>().in("uuid", uuids));
    }
}
