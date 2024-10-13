package com.zxm.club.wx.handler.factory;

import com.zxm.club.wx.enums.MsgTypeEnum;
import com.zxm.club.wx.handler.MsgTypeStrategy;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class MsgTypeFactory {

    @Resource
    private List<MsgTypeStrategy> msgTypeStrategyList;  //实现类全部放进列表中

    private final Map<MsgTypeEnum,MsgTypeStrategy> dealMap = new HashMap<>();

    public MsgTypeStrategy getDealStrategy(String msgType){
        MsgTypeEnum msgTypeEnum = MsgTypeEnum.getByCode(msgType);
        return dealMap.get(msgTypeEnum);
    }

    @PostConstruct
    private void init(){
        msgTypeStrategyList.forEach(msgTypeStrategy -> dealMap.put(msgTypeStrategy.getMsgType(),msgTypeStrategy));
    }
}
