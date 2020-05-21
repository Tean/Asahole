package com.netteans.examples.dubbo.service;

import java.util.List;

public interface IAction<T> {
    T getOne(String actparam);

    List<T> getList();

    String getInstance();
}
