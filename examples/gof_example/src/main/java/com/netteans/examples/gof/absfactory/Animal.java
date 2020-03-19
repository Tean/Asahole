package com.netteans.examples.gof.absfactory;

public class Animal implements ISuit<Animal>, IRcrit<Animal> {
    private String a = "animal ";

    @Override
    public String getDetail() {
        return a;
    }

    @Override
    public Animal from(String factname) {
        if (a.endsWith(" ")) a += "from " + factname;
        return this;
    }

    @Override
    public String getRcru() {
        return "animal Rcru";
    }
}
