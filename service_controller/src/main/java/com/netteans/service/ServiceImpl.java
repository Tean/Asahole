package com.netteans.service;

import com.netteans.domain.DemoUser;
import org.springframework.stereotype.Service;

@Service
public class ServiceImpl implements IService {
    @Override
    public DemoUser getById(int id) {
        DemoUser user = new DemoUser();
        user.setName(String.format("{}", id));
        user.setPassword("id demo");
        return user;
    }

    @Override
    public DemoUser getByName(String name) {
        DemoUser user = new DemoUser();
        user.setName(String.format("{}", name));
        user.setPassword("name demo");
        return user;
    }
}
