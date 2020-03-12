package com.netteans.examples.gof.factory;

import com.netteans.examples.gof.factory.contract.ISampleFactory;

/**
 * Created by tanshengyong on 16/5/31.
 */
public class StringInvokeFactory implements ISampleFactory<StringSampleImpl> {
    public StringSampleImpl getSampleImpl() {
        return new StringSampleImpl();
    }
}
