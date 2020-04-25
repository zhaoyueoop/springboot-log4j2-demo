package com.example.demo.model;

import com.example.demo.model.enums.ResponseCodeEnum;
import lombok.Data;
import org.springframework.util.Assert;

/**
 * @author zhaoyue
 * @description:
 * @create 2019-12-30 下午7:31
 **/
@Data
public class BaseResult<T> extends ResultSupport {

    private T data;

    public static <T> BaseResult<T> error(BaseResult<?> result) {
        return error(result.getCode(), result.getMsg());
    }

    public static <T> BaseResult<T> error(String code, String message) {
        Assert.isTrue(!ResponseCodeEnum.SUCCESS.getCode().equals(code), "code 必须是错误的！");
        BaseResult<T> result = new BaseResult<>();
        result.setCode(code);
        result.setMsg(message);
        result.setSuccess(false);
        return result;
    }

    public static <T> BaseResult<T> error(ResponseCodeEnum responseCodeEnum) {
        Assert.isTrue(!ResponseCodeEnum.SUCCESS.getCode().equals(responseCodeEnum.getCode()), "code 必须是错误的！");
        BaseResult<T> result = new BaseResult<>();
        result.setCode(responseCodeEnum.getCode());
        result.setMsg(responseCodeEnum.getMsg());
        result.setSuccess(false);
        return result;
    }

    public static <T> BaseResult<T> error(ResponseCodeEnum responseCodeEnum, String message) {
        Assert.isTrue(!ResponseCodeEnum.SUCCESS.getCode().equals(responseCodeEnum.getCode()), "code 必须是错误的！");
        BaseResult<T> result = new BaseResult<>();
        result.setCode(responseCodeEnum.getCode());
        result.setMsg(message);
        result.setSuccess(false);
        return result;
    }

    public static <T> BaseResult<T> success(T data) {
        BaseResult<T> result = new BaseResult<>();
        result.data = data;
        result.setCode(ResponseCodeEnum.SUCCESS.getCode());
        result.setMsg(ResponseCodeEnum.SUCCESS.getMsg());
        return result;
    }

}
