package com.netteans.example.shiro.controller;

import com.netteans.example.shiro.controller.r.ResultMap;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/guest")
public class GuesterController {
    @Autowired
    private ResultMap resultMap;

    @RequestMapping(value = "/enter", method = RequestMethod.GET)
    public ResultMap enter() {
        Object s = SecurityUtils.getSubject().getPrincipals();
        return resultMap.success().message("欢迎进入，您的身份是" + (s == null ? "游客" : s.toString()));
    }

    @RequestMapping(value = "/getMessage", method = RequestMethod.GET)
    public ResultMap getMessage() {
        return resultMap.success().message("您拥有获得该接口的信息的权限！");
    }
}