package com.zxm.club.wx.enums;


import lombok.Getter;

@Getter
public enum MsgTypeEnum {
    EVENT("event","事件消息"),  //这里定义事件，后面可以根据事件再分枚举
    TEXT("text","文本消息"),
    IMAGE("image","图片消息"),
    VOICE("voice","语音消息"),
    VIDEO("video","视频消息"),
    LOCATION("location","位置消息");

    private String type;
    private String desc;

    MsgTypeEnum(String type, String desc){
        this.type = type;
        this.desc = desc;
    }

    public static MsgTypeEnum getByCode(String type){
        for (MsgTypeEnum msgTypeEnum : MsgTypeEnum.values()){
            if (msgTypeEnum.getType().equals(type)){
                return msgTypeEnum;
            }
        }
        return null;
    }

}
