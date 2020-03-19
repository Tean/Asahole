package com.netteans.examples.gof.factory;

import com.netteans.examples.gof.factory.contract.SampleInterface;

/**
 * Created by tanshengyong on 16/5/29.
 */
public class StringSampleImpl implements SampleInterface<String> {
    @Override
    public String getSample() {
        return "good";
    }

    @Override
    public void dosth(String val) {
        System.out.println("String " + val);
    }
}
