package com.netteans.example.gof;

public class Bridge {
    private final IWho who;
    private final IRole role;

    public Bridge(IWho who, IRole role) {
        this.who = who;
        this.role = role;
    }

    public String bridged() {
        return this.who.getName() + " is " + this.role.getRole();
    }
}

interface IWho {
    String getName();
}

interface IRole {
    String getRole();
}

