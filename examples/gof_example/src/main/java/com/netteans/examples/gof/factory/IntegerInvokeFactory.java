package com.netteans.examples.gof.factory;

import com.netteans.examples.gof.factory.contract.ISampleFactory;

/**
 * Created by tanshengyong on 16/5/31.
 */
public class IntegerInvokeFactory implements ISampleFactory<IntegerSampleImpl> {
    public IntegerSampleImpl getSampleImpl() {
        return new IntegerSampleImpl();
    }
}
