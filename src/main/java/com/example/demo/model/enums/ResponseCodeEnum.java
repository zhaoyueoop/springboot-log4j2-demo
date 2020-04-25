package com.example.demo.model.enums;

import lombok.Getter;

/**
 * @author zhaoyue
 * @description:
 * @create 2019-12-30 下午7:32
 **/
@Getter
public enum ResponseCodeEnum {
    SUCCESS("0000", "成功"),
    PARAM_ERROR("0001", "参数异常"),
    DB_SAVE_ERROR("0010", "数据库保存失败"),
    DB_UPDATE_ERROR("0011", "数据库保存失败"),
    SYSTEM_ERROR("9999", "系统异常"),
    ;

    ResponseCodeEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private String code;
    private String msg;
}
