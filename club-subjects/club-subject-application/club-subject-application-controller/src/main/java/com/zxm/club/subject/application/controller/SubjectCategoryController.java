package com.zxm.club.subject.application.controller;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.google.common.base.Preconditions;
import com.zxm.club.subject.application.convert.SubjectCategoryBOConverter;
import com.zxm.club.subject.application.dto.SubjectCategoryDto;
import com.zxm.club.subject.application.vo.SubjectCategoryVO;
import com.zxm.club.subject.common.entity.Result;
import com.zxm.club.subject.domain.convert.SubjectCategoryConverter;
import com.zxm.club.subject.domain.entity.SubjectCategoryBO;
import com.zxm.club.subject.domain.service.SubjectCategoryDomainService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 主题类别控制器
 *
 * @author zxm
 * @date 2024/10/02
 */
@RestController
@RequestMapping("/subject/category")
@Slf4j
public class SubjectCategoryController {

    @Autowired
    private SubjectCategoryDomainService subjectCategoryDomainService;


    /**
     * 新增分类
     *
     * @return {@link Result }
     */
    @PostMapping("/add")
    public Result add(@RequestBody SubjectCategoryDto subjectCategoryDto) {
        try {
            //如果当前日志级别高于 Info（如设置为 Warn、Error），log.info 不会被执行。
            if (log.isInfoEnabled()) {
                log.info("SubjectCategoryController.add.dto:{}",
                        JSON.toJSONString(subjectCategoryDto));
            }

            //健壮性判断
            Preconditions.checkArgument(StrUtil.isNotBlank(subjectCategoryDto.getCategoryName()), "分类名称不能为空");
            Preconditions.checkNotNull(subjectCategoryDto.getCategoryType(), "分类类型不能为空");
            Preconditions.checkNotNull(subjectCategoryDto.getParentId(), "分类父级id不能为空");

            SubjectCategoryBO subjectCategoryBO = SubjectCategoryBOConverter
                    .INSTANCE.convertToSubjectCategoryBO(subjectCategoryDto);
            subjectCategoryDomainService.add(subjectCategoryBO);
            return Result.ok();
        } catch (Exception e) {
            log.error("异常信息{}", e.getMessage());
            return Result.fail(e.getMessage());
        }
    }

    /**
     * 查询类别列表
     *
     * @return {@link Result }<{@link SubjectCategoryVO }>
     */
    @GetMapping("/queryPrimaryCategory")
    public Result<List<SubjectCategoryVO>> queryCategoryList() {
        try {

            List<SubjectCategoryBO> categoryBOList = subjectCategoryDomainService.queryCategoryList();
            if (CollUtil.isEmpty(categoryBOList)) {
                return Result.fail("没有查到相关数据");
            }
            List<SubjectCategoryVO> categoryVOList = BeanUtil
                    .copyToList(categoryBOList, SubjectCategoryVO.class);
            return Result.ok(categoryVOList);
        } catch (Exception e) {
            log.error("查询失败,e.message：{}", e.getMessage(), e);
            return Result.fail("查询失败");
        }
    }

    /**
     * 查询小类别列表
     *
     * @return {@link Result }<{@link List }<{@link SubjectCategoryVO }>>
     */
    @PostMapping("/queryMiddleCategory")
    public Result<List<SubjectCategoryVO>> querySmallCategoryList(@RequestBody SubjectCategoryDto subjectCategoryDto) {
        try {
            //如果当前日志级别高于 Info（如设置为 Warn、Error），log.info 不会被执行。
            if (log.isInfoEnabled()) {
                log.info("SubjectCategoryController.add.dto:{}",
                        JSON.toJSONString(subjectCategoryDto));
            }
            //健壮性判断,分类id不能为空
            Preconditions.checkNotNull(subjectCategoryDto.getId(), "分类id不能为空");

            //bo转化
            SubjectCategoryBO subjectCategoryBO = SubjectCategoryBOConverter.INSTANCE.convertToSubjectCategoryBO(subjectCategoryDto);

            List<SubjectCategoryBO> subjectCategoryBOS =
                    subjectCategoryDomainService.queryCategory(subjectCategoryBO);

            if (CollUtil.isEmpty(subjectCategoryBOS)) {
                return Result.fail("没有查到分类数据");
            }

            //bo转vo
            List<SubjectCategoryVO> categoryVOList = BeanUtil.copyToList(subjectCategoryBOS, SubjectCategoryVO.class);
            return Result.ok(categoryVOList);
        } catch (Exception e) {
            log.error("SubjectCategoryController.add.error{}", e.getMessage(), e);
            return Result.fail(e.getMessage());
        }
    }


    /**
     * 更新分类
     *
     * @param subjectCategoryDto 主题类别 DTO
     * @return {@link Result }<{@link Boolean }>
     */
    @PutMapping("/update")
    public Result<Boolean> update(@RequestBody SubjectCategoryDto subjectCategoryDto) {
        try {
            //如果当前日志级别高于 Info（如设置为 Warn、Error），log.info 不会被执行。
            if (log.isInfoEnabled()) {
                log.info("SubjectCategoryController.update.dto:{}",
                        JSON.toJSONString(subjectCategoryDto));
            }
            //健壮性判断,分类id不能为空
            Preconditions.checkNotNull(subjectCategoryDto.getId(), "分类id不能为空");

            //bo转化
            SubjectCategoryBO subjectCategoryBO = SubjectCategoryBOConverter.INSTANCE.convertToSubjectCategoryBO(subjectCategoryDto);

            boolean success = subjectCategoryDomainService.update(subjectCategoryBO);

            return Result.ok(success);
        } catch (Exception e) {
            log.error("SubjectCategoryController.update.error:{};", e.getMessage(), e);
            return Result.fail(e.getMessage());
        }
    }

    /**
     * 删除分类
     *
     * @param ids IDS
     * @return {@link Result }<{@link Boolean }>
     */
    @DeleteMapping("/delete/{ids}")
    public Result<Boolean> delete(@PathVariable List<Long> ids) {
        try {
            //如果当前日志级别高于 Info（如设置为 Warn、Error），log.info 不会被执行。
            if (log.isInfoEnabled()) {
                log.info("SubjectCategoryController.delete.ids:{}",
                        JSON.toJSONString(ids));
            }
            //健壮性判断,分类id不能为空
            Preconditions.checkNotNull(ids, "分类id不能为空");

            boolean success = subjectCategoryDomainService.delete(ids);

            return Result.ok(success);
        } catch (Exception e) {
            log.error("SubjectCategoryController.delete.ids:{};", e.getMessage(), e);
            return Result.fail(e.getMessage());
        }
    }


}
