package com.netteans.exampless.gof;

public class Prototype implements ICloneable<Prototype>{

    @Override
    public Prototype clone() {
        return new Prototype();
    }
}

interface ICloneable<T> {
    T clone();
}


