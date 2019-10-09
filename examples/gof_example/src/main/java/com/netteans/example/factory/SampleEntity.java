package com.netteans.example.factory;

public class SampleEntity {
    String name;
    int age;
    boolean sex;
    String greek;

    public String getName() {
        return name;
    }

    public SampleEntity setName(String name) {
        this.name = name;
        return this;
    }

    public int getAge() {
        return age;
    }

    public SampleEntity setAge(int age) {
        this.age = age;
        return this;
    }

    public boolean isSex() {
        return sex;
    }

    public SampleEntity setSex(boolean sex) {
        this.sex = sex;
        return this;
    }

    public String getGreek() {
        return greek;
    }

    public SampleEntity setGreek(String greek) {
        this.greek = greek;
        return this;
    }

    public String greetting(String hi) {
        return this.name + " say hello to you";
    }
}
