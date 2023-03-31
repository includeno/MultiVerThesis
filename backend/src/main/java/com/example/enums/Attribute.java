package com.example.enums;

import java.util.EnumSet;
import java.util.Set;

public enum Attribute {
    // 字体属性
    FONT("font", "字体", "设置字体", EnumSet.of(StyleType.HEADING_1, StyleType.HEADING_2, StyleType.HEADING_3, StyleType.ABSTRACT)),
    // 大小属性
    FONT_SIZE("font_size", "大小", "设置字体大小", EnumSet.of(StyleType.HEADING_1, StyleType.HEADING_2, StyleType.HEADING_3, StyleType.ABSTRACT)),
    // 对齐方式属性
    ALIGNMENT("alignment", "对齐方式", "设置对齐方式", EnumSet.of(StyleType.HEADING_1, StyleType.HEADING_2, StyleType.HEADING_3, StyleType.ABSTRACT)),
    // 字体颜色属性
    FONT_COLOR("font_color", "字体颜色", "设置字体颜色", EnumSet.of(StyleType.HEADING_1, StyleType.HEADING_2, StyleType.HEADING_3, StyleType.ABSTRACT)),
    // 背景颜色属性
    BACKGROUND_COLOR("background_color", "背景颜色", "设置背景颜色", EnumSet.of(StyleType.TABLE)),
    // 边框属性
    BORDER("border", "边框", "设置边框", EnumSet.of(StyleType.TABLE)),
    // 缩进属性
    INDENT("indent", "缩进", "设置缩进", EnumSet.of(StyleType.HEADING_1, StyleType.HEADING_2, StyleType.HEADING_3, StyleType.ABSTRACT));

    private final String code;
    private final String name;
    private final String description;
    private final Set<StyleType> applicableStyleTypes;

    Attribute(String code, String name, String description, Set<StyleType> applicableStyleTypes) {
        this.code = code;
        this.name = name;
        this.description = description;
        this.applicableStyleTypes = applicableStyleTypes;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Set<StyleType> getApplicableStyleTypes() {
        return applicableStyleTypes;
    }

    // 根据样式类型获取适用的属性列表
    public static EnumSet<Attribute> getApplicableAttributes(StyleType styleType) {
        EnumSet<Attribute> applicableAttributes = EnumSet.noneOf(Attribute.class);
        for (Attribute attribute : Attribute.values()) {
            if (attribute.getApplicableStyleTypes().contains(styleType)) {
                applicableAttributes.add(attribute);
            }
        }
        return applicableAttributes;
    }
}
