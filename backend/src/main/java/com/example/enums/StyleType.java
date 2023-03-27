package com.example.enums;

public enum StyleType {
    // 一级标题样式
    HEADING_1("heading_1"),
    // 二级标题样式
    HEADING_2("heading_2"),
    // 三级标题样式
    HEADING_3("heading_3"),
    // 四级标题样式
    HEADING_4("heading_4"),
    // 五级标题样式
    HEADING_5("heading_5"),
    // 摘要样式
    ABSTRACT("abstract"),
    // 英文摘要样式
    ABSTRACT_EN("abstract_en"),
    // 参考文献正文样式
    REFERENCES("references"),
    // 参考文献标题样式
    REFERENCES_HEADING("references_heading"),
    // 图说明文字样式
    FIGURE_CAPTION("figure_caption"),
    // 表说明文字样式
    TABLE_CAPTION("table_caption"),
    // 表格样式
    TABLE("table");

    // 样式类型的字符串表示
    private final String type;

    // 构造函数，初始化样式类型的字符串表示
    StyleType(String type) {
        this.type = type;
    }

    // 获取样式类型的字符串表示
    public String getType() {
        return type;
    }
}
