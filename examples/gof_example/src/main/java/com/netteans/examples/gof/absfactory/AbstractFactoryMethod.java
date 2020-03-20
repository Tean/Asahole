package com.netteans.exampless.gof.absfactory;

public class AbstractFactoryMethod {

    public static IFactory getSuitFactory() {
        return getSuitFactory("animal");
    }

    public static IFactory getSuitFactory(String factname) {
        if (factname.equalsIgnoreCase("animal")) {
            return new AnimalFactory("AnimalFactory");
        } else {
            return new PlantFactory("PlantFactory");
        }
    }
}

interface IRcrit<R extends IRcrit> {
    String getRcru();
}

class Plant implements ISuit<Plant>, IRcrit<Plant> {
    private String a = "plant ";

    @Override
    public String getDetail() {
        return a;
    }

    @Override
    public Plant from(String factname) {
        if (a.endsWith(" ")) {
            a += "from " + factname;
        }
        return this;
    }

    @Override
    public String getRcru() {
        return "plant rcru";
    }
}

class AnimalFactory implements IFactory<Animal> {

    private final String factname;

    public AnimalFactory(String factname) {
        this.factname = factname;
    }

    @Override
    public <ZP extends Animal> ZP getSuit(Class<? extends ZP> suitclass) {
        Animal animal = new Animal().from(factname);
        boolean isSuit = suitclass.isInstance(animal);
        if (isSuit) {
            return suitclass.cast(animal);
        } else {
            return null;
        }
    }
}

class PlantFactory implements IFactory<Plant> {

    private final String factname;

    public PlantFactory(String factname) {
        this.factname = factname;
    }

    @Override
    public <ZP extends Plant> ZP getSuit(Class<? extends ZP> suitclass) {
        Plant plant = new Plant().from(factname);
        boolean isSuit = suitclass.isInstance(plant);
        if (isSuit) {
            return suitclass.cast(plant);
        } else {
            return null;
        }
    }
}
