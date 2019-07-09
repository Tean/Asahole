package com.netteans.example.factory;

public class SamplePojo {
    String name;
    int age;
    boolean sex;
    String greek;

    public String getName() {
        return name;
    }

    public SamplePojo setName(String name) {
        this.name = name;
        return this;
    }

    public int getAge() {
        return age;
    }

    public SamplePojo setAge(int age) {
        this.age = age;
        return this;
    }

    public boolean isSex() {
        return sex;
    }

    public SamplePojo setSex(boolean sex) {
        this.sex = sex;
        return this;
    }

    public String getGreek() {
        return greek;
    }

    public SamplePojo setGreek(String greek) {
        this.greek = greek;
        return this;
    }

    public String greetting(String hi) {
        return this.name + " say hello to you";
    }
}
