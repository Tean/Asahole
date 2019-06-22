package com.netteans.example.shiro.controller;

import com.netteans.example.shiro.controller.r.ResultMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController{
    @Autowired
    private ResultMap resultMap;

    @RequestMapping(value = "/getMessage", method = RequestMethod.GET)
    public ResultMap getMessage() {
        return resultMap.success().message("您拥有用户权限，可以获得该接口的信息！");
    }
}