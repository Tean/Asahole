package com.netteans.example.factory;

import com.netteans.example.factory.contract.ISampleFactory;

/**
 * Created by tanshengyong on 16/5/31.
 */
public class StringInvokeFactory implements ISampleFactory<StringSampleImpl> {
    public StringSampleImpl getSampleImpl() {
        return new StringSampleImpl();
    }
}
