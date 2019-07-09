package com.netteans.example.factory.enums;

/**
 * Created by Tean on 2016/5/31.
 */
public enum SampleEnum {
    ENUM1(1), ENUM2(2);

    private int value;

    SampleEnum(int val) {
        this.value = val;
    }

    public int getValue() {
        return value;
    }

    public static SampleEnum fromValue(int val) {
        if (val == 1) return ENUM1;
        if (val == 2) return ENUM2;
        return null;
    }
}
