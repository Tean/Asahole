package com.netteans.example.starter;

public class ExampleConfig {
    private String name;
    private String mail;
    private String phone;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getMail() {
        return mail;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    @Override
    public String toString() {
        return "Author:" + this.name + " Contact:" + this.phone + "/" + this.mail;
    }
}
