package com.netteans.examples.gof.factory.contract;

/**
 * Created by tanshengyong on 16/5/29.
 */
public interface SampleInterface<T> {
    T getSample();
    void dosth(T val);
}
