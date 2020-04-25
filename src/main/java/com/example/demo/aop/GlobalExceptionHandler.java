package com.example.demo.aop;

import com.alibaba.fastjson.JSON;
import com.example.demo.exception.ServiceException;
import com.example.demo.model.BaseResult;
import com.example.demo.model.enums.ResponseCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.util.List;

/**
 * @author zhaoyue
 * @description:
 * @create 2020-01-01 上午11:53
 **/
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     *
     * @param req
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = ServiceException.class)
    public BaseResult serviceExceptionHandler(HttpServletRequest req, ServiceException ex) {
        return BaseResult.error(ex.getCode(), ex.getMessage());
    }

    /**
     * 参数不正确
     *
     * @param req
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    public BaseResult missingServletRequestParameterExceptionHandler(HttpServletRequest req, MissingServletRequestParameterException ex) {
        log.error("client_ip={},url={} ,参数不正确", req.getRemoteAddr(),
                req.getRequestURL().toString(), ex);
        return BaseResult.error(ResponseCodeEnum.PARAM_ERROR.getCode(), ResponseCodeEnum.PARAM_ERROR.getMsg() + ":" + ex.getMessage());
    }

    /**
     * 参数不正确
     * @param req
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public BaseResult constraintViolationExceptionHandler(HttpServletRequest req, MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();
        String errorMsg = fieldErrors.stream().findFirst().map(DefaultMessageSourceResolvable::getDefaultMessage).orElse("");
        log.error("client_ip={},url={} ,参数不正确", req.getRemoteAddr(),
                req.getRequestURL().toString(), ex);
        return BaseResult.error(ResponseCodeEnum.PARAM_ERROR.getCode(), errorMsg);
    }

    /**
     * 系统异常
     *
     * @param req
     * @param e
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public BaseResult exceptionHandler(HttpServletRequest req, Exception e) {
        return BaseResult.error(ResponseCodeEnum.SYSTEM_ERROR.getCode(), ResponseCodeEnum.SYSTEM_ERROR.getMsg());
    }
}
