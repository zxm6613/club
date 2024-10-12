package com.zxm.club.auth.application.controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.google.common.base.Preconditions;
import com.zxm.club.auth.application.convert.AuthRoleBOConverter;
import com.zxm.club.auth.application.convert.AuthUserBOConverter;
import com.zxm.club.auth.application.dto.AuthPermissionRoleDTO;
import com.zxm.club.auth.application.dto.AuthRoleDTO;
import com.zxm.club.auth.application.dto.AuthUserDTO;
import com.zxm.club.auth.application.vo.AuthUserVO;
import com.zxm.club.auth.common.entity.Result;
import com.zxm.club.auth.domain.entity.AuthPermissionRoleBO;
import com.zxm.club.auth.domain.entity.AuthRoleBO;
import com.zxm.club.auth.domain.entity.AuthUserBO;
import com.zxm.club.auth.domain.service.impl.AuthRoleDomainService;
import com.zxm.club.auth.domain.service.impl.AuthUserDomainService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


@RestController
@RequestMapping("/auth/role")
@Slf4j
public class AuthRoleController {

    @Resource
    private AuthRoleDomainService authRoleDomainService;


    /**
     * 新增角色
     *
     * @param authRoleDTO 身份验证角色 DTO
     * @return {@link Result }<{@link Boolean }>
     */
    @PostMapping("/add")
    public Result<Boolean> add(@RequestBody AuthRoleDTO authRoleDTO) {
        try {
            //如果当前日志级别高于 Info（如设置为 Warn、Error），log.info 不会被执行。
            if (log.isInfoEnabled()) {
                log.info("AuthRoleController.add.dto:{}",
                        JSON.toJSONString(authRoleDTO));
            }

            //健壮性判断
            Preconditions.checkArgument(StrUtil.isNotBlank(authRoleDTO.getRoleName())
                    , "角色名称不能为空");

            //dto转bo
            AuthRoleBO authRoleBO = AuthRoleBOConverter
                    .INSTANCE.convertToAuthUserBO(authRoleDTO);
            authRoleDomainService.insert(authRoleBO);

            return Result.ok("添加角色成功");
        } catch (Exception e) {
            log.error("异常信息{}", e.getMessage());
            return Result.fail(e.getMessage());
        }
    }

    /**
     * 更新角色
     *
     * @param authRoleDTO 身份验证角色 DTO
     * @return {@link Result }<{@link Boolean }>
     */
    @PutMapping("/update")
    public Result<Boolean> update(@RequestBody AuthRoleDTO authRoleDTO) {
        try {
            //如果当前日志级别高于 Info（如设置为 Warn、Error），log.info 不会被执行。
            if (log.isInfoEnabled()) {
                log.info("AuthRoleController.update.dto:{}",
                        JSON.toJSONString(authRoleDTO));
            }

            //健壮性判断
            Preconditions.checkNotNull(authRoleDTO.getId(),"角色id不能为空");

            //dto转bo
            AuthRoleBO authRoleBO = AuthRoleBOConverter
                    .INSTANCE.convertToAuthUserBO(authRoleDTO);
            authRoleDomainService.update(authRoleBO);

            return Result.ok("修改角色成功");
        } catch (Exception e) {
            log.error("异常信息{}", e.getMessage());
            return Result.fail(e.getMessage());
        }
    }


    /**
     * 删除角色
     *
     * @param id 身份证
     * @return {@link Result }<{@link Boolean }>
     */
    @DeleteMapping("/delete")
    public Result<Boolean> delete(@RequestParam Long id) {
        try {
            //如果当前日志级别高于 Info（如设置为 Warn、Error），log.info 不会被执行。
            if (log.isInfoEnabled()) {
                log.info("AuthRoleController.delete.dto:{}",
                        JSON.toJSONString(id));
            }

            //健壮性判断
            Preconditions.checkNotNull(id,"角色id不能为空");

            authRoleDomainService.delete(id);

            return Result.ok("删除角色成功");
        } catch (Exception e) {
            log.error("异常信息{}", e.getMessage());
            return Result.fail(e.getMessage());
        }
    }


    @PutMapping("/status")
    public Result<Boolean> status(@RequestBody AuthRoleDTO authRoleDTO) {
        try {
            //如果当前日志级别高于 Info（如设置为 Warn、Error），log.info 不会被执行。
            if (log.isInfoEnabled()) {
                log.info("AuthRoleController.status.dto:{}",
                        JSON.toJSONString(authRoleDTO));
            }

            //健壮性判断
            Preconditions.checkNotNull(authRoleDTO.getId(),"角色id不能为空");

            //dto转bo
            AuthRoleBO authRoleBO = AuthRoleBOConverter
                    .INSTANCE.convertToAuthUserBO(authRoleDTO);

            authRoleDomainService.status(authRoleBO);

            return Result.ok("修改角色状态成功");
        } catch (Exception e) {
            log.error("异常信息{}", e.getMessage());
            return Result.fail(e.getMessage());
        }
    }


}
