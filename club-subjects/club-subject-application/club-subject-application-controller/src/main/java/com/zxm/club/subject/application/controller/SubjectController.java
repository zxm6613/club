package com.zxm.club.subject.application.controller;

import com.zxm.club.subject.application.vo.SubjectCategoryVO;
import com.zxm.club.subject.common.entity.Result;
import com.zxm.club.subject.infra.basic.entity.SubjectCategory;
import com.zxm.club.subject.infra.basic.service.SubjectCategoryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class SubjectController {

    @Resource
    private SubjectCategoryService subjectCategoryService;

    @GetMapping("/test")
    public String test(){
        SubjectCategory subjectCategory = subjectCategoryService.queryById(1L);
        return subjectCategory.getCategoryName();
    }



}
