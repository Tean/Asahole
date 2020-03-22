package com.netteans.examples.gof.factory;

import com.netteans.examples.gof.factory.contract.SampleInterface;

/**
 * Created by tanshengyong on 16/5/29.
 */
public class IntegerSampleImpl implements SampleInterface<Integer> {
    @Override
    public Integer getSample() {
        return 0;
    }

    @Override
    public void dosth(Integer val) {
        System.out.println("Integer " + val);
    }
}
