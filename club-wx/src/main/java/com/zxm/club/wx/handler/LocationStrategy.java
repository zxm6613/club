package com.zxm.club.wx.handler;

import com.zxm.club.wx.enums.MsgTypeEnum;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class LocationStrategy implements MsgTypeStrategy{
    @Override
    public MsgTypeEnum getMsgType() {
        return MsgTypeEnum.LOCATION;
    }

    @Override
    public String getMsgTypeString(Map<String, String> messageMap) {
        return null;
    }
}
