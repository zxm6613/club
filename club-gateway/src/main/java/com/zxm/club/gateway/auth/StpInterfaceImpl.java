package com.zxm.club.gateway.auth;

import cn.dev33.satoken.stp.StpInterface;
import com.alibaba.nacos.api.utils.StringUtils;
import com.google.gson.Gson;
import com.zxm.club.gateway.redis.RedisUtil;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

/**
 * 自定义权限验证接口扩展
 */
@Component
public class StpInterfaceImpl implements StpInterface {

    @Resource
    private  RedisUtil redisUtil;

    private final String authPrefix = "auth";
    private final String rolePrefix = "role";

    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        return getList(loginId.toString(), authPrefix);
    }

    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        return getList(loginId.toString(), rolePrefix);
    }


    //loginId就是账号，或者唯一标识
    private List<String> getList(String loginId, String prefix) {
        String authKey = redisUtil.buildKey(prefix, loginId.toString());
        String authValue = redisUtil.get(authKey);
        if (StringUtils.isBlank(authValue)) {
            return Collections.emptyList();
        }
        //本来解析出来是value的json字符串形式，所以需要解析一下，list中村的就是value的集合，没有key
        List<String> list = new Gson().fromJson(authValue, List.class);
        return list;
    }

}
