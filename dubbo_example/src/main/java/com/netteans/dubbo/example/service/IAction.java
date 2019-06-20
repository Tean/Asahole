package com.netteans.dubbo.example.service;

import java.util.List;

public interface IAction<T> {
    T getOne(String actparam);

    List<T> getList();
}
