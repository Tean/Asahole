package com.netteans.example.factory;

import com.netteans.example.factory.contract.ISampleFactory;
import com.netteans.example.factory.contract.ISampleGroupFactory;
import com.netteans.example.factory.contract.SampleInterface;

import java.lang.reflect.InvocationTargetException;

/**
 * Created by tanshengyong on 16/5/31.
 */
public class SampleGroupFactory implements ISampleGroupFactory {
    private static SampleGroupFactory ourInstance = new SampleGroupFactory();

    public static SampleGroupFactory getInstance() {
        return ourInstance;
    }

    private SampleGroupFactory() {
    }

    public <T extends ISampleFactory> T getSomeSampleFactory(Class<T> sampleFactoryClassType) {
        try {
            return sampleFactoryClassType.getConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }

    public <T extends ISampleFactory> T getSomeSampleFactory(ISampleFactory sampleFactory, Class<T> tclass) {
        try {
            return tclass.cast(sampleFactory);
        } catch (ClassCastException e) {
            return (T) sampleFactory;
        }
    }
}
