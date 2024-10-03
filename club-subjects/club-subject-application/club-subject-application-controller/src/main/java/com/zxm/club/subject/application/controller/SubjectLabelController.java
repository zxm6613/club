package com.zxm.club.subject.application.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.google.common.base.Preconditions;
import com.zxm.club.subject.application.convert.SubjectCategoryBOConverter;
import com.zxm.club.subject.application.convert.SubjectLabelBOConverter;
import com.zxm.club.subject.application.dto.SubjectCategoryDto;
import com.zxm.club.subject.application.dto.SubjectLabelDTO;
import com.zxm.club.subject.application.vo.SubjectCategoryVO;
import com.zxm.club.subject.application.vo.SubjectLabelVO;
import com.zxm.club.subject.common.entity.Result;
import com.zxm.club.subject.domain.entity.SubjectCategoryBO;
import com.zxm.club.subject.domain.entity.SubjectLabelBO;
import com.zxm.club.subject.domain.service.SubjectLabelDomainService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 主题标签控制器
 *
 * @author zxm
 * @date 2024/10/03
 */
@RestController
@RequestMapping("/subject/label")
@Slf4j
public class SubjectLabelController {

    @Autowired
    private SubjectLabelDomainService subjectLabelDomainService;


    /**
     * 新增标签
     *
     * @return {@link Result }
     */
    @PostMapping("/add")
    public Result<Boolean> add(@RequestBody SubjectLabelDTO subjectLabelDTO) {
        try {
            //如果当前日志级别高于 Info（如设置为 Warn、Error），log.info 不会被执行。
            if (log.isInfoEnabled()) {
                log.info("SubjectLabelController.add.dto:{}",
                        JSON.toJSONString(subjectLabelDTO));
            }

            //健壮性判断
            Preconditions.checkNotNull(subjectLabelDTO.getCategoryId(),"标签的分类id不能为空");
            Preconditions.checkArgument(StrUtil.isNotBlank(subjectLabelDTO.getLabelName()),"标签名称不能为空");

            SubjectLabelBO subjectLabelBO = SubjectLabelBOConverter.INSTANCE.getSubjectLabelBO(subjectLabelDTO);
            subjectLabelDomainService.add(subjectLabelBO);
            return Result.ok(true);
        } catch (Exception e) {
            log.error("异常信息{}", e.getMessage());
            return Result.fail(e.getMessage());
        }
    }


    /**
     * 查询标签列表
     *
     * @param categoryId 类别 ID
     * @return {@link Result }<{@link List }<{@link SubjectLabelVO }>>
     */
    @GetMapping("/queryLabelByCategoryId")
    public Result<List<SubjectLabelVO>> queryLabelList(@RequestParam Long categoryId) {
        try {
            //如果当前日志级别高于 Info（如设置为 Warn、Error），log.info 不会被执行。
            if (log.isInfoEnabled()) {
                log.info("SubjectLabelController.queryLabelByCategoryId.categoryId:{}",
                        JSON.toJSONString(categoryId));
            }
            //健壮性判断
            Preconditions.checkNotNull(categoryId, "分类id不能为空");

            List<SubjectLabelBO> labelBOS = subjectLabelDomainService.queryLabelList(categoryId);
            if (CollUtil.isEmpty(labelBOS)) {
                throw new RuntimeException("没有查到标签数据");
            }

            List<SubjectLabelVO> labelVOS = BeanUtil
                    .copyToList(labelBOS, SubjectLabelVO.class);
            return Result.ok(labelVOS);
        } catch (Exception e) {
            log.error("查询失败,e.message：{}", e.getMessage(), e);
            return Result.fail(e.getMessage());
        }
    }


    /**
     * 更新标签
     *
     * @param subjectLabelDTO 主题标签 DTO
     * @return {@link Result }<{@link Boolean }>
     */
    @PutMapping("/update")
    public Result<Boolean> update(@RequestBody SubjectLabelDTO subjectLabelDTO) {
        try {
            //如果当前日志级别高于 Info（如设置为 Warn、Error），log.info 不会被执行。
            if (log.isInfoEnabled()) {
                log.info("SubjectLabelController.update.subjectLabelDTO:{}",
                        JSON.toJSONString(subjectLabelDTO));
            }
            //健壮性判断,分类id不能为空
            Preconditions.checkNotNull(subjectLabelDTO.getId(), "标签id不能为空");

            //bo转化
            SubjectLabelBO subjectLabelBO = SubjectLabelBOConverter.INSTANCE.getSubjectLabelBO(subjectLabelDTO);

            boolean success = subjectLabelDomainService.update(subjectLabelBO);

            return Result.ok(success);
        } catch (Exception e) {
            log.error("SubjectLabelController.update.error:{};", e.getMessage(), e);
            return Result.fail(e.getMessage());
        }
    }

    /**
     * 删除标签
     *
     * @param ids IDS
     * @return {@link Result }<{@link Boolean }>
     */
    @DeleteMapping("/delete/{ids}")
    public Result<Boolean> delete(@PathVariable List<Long> ids) {
        try {
            //如果当前日志级别高于 Info（如设置为 Warn、Error），log.info 不会被执行。
            if (log.isInfoEnabled()) {
                log.info("SubjectLabelController.delete.ids:{}",
                        JSON.toJSONString(ids));
            }
            //健壮性判断,分类id不能为空
            Preconditions.checkNotNull(ids, "分类id不能为空");

            boolean success = subjectLabelDomainService.delete(ids);

            return Result.ok(success);
        } catch (Exception e) {
            log.error("SubjectLabelController.delete.ids:{};", e.getMessage(), e);
            return Result.fail(e.getMessage());
        }
    }


}
