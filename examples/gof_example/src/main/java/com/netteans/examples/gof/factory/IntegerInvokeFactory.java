package com.netteans.exampless.gof.factory;

import com.netteans.exampless.gof.factory.contract.ISampleFactory;

/**
 * Created by tanshengyong on 16/5/31.
 */
public class IntegerInvokeFactory implements ISampleFactory<IntegerSampleImpl> {
    @Override
    public IntegerSampleImpl getSampleImpl() {
        return new IntegerSampleImpl();
    }
}
