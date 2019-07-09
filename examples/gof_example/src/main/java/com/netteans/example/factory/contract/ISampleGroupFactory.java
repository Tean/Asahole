package com.netteans.example.factory.contract;

/**
 * Created by tanshengyong on 16/5/31.
 */
public interface ISampleGroupFactory {
    <T extends ISampleFactory> T getSomeSampleFactory(Class<T> sampleFactoryClassType);
}
