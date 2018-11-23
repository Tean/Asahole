package com.netteans.backend.controller;

import com.netteans.domain.DemoUser;
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
import java.io.File;
import java.util.UUID;

@Configuration
@RestController
@Api(protocols = "DEMO", value = "测试", tags = {"测试DEMO"})
public class Controller {

    private static Logger logger = LoggerFactory.getLogger(Controller.class);
    private static Logger history = LoggerFactory.getLogger("history");

    @Autowired
    private IService service;

    @Value("{id.auth}")
    private String auth;

    @Value("{id.litter}")
    private String litter;

    private static UUID INSTANCE_UUID = UUID.randomUUID();

    @RequestMapping(value = {"/get/{id}", "/get"}, method = {RequestMethod.GET, RequestMethod.DELETE})
    @ApiOperation(value = "测试user")
    @ApiResponses(
            {
                    @ApiResponse(code = 204, message = "成功"),
                    @ApiResponse(code = 205, message = "成功"),
                    @ApiResponse(code = 401, message = "未认证"),
                    @ApiResponse(code = 403, message = "禁止的"),
                    @ApiResponse(code = 406, message = "失败"),
            }
    )
    public ResponseEntity get(
            @ApiParam
            @PathVariable(value = "id", required = false)
                    Integer id
    ) {
        auth = new File("./").getAbsolutePath();
        logger.trace("trace @ controller test");
        logger.debug("debug @ controller test");
        logger.info("info @ controller test");
        logger.warn("warn @ controller test");
        logger.error("error @ controller test");
        history.trace("request id is {}, inject auth is {}, litter is {}", id, auth, litter);
        history.debug("request id is {}, inject auth is {}, litter is {}", id, auth, litter);
        history.info("request id is {}, inject auth is {}, litter is {}", id, auth, litter);
        history.warn("request id is {}, inject auth is {}, litter is {}", id, auth, litter);
        history.error("request id is {}, inject auth is {}, litter is {}", id, auth, litter);
        if (id != null) {
            if (id == 0)
                throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, auth);
            if (id < 0)
                throw new ResponseStatusException(HttpStatus.FORBIDDEN, litter);
            if (id < 200)
                return new ResponseEntity(service.getById(id), HttpStatus.OK);
            else {
                return new ResponseEntity(service.getById(id), HttpStatus.ACCEPTED);
            }
        }
        throw new ResponseStatusException(HttpStatus.UNSUPPORTED_MEDIA_TYPE);
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
            @ApiParam
            @PathVariable("name")
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
            @ApiParam(
                    defaultValue = "{\"name\": \"string\",\"password\": \"string\"z}",
                    allowEmptyValue = true,
                    name = "测试用户结构",
                    value = "测试用户结构体"
            )
            @Valid
            @RequestBody(required = false)
                    DemoUser user
    ) {
    }
}

