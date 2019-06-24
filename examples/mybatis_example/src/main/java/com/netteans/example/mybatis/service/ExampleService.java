package com.netteans.example.mybatis.service;

import com.netteans.example.mybatis.dao.mapper.ExampleMessageMapperExt;
import com.netteans.example.mybatis.dao.mapper.ExampleUserMapper;
import com.netteans.example.mybatis.dao.model.ExampleMessage;
import com.netteans.example.mybatis.dao.model.ExampleUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExampleService {

    @Autowired
    private ExampleUserMapper userMapper;

    @Autowired
    private ExampleMessageMapperExt messageMapperExt;

    public ExampleUser getUser(Integer id) {
        if (id == null) return null;
        return userMapper.selectByPrimaryKey(id);
    }

    public ExampleMessage getMessage(Integer id) {
        return messageMapperExt.selectByPrimaryKey(id);
    }
}
