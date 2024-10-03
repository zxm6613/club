package com.zxm.club.subject.common.enums;

import lombok.Getter;

public enum ResultCodeEnums {

    SUCCESS(200,"成功"),
    FAIL(500,"失败");

    public Integer code;
    public String message;

    ResultCodeEnums(int code,String massage){
        this.code = code;
        this.message = massage;
    }


}
