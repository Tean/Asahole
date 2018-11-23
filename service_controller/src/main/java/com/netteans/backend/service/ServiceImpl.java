package com.netteans.backend.service;

import com.netteans.domain.DemoUser;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ServiceImpl implements IService {

    private static UUID INSTANCE_UUID = UUID.randomUUID();

    @Override
    public DemoUser getById(int id) {
        DemoUser user = new DemoUser();
        user.setName(String.format("{id:%s}", id));
        user.setPassword("id demo");
        user.setEmail(INSTANCE_UUID + "@de.mo");
        return user;
    }

    @Override
    public DemoUser getByName(String name) {
        DemoUser user = new DemoUser();
        user.setName(String.format("{name:%s}", name));
        user.setPassword("name demo");
        user.setEmail(INSTANCE_UUID + "@de.mo");
        return user;
    }
}
