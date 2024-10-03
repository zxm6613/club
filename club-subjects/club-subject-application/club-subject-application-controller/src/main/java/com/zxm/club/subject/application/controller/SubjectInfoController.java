package com.zxm.club.subject.application.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.google.common.base.Preconditions;
import com.zxm.club.subject.application.convert.SubjectInfoBOConverter;
import com.zxm.club.subject.application.dto.SubjectInfoDTO;
import com.zxm.club.subject.common.entity.Result;
import com.zxm.club.subject.domain.entity.OptionListBO;
import com.zxm.club.subject.domain.entity.SubjectInfoBO;
import com.zxm.club.subject.domain.service.SubjectInfoDomainService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 题目controller
 *
 * @author zxm
 * @date 2024/10/02
 */
@RestController
@RequestMapping("/subject/subject")
@Slf4j
public class SubjectInfoController {

    @Autowired
    private SubjectInfoDomainService subjectInfoDomainService;


    /**
     * 新增题目
     *
     * @param subjectInfoDTO 主题信息 DTO
     * @return {@link Result }<{@link Boolean }>
     */
    @PostMapping("/add")
    public Result<Boolean> add(@RequestBody SubjectInfoDTO subjectInfoDTO) {
        try {
            //如果当前日志级别高于 Info（如设置为 Warn、Error），log.info 不会被执行。
            if (log.isInfoEnabled()) {
                log.info("SubjectInfoController.add.dto:{}",
                        JSON.toJSONString(subjectInfoDTO));
            }

            //健壮性判断
            Preconditions.checkArgument(StrUtil.isNotBlank(subjectInfoDTO.getSubjectName())
                    ,"题目名称不能为空");
            Preconditions.checkNotNull(subjectInfoDTO.getSubjectPrase(),"题目解析不能为空");
            Preconditions.checkNotNull(subjectInfoDTO.getSubjectType(),"题目类型不能为空");
            Preconditions.checkNotNull(subjectInfoDTO.getSubjectScore(),"题目分数不能为空");
            Preconditions.checkNotNull(subjectInfoDTO.getSubjectDifficult(),"题目难度不能为空");
            Preconditions.checkArgument(CollUtil.isNotEmpty(subjectInfoDTO.getCategoryId())
                    ,"分类id不能为空");
            Preconditions.checkArgument(CollUtil.isNotEmpty(subjectInfoDTO.getLabelId())
                    ,"标签id不能为空");

            //dto转bo
            SubjectInfoBO subjectInfoBO = SubjectInfoBOConverter.INSTANCE.subjectInfoDtoToBo(subjectInfoDTO);
            List<OptionListBO> optionListBOS = SubjectInfoBOConverter.INSTANCE.optionListDtoToBo(subjectInfoBO.getOptionListDTOS());
            subjectInfoBO.setOptionListDTOS(optionListBOS);

            subjectInfoDomainService.add(subjectInfoBO);
            return Result.ok();
        } catch (Exception e) {
            log.error("异常信息{}", e.getMessage());
            return Result.fail(e.getMessage());
        }
    }



}
