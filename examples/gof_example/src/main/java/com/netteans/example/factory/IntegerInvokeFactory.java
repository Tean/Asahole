package com.netteans.example.factory;

import com.netteans.example.factory.contract.ISampleFactory;

/**
 * Created by tanshengyong on 16/5/31.
 */
public class IntegerInvokeFactory implements ISampleFactory<IntegerSampleImpl> {
    public IntegerSampleImpl getSampleImpl() {
        return new IntegerSampleImpl();
    }
}
