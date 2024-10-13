package com.zxm.club.auth.application.controller;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.google.common.base.Preconditions;
import com.zxm.club.auth.application.convert.AuthUserBOConverter;
import com.zxm.club.auth.application.dto.AuthUserDTO;
import com.zxm.club.auth.application.vo.AuthUserVO;
import com.zxm.club.auth.common.entity.Result;
import com.zxm.club.auth.domain.entity.AuthUserBO;
import com.zxm.club.auth.domain.service.impl.AuthUserDomainService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


@RestController
@RequestMapping("/auth/user")
@Slf4j
public class AuthUserController {

    @Resource
    private AuthUserDomainService authUserDomainService;


    /**
     * 注册用户
     *
     * @param authUserDTO auth 用户 dto
     * @return {@link Result }<{@link Boolean }>
     */
    @PostMapping("/register")
    public Result<Boolean> register(@RequestBody AuthUserDTO authUserDTO) {
        try {
            //如果当前日志级别高于 Info（如设置为 Warn、Error），log.info 不会被执行。
            if (log.isInfoEnabled()) {
                log.info("UserController.add.dto:{}",
                        JSON.toJSONString(authUserDTO));
            }

            //健壮性判断
            Preconditions.checkArgument(StrUtil.isNotBlank(authUserDTO.getUsername())
                    , "账号不能为空");
            Preconditions.checkArgument(StrUtil.isNotBlank(authUserDTO.getPassword())
                    , "密码不能为空");
            Preconditions.checkArgument(StrUtil.isNotBlank(authUserDTO.getEmail())
                    , "邮箱不能为空");
            Preconditions.checkArgument(StrUtil.isNotBlank(authUserDTO.getPhone())
                    , "电话不能为空");


            //dto转bo
            AuthUserBO authUserBO = AuthUserBOConverter
                    .INSTANCE.convertToAuthUserBO(authUserDTO);
            authUserDomainService.insert(authUserBO);

            return Result.ok("注册成功");
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
    public Result<Boolean> delete(@RequestParam Long id) {
        try {
            //如果当前日志级别高于 Info（如设置为 Warn、Error），log.info 不会被执行。
            if (log.isInfoEnabled()) {
                log.info("UserController.delete.dto:{}",
                        JSON.toJSONString(id));
            }
            //健壮性判断
            Preconditions.checkNotNull(id, "用户id不能为空");

            authUserDomainService.delete(id);

            return Result.ok("删除成功");
        } catch (Exception e) {
            log.error("异常信息{}", e.getMessage());
            return Result.fail(e.getMessage());
        }
    }

    /**
     * 更新用户信息
     *
     * @param authUserDTO auth 用户 dto
     * @return {@link Result }<{@link Boolean }>
     */
    @PutMapping("/update")
    public Result<Boolean> update(@RequestBody AuthUserDTO authUserDTO) {
        try {
            //如果当前日志级别高于 Info（如设置为 Warn、Error），log.info 不会被执行。
            if (log.isInfoEnabled()) {
                log.info("UserController.update.dto:{}",
                        JSON.toJSONString(authUserDTO));
            }

            //健壮性判断
            Preconditions.checkNotNull(authUserDTO.getId(), "用户id不能为空");

            //dto转bo
            AuthUserBO authUserBO = AuthUserBOConverter
                    .INSTANCE.convertToAuthUserBO(authUserDTO);
            authUserDomainService.update(authUserBO);

            return Result.ok("修改成功");
        } catch (Exception e) {
            log.error("异常信息{}", e.getMessage());
            return Result.fail(e.getMessage());
        }
    }

    /**
     * 禁用和启用
     *
     * @param id 身份证
     * @return {@link Result }<{@link Boolean }>
     */
    @GetMapping("/status")
    public Result<Boolean> status(@RequestParam Long id) {
        try {
            //如果当前日志级别高于 Info（如设置为 Warn、Error），log.info 不会被执行。
            if (log.isInfoEnabled()) {
                log.info("UserController.status.dto:{}",
                        JSON.toJSONString(id));
            }
            //健壮性判断
            Preconditions.checkNotNull(id, "用户id不能为空");

            authUserDomainService.updateStatus(id);

            return Result.ok("修改成功");
        } catch (Exception e) {
            log.error("异常信息{}", e.getMessage());
            return Result.fail(e.getMessage());
        }
    }


    /**
     * 查询用户信息
     *
     * @param id 身份证
     * @return {@link Result }<{@link Boolean }>
     */
    @GetMapping("/info")
    public Result<Boolean> info(@RequestParam Long id) {
        try {
            //如果当前日志级别高于 Info（如设置为 Warn、Error），log.info 不会被执行。
            if (log.isInfoEnabled()) {
                log.info("UserController.info.dto:{}",
                        JSON.toJSONString(id));
            }
            //健壮性判断
            Preconditions.checkNotNull(id, "用户id不能为空");

            AuthUserBO authUserBO = authUserDomainService.queryById(id);
            AuthUserVO authUserVO = AuthUserBOConverter.INSTANCE.convertToAuthUserVO(authUserBO);

            return Result.ok(authUserVO);
        } catch (Exception e) {
            log.error("异常信息{}", e.getMessage());
            return Result.fail(e.getMessage());
        }
    }

    // 测试登录，浏览器访问： http://localhost:4003/user/doLogin?username=zhang&password=123456
    @GetMapping("/doLogin")
    public Result<SaTokenInfo> doLogin(@RequestParam("code") String code) {
        try {
            //如果当前日志级别高于 Info（如设置为 Warn、Error），log.info 不会被执行。
            if (log.isInfoEnabled()) {
                log.info("UserController.doLogin.code:{}",
                        JSON.toJSONString(code));
            }

            Preconditions.checkNotNull(code,"验证码不能为空");

            this.authUserDomainService.doLogin(code);
            SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
            return Result.ok(tokenInfo);
        } catch (Exception e) {
            log.error("异常信息{}", e.getMessage());
            return Result.fail(e.getMessage());
        }
    }

    // 查询登录状态，浏览器访问： http://localhost:8081/user/isLogin
    @RequestMapping("/isLogin")
    public String isLogin() {
        return "当前会话是否登录：" + StpUtil.isLogin();
    }

}
