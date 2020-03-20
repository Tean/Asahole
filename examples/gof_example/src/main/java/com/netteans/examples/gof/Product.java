package com.netteans.exampless.gof;

public class
Product implements IProdMaker {
    private String prod = "product";

    @Override
    public void addPart(IPart p) {
        prod += " add " + p.part();
    }

    @Override
    public String getDetail() {
        return prod;
    }
}
