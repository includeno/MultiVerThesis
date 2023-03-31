package com.example.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.example.controller.request.ParagraphCreateRequest;
import com.example.controller.request.ParagraphPageQueryRequest;
import com.example.controller.request.ParagraphUpdateRequest;
import com.example.entity.Paragraph;
import com.example.service.logic.ParagraphLogicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ParagraphController {
    @Autowired
    private ParagraphLogicService paragraphLogicService;

    @GetMapping("/paragraph")
    public Paragraph getParagraph(@RequestParam("id") Integer id) {
        return paragraphLogicService.getParagraphById(id);
    }

    @PostMapping("/paragraph")
    public Boolean createParagraph(@Valid ParagraphCreateRequest request) {
        Paragraph paragraph = new Paragraph();
        paragraph.setContent(request.getContent());
        paragraph.setFileId(request.getFileId());
        paragraph.setContentType(request.getContentType());
        return paragraphLogicService.createParagraph(paragraph);
    }

    @PutMapping("/paragraph")
    public Boolean updateParagraph(@Valid ParagraphUpdateRequest request) {
        Paragraph paragraph = new Paragraph();
        paragraph.setParagraphId(request.getParagraphId());
        paragraph.setContent(request.getContent());
        paragraph.setFileId(request.getFileId());
        paragraph.setContentType(request.getContentType());
        return paragraphLogicService.updateParagraph(paragraph);
    }

    @GetMapping("/paragraphs/page")
    public IPage<Paragraph> getParagraphsByPage(@Valid ParagraphPageQueryRequest request) {
        return paragraphLogicService.getParagraphsByPage(request.getPage(), request.getSize());
    }

    @GetMapping("/paragraphs")
    public List<Paragraph> getParagraphs(@RequestParam("ids") List<String> ids,String uuid) {
        if(ids!=null&&ids.size()>0){
            return paragraphLogicService.getParagraphsByIds(ids);
        }
        if(uuid!=null&&!uuid.equals("")){
            return paragraphLogicService.getParagraphByUuid(uuid);
        }
        return new ArrayList<>();
    }
}
