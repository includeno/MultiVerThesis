package com.example.utils;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.List;
@Data
@Accessors(chain = true)
public class Element {
    String title;
    String type;//VersionType: section, paragraph
    String contentType;//ContentType: text, image, video, audio, link, code, table, list, math, html, markdown
    String content;
    Integer id;

    String uuid;//Version uuid
    BigDecimal sortIndex;
    List<Element> contents;
    Boolean isCollapsed;

    public Element(String uuid,String title, String type, String contentType, String content,BigDecimal sortIndex, List<Element> contents) {
        this.uuid = uuid;
        this.title = title;
        this.type = type;
        this.contentType = contentType;
        this.content = content;
        this.contents = contents;
        this.sortIndex = sortIndex;
        this.isCollapsed = true;
    }

    public static Element createSection(String uuid,String title, String type, String contentType, String content,BigDecimal sortIndex, List<Element> contents) {
        return new Element(uuid,title, type, contentType, content, sortIndex,contents);
    }

    public static Element createParagraph(String uuid,String title, String type, String contentType, String content,BigDecimal sortIndex, List<Element> contents) {
        return new Element(uuid,title, type, contentType, content,sortIndex, contents);
    }
}
