package com.netteans.example;

public class AbstractFactoryMethod {

    public static IFactory getSuit(String factname) {
        if (factname.equalsIgnoreCase("animal")) {
            return new AnimalFactory("AnimalFactory");
        } else {
            return new PlantFactory("PlantFactory");
        }
    }
}

interface IFactory<P extends ISuit> {

    P getSuit();
}

interface ISuit<S extends ISuit> {
    String getDetail();

    S from(String factname);
}

class Animal implements ISuit<Animal> {
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
}

class Plant implements ISuit<Plant> {
    private String a = "plant ";

    @Override
    public String getDetail() {
        return a;
    }

    @Override
    public Plant from(String factname) {
        if (a.endsWith(" ")) a += "from " + factname;
        return this;
    }
}

class AnimalFactory implements IFactory<Animal> {

    private final String factname;

    public AnimalFactory(String factname) {
        this.factname = factname;
    }

    @Override
    public Animal getSuit() {
        return new Animal().from(factname);
    }
}

class PlantFactory implements IFactory<Plant> {

    private final String factname;

    public PlantFactory(String factname) {
        this.factname = factname;
    }

    @Override
    public Plant getSuit() {
        return new Plant().from(factname);
    }
}
