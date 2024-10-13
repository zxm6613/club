package com.zxm.club.wx.handler;

import com.zxm.club.wx.enums.MsgTypeEnum;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class VideoStrategy implements MsgTypeStrategy{
    @Override
    public MsgTypeEnum getMsgType() {
        return MsgTypeEnum.VIDEO;
    }

    @Override
    public String getMsgTypeString(Map<String, String> messageMap) {
        return null;
    }
}
