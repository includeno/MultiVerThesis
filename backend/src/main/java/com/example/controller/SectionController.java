package com.example.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.controller.request.SectionCreateRequest;
import com.example.controller.request.SectionPageQueryRequest;
import com.example.controller.request.SectionUpdateRequest;
import com.example.entity.Section;
import com.example.service.logic.SectionLogicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
public class SectionController {
    @Autowired
    private SectionLogicService sectionLogicService;

    @GetMapping("/section")
    public Section getSection(Integer id) {
        return sectionLogicService.getSectionById(id);
    }

    @PostMapping("/section")
    public Boolean createSection(@Valid SectionCreateRequest request) {
        Section section = new Section();
        section.setTitle(request.getSectionTitle());
        return sectionLogicService.createSection(section);
    }

    @PutMapping("/section")
    public Boolean updateSection(@Valid SectionUpdateRequest request) {
        Section section = new Section();
        section.setSectionId(request.getSectionId());
        section.setTitle(request.getSectionTitle());
        return sectionLogicService.updateSection(section);
    }

    @GetMapping("/sections/page")
    public IPage<Section> getSectionsByPage(@Valid SectionPageQueryRequest request) {
        return sectionLogicService.getSectionsByPage(request.getPage(), request.getSize());
    }

    @GetMapping("/sections")
    public List<Section> getSectionByProjectId(@RequestParam("ids") List<String> ids,String uuid) {
        if(ids!=null&&ids.size()>0){
            return sectionLogicService.getSectionsByIds(ids);
        }
        if(uuid!=null&&!uuid.equals("")){
            return sectionLogicService.getSectionByUuid(uuid);
        }
        return new ArrayList<>();
    }
}

