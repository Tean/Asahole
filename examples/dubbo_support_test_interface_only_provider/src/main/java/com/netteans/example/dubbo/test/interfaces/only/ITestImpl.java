package com.netteans.example.dubbo.test.interfaces.only;

public class ITestImpl implements ITest {
    @Override
    public String test() {
        return this.getClass().getName();
    }
}
