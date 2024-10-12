package com.zxm.club.auth.application.controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.google.common.base.Preconditions;
import com.zxm.club.auth.application.convert.AuthPermissionBOConverter;
import com.zxm.club.auth.application.convert.AuthUserBOConverter;
import com.zxm.club.auth.application.dto.AuthPermissionDTO;
import com.zxm.club.auth.application.dto.AuthPermissionRoleDTO;
import com.zxm.club.auth.application.dto.AuthUserDTO;
import com.zxm.club.auth.application.vo.AuthUserVO;
import com.zxm.club.auth.common.entity.Result;
import com.zxm.club.auth.domain.entity.AuthPermissionBO;
import com.zxm.club.auth.domain.entity.AuthUserBO;
import com.zxm.club.auth.domain.service.impl.AuthPermissionDomainService;
import com.zxm.club.auth.domain.service.impl.AuthUserDomainService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


@RestController
@RequestMapping("/auth/permission")
@Slf4j
public class AuthPermissionController {

    @Resource
    private AuthPermissionDomainService authPermissionDomainService;


    /**
     * 添加权限
     *
     * @param authPermissionDTO auth 权限 dto
     * @return {@link Result }<{@link Boolean }>
     */
    @PostMapping("/add")
    public Result<Boolean> add(@RequestBody AuthPermissionDTO authPermissionDTO) {
        try {
            //如果当前日志级别高于 Info（如设置为 Warn、Error），log.info 不会被执行。
            if (log.isInfoEnabled()) {
                log.info("AuthPermissionController.add.dto:{}",
                        JSON.toJSONString(authPermissionDTO));
            }

            //健壮性判断
            Preconditions.checkArgument(StrUtil.isNotBlank(authPermissionDTO.getName())
                    , "权限名称不能为空");
            Preconditions.checkNotNull(authPermissionDTO.getType(),"权限类型不能为空");
            Preconditions.checkNotNull(authPermissionDTO.getShow(),"权限是否展示不能为空");
            Preconditions.checkNotNull(authPermissionDTO.getParentId(),"权限父级id不能为空");

            //dto转bo
            AuthPermissionBO authPermissionBO = AuthPermissionBOConverter
                    .INSTANCE.convertToAuthPermissionBO(authPermissionDTO);
            authPermissionDomainService.insert(authPermissionBO);

            return Result.ok("添加成功");
        } catch (Exception e) {
            log.error("异常信息{}", e.getMessage());
            return Result.fail(e.getMessage());
        }
    }

    /**
     * 删除权限
     *
     * @param id 身份证
     * @return {@link Result }<{@link Boolean }>
     */
    @DeleteMapping("/delete")
    public Result<Boolean> delete(@RequestParam Long id) {
        try {
            //如果当前日志级别高于 Info（如设置为 Warn、Error），log.info 不会被执行。
            if (log.isInfoEnabled()) {
                log.info("AuthPermissionController.delete.dto:{}",
                        JSON.toJSONString(id));
            }

            //健壮性判断
            Preconditions.checkNotNull(id,"权限id不能为空");

            authPermissionDomainService.delete(id);

            return Result.ok("删除成功");
        } catch (Exception e) {
            log.error("异常信息{}", e.getMessage());
            return Result.fail(e.getMessage());
        }
    }

    /**
     * 更新权限
     *
     * @param authPermissionDTO auth 权限 dto
     * @return {@link Result }<{@link Boolean }>
     */
    @PutMapping("/update")
    public Result<Boolean> update(@RequestBody AuthPermissionDTO authPermissionDTO) {
        try {
            //如果当前日志级别高于 Info（如设置为 Warn、Error），log.info 不会被执行。
            if (log.isInfoEnabled()) {
                log.info("AuthPermissionController.update.dto:{}",
                        JSON.toJSONString(authPermissionDTO));
            }

            //健壮性判断
            Preconditions.checkNotNull(authPermissionDTO.getId(),"权限id不能为空");

            //类型转换
            AuthPermissionBO authPermissionBO = AuthPermissionBOConverter.INSTANCE.convertToAuthPermissionBO(authPermissionDTO);

            authPermissionDomainService.update(authPermissionBO);

            return Result.ok("修改成功");
        } catch (Exception e) {
            log.error("异常信息{}", e.getMessage());
            return Result.fail(e.getMessage());
        }
    }

    /**
     * 修改状态是否启用
     *
     * @param authPermissionDTO auth 权限 dto
     * @return {@link Result }<{@link Boolean }>
     */
    @PutMapping("/status")
    public Result<Boolean> status(@RequestBody AuthPermissionDTO authPermissionDTO) {
        try {
            //如果当前日志级别高于 Info（如设置为 Warn、Error），log.info 不会被执行。
            if (log.isInfoEnabled()) {
                log.info("AuthPermissionController.status.dto:{}",
                        JSON.toJSONString(authPermissionDTO));
            }

            //健壮性判断
            Preconditions.checkNotNull(authPermissionDTO.getId(),"权限id不能为空");

            //类型转换
            AuthPermissionBO authPermissionBO = AuthPermissionBOConverter.INSTANCE.convertToAuthPermissionBO(authPermissionDTO);

            authPermissionDomainService.status(authPermissionBO);

            return Result.ok("修改状态成功");
        } catch (Exception e) {
            log.error("异常信息{}", e.getMessage());
            return Result.fail(e.getMessage());
        }
    }

    /**
     * 权限是否展示
     *
     * @param authPermissionDTO auth 权限 dto
     * @return {@link Result }<{@link Boolean }>
     */
    @PostMapping("/show")
    public Result<Boolean> show(@RequestBody AuthPermissionDTO authPermissionDTO) {
        try {
            //如果当前日志级别高于 Info（如设置为 Warn、Error），log.info 不会被执行。
            if (log.isInfoEnabled()) {
                log.info("AuthPermissionController.show.dto:{}",
                        JSON.toJSONString(authPermissionDTO));
            }

            //健壮性判断
            Preconditions.checkNotNull(authPermissionDTO.getId(),"权限id不能为空");

            //类型转换
            AuthPermissionBO authPermissionBO = AuthPermissionBOConverter.INSTANCE.convertToAuthPermissionBO(authPermissionDTO);

            authPermissionDomainService.show(authPermissionBO);

            return Result.ok("修改状态成功");
        } catch (Exception e) {
            log.error("异常信息{}", e.getMessage());
            return Result.fail(e.getMessage());
        }
    }






}
