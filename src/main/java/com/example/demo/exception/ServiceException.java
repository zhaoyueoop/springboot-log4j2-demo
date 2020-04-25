package com.example.demo.exception;

/**
 * @author zhaoyue
 * @description:
 * @create 2019-12-30 下午7:38
 **/
public class ServiceException extends RuntimeException {

    /**
     * 错误码
     */
    private final String code;

    public ServiceException(String code, String message) {
        super(message);
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
