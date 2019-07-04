package com.netteans.example;

public class AdapterExecutor {
    private IAdapter adapter;

    public void injectAdapter(IAdapter adapter) {
        this.adapter = adapter;
    }

    public Object exec() {
        return this.adapter.exec();
    }
}


interface IAdapter<R> {
    R exec();
}

class Dog {
    String wow() {
        return "wof";
    }
}

class Cat {
    int miow() {
        return 666;
    }
}

class DogAdapter implements IAdapter<String> {
    private final Dog dog;

    DogAdapter(Dog dog) {
        this.dog = dog;
    }

    @Override
    public String exec() {
        return dog.wow();
    }
}

class CatAdapter implements IAdapter<Integer> {
    private final Cat cat;

    CatAdapter(Cat cat) {
        this.cat = cat;
    }

    @Override
    public Integer exec() {
        return cat.miow();
    }
}