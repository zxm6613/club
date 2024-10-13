package com.zxm.club.wx.handler;

import com.zxm.club.wx.enums.MsgTypeEnum;

import java.util.Map;

/**
 * MSG 类型策略规范
 *
 * @author zxm
 * @date 2024/10/13
 */
public interface MsgTypeStrategy {

    //因为我要在工厂中，把每个策略和枚举对应起来，你要告诉我你是哪个策略
    MsgTypeEnum getMsgType();

    // 处理该类型的消息
    String getMsgTypeString(Map<String, String> messageMap);
}
