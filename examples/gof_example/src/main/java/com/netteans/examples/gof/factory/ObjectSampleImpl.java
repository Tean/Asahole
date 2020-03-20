package com.netteans.exampless.gof.factory;

import com.netteans.exampless.gof.factory.contract.SampleInterface;

public class ObjectSampleImpl implements SampleInterface<SampleEntity> {
    private final SampleEntity sampleEntity;

    public ObjectSampleImpl(SampleEntity sampleEntity) {
        this.sampleEntity = sampleEntity;
    }

    @Override
    public SampleEntity getSample() {
        return this.sampleEntity;
    }

    @Override
    public void dosth(SampleEntity val) {
        if (val.sex != sampleEntity.sex && val.age < 20)
            System.out.println(val.greetting("hi"));
        else
            System.out.println("hello");
    }
}
