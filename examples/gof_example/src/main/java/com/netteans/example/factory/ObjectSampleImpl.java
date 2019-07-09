package com.netteans.example.factory;

import com.netteans.example.factory.contract.SampleInterface;

public class ObjectSampleImpl implements SampleInterface<SamplePojo> {
    private final SamplePojo samplePojo;

    public ObjectSampleImpl(SamplePojo samplePojo) {
        this.samplePojo = samplePojo;
    }

    @Override
    public SamplePojo getSample() {
        return this.samplePojo;
    }

    @Override
    public void dosth(SamplePojo val) {
        if (val.sex != samplePojo.sex && val.age < 20)
            System.out.println(val.greetting("hi"));
        else
            System.out.println("hello");
    }
}
