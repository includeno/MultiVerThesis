package com.example.vo;

import com.example.utils.Element;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ProjectMetaData {
    private Integer id;
    private String uuid;
    private String projectName;
    private List<Element> elements=new ArrayList<>();
}
