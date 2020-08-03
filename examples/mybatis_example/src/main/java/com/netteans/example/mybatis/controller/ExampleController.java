package com.netteans.example.mybatis.controller;

import com.netteans.example.mybatis.dao.model.ExampleUser;
import com.netteans.example.mybatis.service.ExampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExampleController {

    @Autowired
    private ExampleService exampleService;

    @GetMapping(value = "/user/{id}")
    public ExampleUser get(@PathVariable("id") Integer id) {
        return exampleService.getUser(id);
    }
}
