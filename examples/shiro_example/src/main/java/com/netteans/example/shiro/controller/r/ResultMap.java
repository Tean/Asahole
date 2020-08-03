package com.netteans.example.shiro.controller.r;

import org.springframework.stereotype.Component;

@Component
public class ResultMap {

    private static final ResultMap SUCCESS = new ResultMap("result", "success");
    private static final ResultMap FAIL = new ResultMap("result", "fail");
    private static final ResultMap CODE = new ResultMap("code");
    private String resp;
    private Object value;
    private String message;

    public ResultMap() {
    }

    public ResultMap(String resp, Object value) {
        this.resp = resp;
        this.value = value;
    }

    public ResultMap(String code) {
        this(code, "");
    }

    public ResultMap success() {
        return this.SUCCESS;
    }

    public ResultMap fail() {
        return this.FAIL;
    }

    public ResultMap code(int code) {
        return this.CODE.setValue(code);
    }

    public ResultMap message(String message) {
        this.setMessage(message);
        return this;
    }

    private void setMessage(String message) {
        this.message = message;
    }

    public ResultMap setValue(Object value) {
        this.value = value;
        return this;
    }

    public String getResp() {
        return resp;
    }

    public void setResp(String resp) {
        this.resp = resp;
    }

    public Object getValue() {
        return value;
    }

    public String getMessage() {
        return message;
    }
}
