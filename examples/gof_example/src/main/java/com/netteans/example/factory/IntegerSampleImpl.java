package com.netteans.example.factory;

import com.netteans.example.factory.contract.SampleInterface;

/**
 * Created by tanshengyong on 16/5/29.
 */
public class IntegerSampleImpl implements SampleInterface<Integer> {
    public Integer getSample() {
        return 0;
    }

    public void dosth(Integer val) {
        System.out.println("Integer " + val);
    }
}
