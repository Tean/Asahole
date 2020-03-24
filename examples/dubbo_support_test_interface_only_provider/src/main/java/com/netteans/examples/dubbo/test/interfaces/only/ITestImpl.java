package com.netteans.examples.dubbo.test.interfaces.only;

public class ITestImpl implements ITest {
    @Override
    public String test() {
        return this.getClass().getName();
    }
}
