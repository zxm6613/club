package com.zxm.club.gateway.enums;

import lombok.Getter;

@Getter
public enum ResultCodeEnums {

    SUCCESS(200,"成功"),
    FAIL(500,"失败");

    public Integer code;
    public String message;

    ResultCodeEnums(int code, String massage){
        this.code = code;
        this.message = massage;
    }

    public static ResultCodeEnums getByCode(int codeVal){
        for (ResultCodeEnums resultCodeEnums : ResultCodeEnums.values()){
            if (resultCodeEnums.getCode().equals(codeVal)){
                return resultCodeEnums;
            }
        }
        return null;
    }

}
