package com.netteans.example.factory;

import com.netteans.example.factory.contract.SampleInterface;

/**
 * Created by tanshengyong on 16/5/29.
 */
public class StringSampleImpl implements SampleInterface<String> {
    public String getSample() {
        return "good";
    }

    public void dosth(String val) {
        System.out.println("String " + val);
    }
}
