package com.netteans.example.gof;

public class FactoryMethod {

    public static Suit genSuit(String suitname) {
        if (suitname.equalsIgnoreCase("new")) {
            return new NewSuit();
        } else {
            return new OldSuit();
        }
    }
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
