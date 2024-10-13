package com.zxm.club.wx.handler;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.RandomUtil;
import com.zxm.club.wx.enums.MsgTypeEnum;
import com.zxm.club.wx.redis.RedisUtil;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Component
public class TextStrategy implements MsgTypeStrategy{

    @Resource
    private RedisUtil redisUtil;

    private final String captchaPrefix = "code";

    @Override
    public MsgTypeEnum getMsgType() {
        return MsgTypeEnum.TEXT;
    }

    @Override
    public String getMsgTypeString(Map<String, String> messageMap) {
        String fromUserName = messageMap.get("FromUserName");
        String toUserName = messageMap.get("ToUserName");
        if (!messageMap.get("Content").equals("验证码")) {
            return "<xml>\n" +
                    "  <ToUserName><![CDATA[" + fromUserName + "]]></ToUserName>\n" +
                    "  <FromUserName><![CDATA[" + toUserName + "]]></FromUserName>\n" +
                    "  <CreateTime>12345678</CreateTime>\n" +
                    "  <MsgType><![CDATA[text]]></MsgType>\n" +
                    "  <Content><![CDATA[当前逻辑未开发]]></Content>\n" +
                    "</xml>";
        }
        String captcha = RandomUtil.randomString(6); //验证码
        // 把验证码放到缓存中,时效为5分钟
        String codeKey = redisUtil.buildKey(captchaPrefix, captcha);
        redisUtil.setNx(codeKey,fromUserName,5L, TimeUnit.MINUTES);
        return "<xml>\n" +
                "  <ToUserName><![CDATA[" + fromUserName + "]]></ToUserName>\n" +
                "  <FromUserName><![CDATA[" + toUserName + "]]></FromUserName>\n" +
                "  <CreateTime>12345678</CreateTime>\n" +
                "  <MsgType><![CDATA[text]]></MsgType>\n" +
                "  <Content><![CDATA[验证码为："+captcha+" 五分钟内有效]]></Content>\n" +
                "</xml>";
    }
}
