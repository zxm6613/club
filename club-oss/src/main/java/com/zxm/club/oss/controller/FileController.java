package com.zxm.club.oss.controller;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.zxm.club.oss.service.FileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/oss")
public class FileController {
    @NacosValue(value = "${oss.storyType}",autoRefreshed = true)
    private String storyType;
    @Resource
    private FileService fileService;

    @GetMapping("/bucket")
    public String upload() throws Exception {
        List<String> allBuckets = fileService.getAllBuckets();
        return allBuckets.get(0);
    }

    @GetMapping("/storyType")
    public String testNacos() throws Exception {
        return storyType;
    }
}
