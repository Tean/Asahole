package com.netteans.controller;

import com.netteans.domain.DemoUser;
import com.netteans.service.IService;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;

@Configuration
@RestController
@Api(protocols = "DEMO", value = "测试", tags = {"测试DEMO"})
public class Controller {

    private static Logger logger = LoggerFactory.getLogger(Controller.class);

    @Autowired
    private IService service;

    @RequestMapping(value = {"/get/{id}", "/get"}, method = {RequestMethod.GET, RequestMethod.DELETE})
    @ApiOperation(value = "测试user")
    @ApiResponses(
            {
                    @ApiResponse(code = 204, message = "成功"),
                    @ApiResponse(code = 205, message = "成功"),
                    @ApiResponse(code = 401, message = "未认证"),
                    @ApiResponse(code = 403, message = "禁止的"),
                    @ApiResponse(code = 405, message = "失败"),
            }
    )
    public DemoUser get(
            @PathVariable(value = "id", required = false)
            @ApiParam()
                    Integer id
    ) {
        if (id != null) {
            if (id == 0)
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "no user id can be 0,because it's tron");
            if (id < 0)
                throw new ResponseStatusException(HttpStatus.FORBIDDEN, "id cannot be litter than 0");
        }
        logger.debug("request id is {}", id);
        return service.getById(id);
    }

    @RequestMapping(value = "/get/name/{name}", method = RequestMethod.GET)
    @ApiOperation("测试用户名方式获取")
    @ApiResponses(
            {
                    @ApiResponse(code = 204, message = "成功"),
                    @ApiResponse(code = 205, message = "成功"),
                    @ApiResponse(code = 401, message = "未认证"),
                    @ApiResponse(code = 403, message = "禁止的"),
                    @ApiResponse(code = 405, message = "失败"),
            }
    )
    public DemoUser getByName(
            @PathVariable("name")
            @Valid
            @ApiParam
                    String name
    ) {
        return service.getByName(name);
    }

    @RequestMapping(value = {"/post/{id}", "/post"}, method = {RequestMethod.POST, RequestMethod.PUT})
    @ApiOperation(value = "测试user")
    @ApiResponses(
            {
                    @ApiResponse(code = 204, message = "成功"),
                    @ApiResponse(code = 205, message = "成功"),
                    @ApiResponse(code = 401, message = "未认证"),
                    @ApiResponse(code = 403, message = "禁止的"),
                    @ApiResponse(code = 405, message = "失败"),
            }
    )
    public void post(
            @Valid
            @RequestBody(required = false)
            @ApiParam(
                    defaultValue = "{\"name\": \"string\",\"password\": \"string\"z}",
                    allowEmptyValue = true,
                    name = "测试用户结构",
                    value = "测试用户结构体"
            )
                    DemoUser user
    ) {
    }
}

