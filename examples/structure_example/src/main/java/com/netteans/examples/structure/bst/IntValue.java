package com.netteans.examples.structure.bst;

import com.netteans.examples.structure.IValue;

public class IntValue implements IValue<Integer> {
    int value;

    public IntValue(int value) {
        this.value = value;
    }


    @Override
    public Integer getValue() {
        return value;
    }

    @Override
    public int compareTo(Integer o) {
        return o.compareTo(value);
    }
}
