package com.example.service.logic;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.entity.Paragraph;
import com.example.entity.Section;
import com.example.entity.Version;
import com.example.enums.VersionType;
import com.example.service.sql.ParagraphService;
import com.example.service.sql.VersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class ParagraphLogicService {
    @Autowired
    private ParagraphService paragraphService;

    @Autowired
    private VersionService versionService;

    @Transactional
    // 新增段落
    public boolean createParagraph(Paragraph paragraph) {
        Paragraph newParagraph = new Paragraph();
        newParagraph.setContent(paragraph.getContent());
        newParagraph.setFileId(paragraph.getFileId());

        String uuid= UUID.randomUUID().toString();
        newParagraph.setContentType(paragraph.getContentType());
        newParagraph.setUuid(uuid);

        // 保存段落
        boolean paragraphSaved = paragraphService.save(newParagraph);
        // 创建新版本
        Version newVersion = new Version();
        newVersion.setVersionUuid(newParagraph.getUuid());
        newVersion.setVersionNumber(1000);
        newVersion.setVersionType(VersionType.PARAGRAPH.getCode());
        newVersion.setLatestDataId(newParagraph.getParagraphId());
        newVersion.setValid(1);
        boolean versionSaved = versionService.save(newVersion);
        // 返回保存结果
        return paragraphSaved && versionSaved;
    }

    // 更新段落
    @Transactional
    public boolean updateParagraph(Paragraph paragraph) {
        // 插入新段落作为新版本
        Paragraph newParagraph = new Paragraph();
        newParagraph.setContent(paragraph.getContent());
        newParagraph.setFileId(paragraph.getFileId());
        newParagraph.setContentType(paragraph.getContentType());
        newParagraph.setUuid(paragraph.getUuid());

        // 更新段落
        boolean paragraphSaved = paragraphService.save(newParagraph);

        // 创建新版本
        Version newVersion = new Version();
        newVersion.setVersionUuid(paragraph.getUuid());
        newVersion.setVersionNumber(1); // 默认为1，实际应根据前一个版本号递增
        newVersion.setLatestDataId(paragraph.getParagraphId());
        boolean versionSaved = versionService.save(newVersion);
        // 返回更新结果
        return paragraphSaved && versionSaved;
    }

    // 根据段落ID查询
    public Paragraph getParagraphById(Integer paragraphId) {
        return paragraphService.getById(paragraphId);
    }

    // 根据版本UUID查询
    public List<Paragraph> getParagraphByUuid(String uuid) {
        QueryWrapper<Paragraph> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("uuid", uuid);
        return paragraphService.list(queryWrapper);
    }

    // 分页查询段落
    public IPage<Paragraph> getParagraphsByPage(Integer page, Integer size) {
        Page<Paragraph> pageParam = new Page<>(page, size);
        return paragraphService.page(pageParam);
    }

    public List<Paragraph> getParagraphsByIds(List<String> uuids) {
        return paragraphService.list(new QueryWrapper<Paragraph>().in("uuid", uuids));
    }
}
