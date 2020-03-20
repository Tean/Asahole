package com.netteans.exampless.gof.factory;

import com.netteans.exampless.gof.factory.contract.ISampleFactory;

/**
 * Created by tanshengyong on 16/5/31.
 */
public class ObjectInvokeFactory implements ISampleFactory<ObjectSampleImpl> {
    @Override
    public ObjectSampleImpl getSampleImpl() {
        return new ObjectSampleImpl(new SampleEntity().setName("Tean").setAge(66).setGreek("wow").setSex(true));
    }
}
