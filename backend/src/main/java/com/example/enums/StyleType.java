package com.example.enums;

public enum StyleType {
    TEXT("text", "文本"),
    IMAGE("image", "图片"),
    FORMULA("formula", "公式"),
    HEADING_1("heading_1", "一级标题"),
    HEADING_2("heading_2", "二级标题"),
    HEADING_3("heading_3", "三级标题"),
    HEADING_4("heading_4", "四级标题"),
    HEADING_5("heading_5", "五级标题"),
    ABSTRACT("abstract", "摘要"),
    ABSTRACT_EN("abstract_en", "英文摘要"),
    REFERENCES("references", "参考文献正文"),
    REFERENCES_HEADING("references_heading", "参考文献标题"),
    FIGURE_CAPTION("figure_caption", "图说明文字"),
    TABLE_CAPTION("table_caption", "表说明文字"),
    TABLE("table", "表格");

    private final String code;
    private final String description;

    StyleType(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }
}