package com.netteans.examples.structure;

public interface IValue<V extends Comparable> extends Comparable<V> {
    public V getValue();
}
