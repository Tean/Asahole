package com.netteans.examples.structure;

import com.netteans.examples.structure.bst.IntValue;

public interface IValue<V> extends Comparable<V> {
    public V getValue();
}
