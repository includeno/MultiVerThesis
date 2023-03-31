package com.example.service.logic;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.entity.Style;
import com.example.entity.Theme;
import com.example.entity.ThemeStyleRelation;
import com.example.service.sql.StyleService;
import com.example.service.sql.ThemeService;
import com.example.service.sql.ThemeStyleRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ThemeLogicService {
    @Autowired
    private ThemeService themeService;

    @Autowired
    private ThemeStyleRelationService themeStyleRelationService;

    @Autowired
    private StyleService styleService;

    // 新增主题
    public boolean createTheme(Theme theme) {
        String uuid = UUID.randomUUID().toString();
        theme.setThemeUuid(uuid);

        // 保存主题
        boolean themeSaved = themeService.save(theme);

        // 返回保存结果
        return themeSaved;
    }

    // 更新主题
    public boolean updateTheme(Theme theme) {
        // 部分字段更新
        boolean themeUpdated = themeService.updateById(theme);

        // 返回更新结果
        return themeUpdated;
    }

    // 根据主题UUID查询
    public Theme getThemeByUuid(String themeUuid) {
        return themeService.getById(themeUuid);
    }

    // 分页查询主题
    public IPage<Theme> getThemesByPage(Integer page, Integer size) {
        Page<Theme> pageParam = new Page<>(page, size);
        return themeService.page(pageParam);
    }

    // 新增主题与样式的关联关系
    public boolean createThemeStyleRelation(String themeUuid, String styleUuid) {
        ThemeStyleRelation relation = new ThemeStyleRelation();
        relation.setThemeUuid(themeUuid);
        relation.setStyleUuid(styleUuid);

        // 保存关联关系
        return themeStyleRelationService.save(relation);
    }

    // 根据主题UUID查询关联的样式列表
    public List<Style> getStyleListByThemeUuid(String themeUuid) {
        // 根据主题UUID查询关联的样式UUID列表
        QueryWrapper<ThemeStyleRelation> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("style_uuid").eq("theme_uuid", themeUuid);
        List<String> styleUuidList = themeStyleRelationService.listObjs(queryWrapper, obj -> (String) obj);

        // 查询样式列表
        QueryWrapper<Style> styleQueryWrapper = new QueryWrapper<>();
        styleQueryWrapper.in("style_uuid", styleUuidList);
        return styleService.list(styleQueryWrapper);
    }
}
