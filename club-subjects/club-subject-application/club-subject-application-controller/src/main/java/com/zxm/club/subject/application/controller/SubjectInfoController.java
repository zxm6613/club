package com.zxm.club.subject.application.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.google.common.base.Preconditions;
import com.zxm.club.subject.application.convert.SubjectInfoBOConverter;
import com.zxm.club.subject.application.convert.SubjectInfoDetailBOConverter;
import com.zxm.club.subject.application.convert.SubjectInfoPageBOConverter;
import com.zxm.club.subject.application.convert.SubjectOptionsBOConverter;
import com.zxm.club.subject.application.dto.OptionListDTO;
import com.zxm.club.subject.application.dto.SubjectInfoDTO;
import com.zxm.club.subject.application.dto.SubjectInfoPageDTO;
import com.zxm.club.subject.application.vo.OptionListVO;
import com.zxm.club.subject.application.vo.SubjectInfoDetailVO;
import com.zxm.club.subject.application.vo.SubjectInfoVO;
import com.zxm.club.subject.common.entity.Result;
import com.zxm.club.subject.common.entity.ResultPage;
import com.zxm.club.subject.domain.entity.OptionListBO;
import com.zxm.club.subject.domain.entity.SubjectInfoBO;
import com.zxm.club.subject.domain.entity.SubjectInfoDetailBO;
import com.zxm.club.subject.domain.entity.SubjectInfoPageBO;
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
                    , "题目名称不能为空");
            Preconditions.checkNotNull(subjectInfoDTO.getSubjectPrase(), "题目解析不能为空");
            Preconditions.checkNotNull(subjectInfoDTO.getSubjectType(), "题目类型不能为空");
            Preconditions.checkNotNull(subjectInfoDTO.getSubjectScore(), "题目分数不能为空");
            Preconditions.checkNotNull(subjectInfoDTO.getSubjectDifficult(), "题目难度不能为空");
            Preconditions.checkArgument(CollUtil.isNotEmpty(subjectInfoDTO.getCategoryId())
                    , "分类id不能为空");
            Preconditions.checkArgument(CollUtil.isNotEmpty(subjectInfoDTO.getLabelId())
                    , "标签id不能为空");

            //dto转bo
            SubjectInfoBO subjectInfoBO = SubjectInfoBOConverter.INSTANCE.subjectInfoDtoToBo(subjectInfoDTO);
            List<OptionListBO> optionListBOS = SubjectInfoBOConverter.INSTANCE.optionListDtoToBo(subjectInfoBO.getOptionListBOS());
            subjectInfoBO.setOptionListBOS(optionListBOS);

            subjectInfoDomainService.add(subjectInfoBO);
            return Result.ok();
        } catch (Exception e) {
            log.error("异常信息{}", e.getMessage());
            return Result.fail(e.getMessage());
        }
    }


    /**
     * 分页查询题目列表
     *
     * @param subjectInfoPageDTO 主题信息页面 DTO
     * @return {@link Result }<{@link ResultPage }<{@link SubjectInfoVO }>>
     */
    @PostMapping("/pageSubject")
    public Result<ResultPage<List<SubjectInfoVO>>> pageQuery(@RequestBody SubjectInfoPageDTO subjectInfoPageDTO) {
        try {
            //如果当前日志级别高于 Info（如设置为 Warn、Error），log.info 不会被执行。
            if (log.isInfoEnabled()) {
                log.info("SubjectInfoController.pageQuery.dto:{}",
                        JSON.toJSONString(subjectInfoPageDTO));
            }
            //健壮性判断
            Preconditions.checkNotNull(subjectInfoPageDTO.getDifficult(), "题目难度不能为空");
            Preconditions.checkNotNull(subjectInfoPageDTO.getLabelId(), "标签id不能为空");
            Preconditions.checkNotNull(subjectInfoPageDTO.getCategoryId(), "题目分类id不能为空");

            SubjectInfoPageBO subjectInfoPageBO = SubjectInfoPageBOConverter.INSTANCE.subjectInfoPageBOToDTO(subjectInfoPageDTO);

            ResultPage<List<SubjectInfoBO>> subjectInfoBOS = subjectInfoDomainService.pageQuery(subjectInfoPageBO);
            List<SubjectInfoBO> boData = subjectInfoBOS.getT();
            if (CollUtil.isEmpty(boData)) {
                throw new RuntimeException("没有查到题目数据");
            }

            List<SubjectInfoVO> voData = BeanUtil.copyToList(boData, SubjectInfoVO.class);
            ResultPage<List<SubjectInfoVO>> resultPage = new ResultPage<>();
            resultPage.setT(voData);
            resultPage.setTotal(subjectInfoBOS.getTotal());
            resultPage.setTotalPage(subjectInfoBOS.getTotalPage());
            return Result.ok(resultPage);
        } catch (Exception e) {
            log.error("查询失败,e.message：{}", e.getMessage(), e);
            return Result.fail(e.getMessage());
        }
    }


    /**
     * 查询题目详情
     *
     * @param subjectInfoDTO 主题信息 DTO
     * @return {@link Result }<{@link SubjectInfoDetailVO }>
     */
    @PostMapping("/subjectDetail")
    public Result<SubjectInfoDetailVO> subjectDetail(@RequestBody SubjectInfoDTO subjectInfoDTO) {
        try {
            //如果当前日志级别高于 Info（如设置为 Warn、Error），log.info 不会被执行。
            if (log.isInfoEnabled()) {
                log.info("SubjectInfoController.subjectDetail.dto:{}",
                        JSON.toJSONString(subjectInfoDTO));
            }
            //健壮性判断
            Preconditions.checkNotNull(subjectInfoDTO.getId(), "题目id不能为空");

            SubjectInfoBO subjectInfoBO = SubjectInfoBOConverter.INSTANCE.subjectInfoDtoToBo(subjectInfoDTO);

            //类型转换
            SubjectInfoDetailBO subjectInfoDetailBO = subjectInfoDomainService.subjectDetail(subjectInfoBO);
            SubjectInfoDetailVO subjectInfoDetailVO = SubjectInfoDetailBOConverter.INSTANCE.subjectInfoDetailBoToVo(subjectInfoDetailBO);
            List<OptionListBO> optionListBOS = subjectInfoDetailBO.getOptionListBOS();
            List<OptionListVO> optionListVOS = SubjectOptionsBOConverter.INSTANCE.subjectOptionsBoToVo(optionListBOS);
            subjectInfoDetailVO.setOptionListVOS(optionListVOS);

            return Result.ok(subjectInfoDetailVO);
        } catch (Exception e) {
            log.error("查询失败,e.message：{}", e.getMessage(), e);
            return Result.fail(e.getMessage());
        }
    }

    /**
     * 更新题目
     *
     * @param subjectInfoDTO 主题信息 DTO
     * @return {@link Result }<{@link Boolean }>
     */
    @PutMapping("/update")
    public Result<Boolean> update(@RequestBody SubjectInfoDTO subjectInfoDTO) {
        try {
            //如果当前日志级别高于 Info（如设置为 Warn、Error），log.info 不会被执行。
            if (log.isInfoEnabled()) {
                log.info("SubjectInfoController.update.dto:{}",
                        JSON.toJSONString(subjectInfoDTO));
            }
            //健壮性判断
            Preconditions.checkNotNull(subjectInfoDTO.getId(), "题目ID不能为空");

            //dto转bo
            SubjectInfoBO subjectInfoBO = SubjectInfoBOConverter.INSTANCE.subjectInfoDtoToBo(subjectInfoDTO);
            List<OptionListDTO> optionListDTOS = subjectInfoDTO.getOptionListDTOS();
            List<OptionListBO> optionListBOS = BeanUtil.copyToList(optionListDTOS, OptionListBO.class);
            subjectInfoBO.setOptionListBOS(optionListBOS);

            subjectInfoDomainService.update(subjectInfoBO);
            return Result.ok("修改成功");
        } catch (Exception e) {
            log.error("异常信息{}", e.getMessage());
            return Result.fail(e.getMessage());
        }
    }


    /**
     * 删除
     *
     * @param id 身份证
     * @return {@link Result }<{@link Boolean }>
     */
    @DeleteMapping("/delete")
    public Result<Boolean> delete(@RequestParam Integer id) {
        try {
            //如果当前日志级别高于 Info（如设置为 Warn、Error），log.info 不会被执行。
            if (log.isInfoEnabled()) {
                log.info("SubjectInfoController.delete.id:{}",
                        JSON.toJSONString(id));
            }
            //健壮性判断
            Preconditions.checkNotNull(id, "题目ID不能为空");

            subjectInfoDomainService.delete(id);
            return Result.ok("删除成功");
        } catch (Exception e) {
            log.error("异常信息{}", e.getMessage());
            return Result.fail(e.getMessage());
        }
    }


}
