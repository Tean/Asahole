package com.netteans.examples.gof;

public class DogAdapter implements IAdapter<String> {
    private final Dog dog;

    public DogAdapter(Dog dog) {
        this.dog = dog;
    }

    @Override
    public String exec() {
        return dog.wow();
    }
}
