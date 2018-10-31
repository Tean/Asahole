package com.netteans.backend.controller;

import com.netteans.backend.domain.DemoUser;
import com.netteans.backend.service.IService;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @Value("${id.auth}")
    private String auth;

    @Value("${id.litter}")
    private String litter;

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
    public ResponseEntity get(
            @PathVariable(value = "id", required = false)
            @ApiParam()
                    Integer id
    ) {
        if (id != null) {
            if (id == 0)
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, auth);
            if (id < 0)
                throw new ResponseStatusException(HttpStatus.FORBIDDEN, litter);
        }
        logger.debug("request id is {}", id);
        if (id < 200)
            return new ResponseEntity(service.getById(id), HttpStatus.OK);
        else
            return new ResponseEntity(service.getById(id), HttpStatus.CREATED);
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

