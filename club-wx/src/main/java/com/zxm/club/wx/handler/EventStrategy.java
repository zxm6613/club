package com.zxm.club.wx.handler;

import com.zxm.club.wx.enums.MsgTypeEnum;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class EventStrategy implements MsgTypeStrategy{
    @Override
    public MsgTypeEnum getMsgType() {
        return MsgTypeEnum.EVENT;
    }

    @Override
    public String getMsgTypeString(Map<String, String> messageMap) {
        String fromUserName = messageMap.get("FromUserName");
        String toUserName = messageMap.get("ToUserName");
        if (!messageMap.get("Event").equals("subscribe")){
            return "<xml>\n" +
                    "  <ToUserName><![CDATA[" + fromUserName + "]]></ToUserName>\n" +
                    "  <FromUserName><![CDATA[" + toUserName + "]]></FromUserName>\n" +
                    "  <CreateTime>12345678</CreateTime>\n" +
                    "  <MsgType><![CDATA[text]]></MsgType>\n" +
                    "  <Content><![CDATA[当前逻辑未开发]]></Content>\n" +
                    "</xml>";
        }
        return "<xml>\n" +
                "  <ToUserName><![CDATA[" + fromUserName + "]]></ToUserName>\n" +
                "  <FromUserName><![CDATA[" + toUserName + "]]></FromUserName>\n" +
                "  <CreateTime>12345678</CreateTime>\n" +
                "  <MsgType><![CDATA[text]]></MsgType>\n" +
                "  <Content><![CDATA[扫码成功]]></Content>\n" +
                "</xml>";
    }
}
