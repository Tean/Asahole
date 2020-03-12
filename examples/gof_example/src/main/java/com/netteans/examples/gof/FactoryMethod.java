package com.netteans.examples.gof;

public class FactoryMethod {

    static Suit genSuit(String suitname) {
        if (suitname.equalsIgnoreCase("new")) {
            return new NewSuit();
        } else {
            return new OldSuit();
        }
    }
}

interface Suit {
    String name();
}

class NewSuit implements Suit {

    @Override
    public String name() {
        return "new suit";
    }
}

class OldSuit implements Suit {

    @Override
    public String name() {
        return "old suit";
    }
}
