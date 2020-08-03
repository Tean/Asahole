package com.netteans.example.structure;

public interface IValue<V extends Comparable> extends Comparable<V> {
    public V getValue();
}
