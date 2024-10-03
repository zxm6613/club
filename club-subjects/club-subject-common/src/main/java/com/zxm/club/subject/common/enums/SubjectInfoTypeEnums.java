package com.zxm.club.subject.common.enums;

import lombok.Getter;

@Getter
public enum SubjectInfoTypeEnums {

    REDIO(1,"单选"),
    MULTIPLE(2,"多选"),
    JUDGE(3,"判断"),
    BRIEF(4,"简答");

    public Integer code;
    public String message;

    SubjectInfoTypeEnums(int code, String massage){
        this.code = code;
        this.message = massage;
    }

    public static SubjectInfoTypeEnums getByCode(int codeVal){
        for (SubjectInfoTypeEnums resultCodeEnums : SubjectInfoTypeEnums.values()){
            if (resultCodeEnums.getCode().equals(codeVal)){
                return resultCodeEnums;
            }
        }
        return null;
    }

}
