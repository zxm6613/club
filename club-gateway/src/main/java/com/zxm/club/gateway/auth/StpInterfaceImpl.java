package com.zxm.club.gateway.auth;

import cn.dev33.satoken.stp.StpInterface;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义权限验证接口扩展 
 */
@Component
public class StpInterfaceImpl implements StpInterface {

    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        // 返回此 loginId 拥有的权限列表,无论是什么 loginId 登录，都会自动拥有 "user:add" 权限，因为没有对具体的用户权限进行动态的判断。
        List<String> permissionList = new ArrayList<String>();
        permissionList.add("user:add");
        return permissionList;
    }

    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        // 返回此 loginId 拥有的角色列表
        List<String> roleList = new ArrayList<String>();
        roleList.add("admin");
        return roleList;
    }

}
