package com.netteans.backend.service;

import com.netteans.domain.DemoUser;
import org.springframework.beans.factory.annotation.Value;

public interface IService {

    DemoUser getById(int id);

    DemoUser getByName(String name);

    String getAuth();

    String getLitter();
}
