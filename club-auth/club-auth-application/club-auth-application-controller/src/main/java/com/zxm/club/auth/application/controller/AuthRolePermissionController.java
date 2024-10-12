package com.zxm.club.auth.application.controller;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.google.common.base.Preconditions;
import com.zxm.club.auth.application.convert.AuthPermissionBOConverter;
import com.zxm.club.auth.application.convert.AuthRoleBOConverter;
import com.zxm.club.auth.application.convert.AuthRolePermissionBOConverter;
import com.zxm.club.auth.application.dto.AuthPermissionDTO;
import com.zxm.club.auth.application.dto.AuthPermissionRoleDTO;
import com.zxm.club.auth.common.entity.Result;
import com.zxm.club.auth.domain.entity.AuthPermissionBO;
import com.zxm.club.auth.domain.entity.AuthPermissionRoleBO;
import com.zxm.club.auth.domain.service.impl.AuthPermissionDomainService;
import com.zxm.club.auth.domain.service.impl.AuthRolePermissionDomainService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


@RestController
@RequestMapping("/auth/role/permission")
@Slf4j
public class AuthRolePermissionController {

    @Resource
    private AuthRolePermissionDomainService authRolePermissionDomainService;


    /**
     * 新增角色的权限
     *
     * @param authPermissionRoleDTOList auth 权限 role dtolist
     * @return {@link Result }<{@link Boolean }>
     */
    @PostMapping("/add")
    public Result<Boolean> add(@RequestBody List<AuthPermissionRoleDTO> authPermissionRoleDTOList) {
        try {
            //如果当前日志级别高于 Info（如设置为 Warn、Error），log.info 不会被执行。
            if (log.isInfoEnabled()) {
                log.info("AuthRoleController.add.dto:{}",
                        JSON.toJSONString(authPermissionRoleDTOList));
            }

            //健壮性判断
            Preconditions.checkArgument(authPermissionRoleDTOList.size() > 0
                    , "至少给角色赋予一个权限！");

            //dto转bo
            List<AuthPermissionRoleBO> authPermissionRoleBOS = AuthRolePermissionBOConverter
                    .INSTANCE.convertToAuthRolePermissions(authPermissionRoleDTOList);
            authRolePermissionDomainService.insert(authPermissionRoleBOS);

            return Result.ok("添加角色权限成功");
        } catch (Exception e) {
            log.error("异常信息{}", e.getMessage());
            return Result.fail(e.getMessage());
        }
    }

}
