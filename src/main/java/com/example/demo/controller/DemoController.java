package com.example.demo.controller;

import com.example.demo.model.BaseResult;
import com.example.demo.model.enums.ResponseCodeEnum;
import com.example.demo.model.params.QueryUserParam;
import com.example.demo.util.ServiceExceptionUtil;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author zhaoyue
 * @description:
 * @create 2020/4/24 2:02 下午
 **/
@RestController
public class DemoController {

    @GetMapping("/hello")
    public String hello(@RequestParam(value = "name") String name) {
        return String.format("Hello %s!", name);
    }

    @PostMapping("/queryUser")
    public BaseResult<String> queryUser(@Valid @RequestBody QueryUserParam queryUserParam) {
        if (1 == 1) {
            throw ServiceExceptionUtil.exception(ResponseCodeEnum.DB_SAVE_ERROR);
        }
        return BaseResult.success("测试");
    }
}
