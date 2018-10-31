package com.netteans.backend.service;

import com.netteans.backend.domain.DemoUser;

public interface IService {

    DemoUser getById(int id);

    DemoUser getByName(String name);
}
