package com.netteans.exampless.gof.factory;

import com.netteans.exampless.gof.factory.contract.ISampleFactory;

/**
 * Created by tanshengyong on 16/5/31.
 */
public class StringInvokeFactory implements ISampleFactory<StringSampleImpl> {
    @Override
    public StringSampleImpl getSampleImpl() {
        return new StringSampleImpl();
    }
}
