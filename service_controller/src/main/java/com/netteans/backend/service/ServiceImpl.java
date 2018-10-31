package com.netteans.backend.service;

import com.netteans.backend.domain.DemoUser;
import org.springframework.stereotype.Service;

@Service
public class ServiceImpl implements IService {
    @Override
    public DemoUser getById(int id) {
        DemoUser user = new DemoUser();
        user.setName(String.format("{id:%s}", id));
        user.setPassword("id demo");
        user.setEmail("id@de.mo");
        return user;
    }

    @Override
    public DemoUser getByName(String name) {
        DemoUser user = new DemoUser();
        user.setName(String.format("{name:%s}", name));
        user.setPassword("name demo");
        user.setEmail("name@de.mo");
        return user;
    }
}
