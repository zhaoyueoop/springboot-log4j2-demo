package com.example.demo.model;

import com.example.demo.model.enums.ResponseCodeEnum;
import lombok.Data;

/**
 * @author zhaoyue
 * @description:
 * @create 2019-12-30 下午7:30
 **/
@Data
public class ResultSupport {

    private boolean success = true;
    private String msg;
    private String code;


    public static ResultSupport success() {
        ResultSupport result = new ResultSupport();
        result.setCode(ResponseCodeEnum.SUCCESS.getCode());
        result.setMsg(ResponseCodeEnum.SUCCESS.getMsg());
        return result;
    }

}
