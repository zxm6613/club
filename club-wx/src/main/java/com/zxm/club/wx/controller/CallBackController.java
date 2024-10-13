package com.zxm.club.wx.controller;

import com.zxm.club.wx.handler.MsgTypeStrategy;
import com.zxm.club.wx.handler.factory.MsgTypeFactory;
import com.zxm.club.wx.utils.MessageUtil;
import com.zxm.club.wx.utils.SHA1;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 微信回调
 *
 * @author zxm
 * @date 2024/10/13
 */
@RestController
@Slf4j
public class CallBackController {

    @Resource
    private MsgTypeFactory msgTypeFactory;

    private static final String token = "cbxd9YyHqPK7I1BurexsQXfyFOPvCDQQ";

    /**
     * 回调消息校验
     */
    @GetMapping("callback")
    public String callback(@RequestParam("signature") String signature,
                           @RequestParam("timestamp") String timestamp,
                           @RequestParam("nonce") String nonce,
                           @RequestParam("echostr") String echostr) {
        log.info("get验签请求参数：signature:{}，timestamp:{}，nonce:{}，echostr:{}",
                signature, timestamp, nonce, echostr);
        String shaStr = SHA1.getSHA1(token, timestamp, nonce, "");
        if (signature.equals(shaStr)) {
            return echostr;
        }
        return "unknown";
    }

    @PostMapping(value = "callback", produces = "application/xml;charset=UTF-8")
    public String callback(
            @RequestBody String requestBody,
            @RequestParam("signature") String signature,
            @RequestParam("timestamp") String timestamp,
            @RequestParam("nonce") String nonce,
            @RequestParam(value = "msg_signature", required = false) String msgSignature) {
        log.info("接收到微信消息：requestBody：{}", requestBody);
        Map<String, String> messageMap = MessageUtil.parseXml(requestBody);
        String fromUserName = messageMap.get("FromUserName");
        String toUserName = messageMap.get("ToUserName");
        String msgType = messageMap.get("MsgType");
        MsgTypeStrategy dealStrategy = msgTypeFactory.getDealStrategy(msgType);
        String content = dealStrategy.getMsgTypeString(messageMap);
        if (content == null || content.equals("")) {
            content = "<xml>\n" +
                    "  <ToUserName><![CDATA[" + fromUserName + "]]></ToUserName>\n" +
                    "  <FromUserName><![CDATA[" + toUserName + "]]></FromUserName>\n" +
                    "  <CreateTime>12345678</CreateTime>\n" +
                    "  <MsgType><![CDATA[text]]></MsgType>\n" +
                    "  <Content><![CDATA[当前逻辑未开发]]></Content>\n" +
                    "</xml>";
        }
        return content;
    }


//    String fromUserName = messageMap.get("FromUserName");
//    String toUserName = messageMap.get("ToUserName");
//    String content = "<xml>\n" +
//            "  <ToUserName><![CDATA[" + fromUserName + "]]></ToUserName>\n" +
//            "  <FromUserName><![CDATA[" + toUserName + "]]></FromUserName>\n" +
//            "  <CreateTime>12345678</CreateTime>\n" +
//            "  <MsgType><![CDATA[text]]></MsgType>\n" +
//            "  <Content><![CDATA[我叫zzb在学习]]></Content>\n" +
//            "</xml>";


    @GetMapping("/test")
    public String test() {
        return "hello world";
    }

}
