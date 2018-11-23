package com.netteans.backend.service;

import com.netteans.domain.DemoUser;

public interface IService {

    DemoUser getById(int id);

    DemoUser getByName(String name);
}
