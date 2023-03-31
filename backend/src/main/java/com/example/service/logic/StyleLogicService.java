package com.example.service.logic;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.entity.Style;
import com.example.service.sql.StyleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class StyleLogicService {
    @Autowired
    private StyleService styleService;

    @Transactional
    // 新增样式
    public boolean createStyle(Style style) {
        String uuid = UUID.randomUUID().toString();
        style.setStyleUuid(uuid);

        // 保存样式
        boolean styleSaved = styleService.save(style);

        // 返回保存结果
        return styleSaved;
    }

    // 更新样式
    @Transactional
    public boolean updateStyle(Style style) {
        // 部分字段更新
        boolean styleUpdated = styleService.updateById(style);

        // 返回更新结果
        return styleUpdated;
    }

    // 根据样式UUID查询
    public Style getStyleByUuid(String styleUuid) {
        return styleService.getById(styleUuid);
    }

    // 分页查询样式
    public IPage<Style> getStylesByPage(Integer page, Integer size) {
        Page<Style> pageParam = new Page<>(page, size);
        return styleService.page(pageParam);
    }

    // 删除样式
    public boolean deleteStyle(String styleUuid) {
        UpdateWrapper<Style> updateWrapper = new UpdateWrapper<>();
        updateWrapper.set("valid",0);
        updateWrapper.eq("style_uuid",styleUuid);
        boolean styleUpdated = styleService.update(updateWrapper);
        // 返回更新结果
        return styleUpdated;
    }
}
