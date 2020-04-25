package com.example.demo.util;

import com.alibaba.fastjson.JSON;
import com.example.demo.exception.ServiceException;
import com.example.demo.model.enums.ResponseCodeEnum;
import org.apache.commons.lang3.ArrayUtils;

/**
 * @author zhaoyue
 * @description:
 * @create 2020-01-01 上午11:31
 **/
public class ServiceExceptionUtil {

    private ServiceExceptionUtil() {
    }

    public static ServiceException exception(ResponseCodeEnum responseCodeEnum, Object... params) {
        String message = responseCodeEnum.getMsg();
        if (ArrayUtils.isNotEmpty(params)) {
            message = message + "[params]:" + JSON.toJSONString(params);
        }
        return new ServiceException(responseCodeEnum.getCode(), message);
    }

    public static ServiceException exception(ResponseCodeEnum responseCodeEnum, String message, Object... params) {
        if (ArrayUtils.isNotEmpty(params)) {
            message = message + "[params]:" + JSON.toJSONString(params);
        }
        return new ServiceException(responseCodeEnum.getCode(), message);
    }
}
