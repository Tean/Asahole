package com.netteans.examples.gof.factory;

import com.netteans.examples.gof.factory.contract.SampleInterface;

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
