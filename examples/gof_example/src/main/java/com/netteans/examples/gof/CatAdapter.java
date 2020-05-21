package com.netteans.examples.gof;

public class CatAdapter implements IAdapter<Integer> {
    private final Cat cat;

    public CatAdapter(Cat cat) {
        this.cat = cat;
    }

    @Override
    public Integer exec() {
        return cat.miow();
    }
}
