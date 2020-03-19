package com.netteans.example.structure;

import com.netteans.example.structure.bst.IntValue;

public interface IValue<V> extends Comparable<V> {
    public V getValue();
}
