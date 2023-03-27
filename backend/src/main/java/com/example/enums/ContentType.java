package com.example.enums;

public enum ContentType {
    SECTION("section"),
    PARAGRAPH("paragraph");

    private final String value;

    ContentType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    // 通过值获取对应的枚举实例
    public static ContentType fromValue(String value) {
        for (ContentType type : ContentType.values()) {
            if (type.getValue().equals(value)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Invalid content type value: " + value);
    }
}

