package com.netteans.example.gof.factory.contract;

/**
 * Created by tanshengyong on 16/5/29.
 */
public interface ISampleFactory<T extends SampleInterface> {

    T getSampleImpl();
}
