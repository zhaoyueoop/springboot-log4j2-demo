package com.example.demo.model.params;

import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author zhaoyue
 * @description:
 * @create 2020/4/24 5:54 下午
 **/
@Data
public class QueryUserParam {

    @NotBlank(message = "姓名不能为空")
    private String name;
}
