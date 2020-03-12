package com.netteans.examples.gof;

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

class Dami implements IWho {

    @Override
    public String getName() {
        return "Daymi";
    }
}

class Tom implements IWho {

    @Override
    public String getName() {
        return "Tom";
    }
}

class AdminRole implements IRole {

    @Override
    public String getRole() {
        return "Admin";
    }
}

class GunsterRole implements IRole {

    @Override
    public String getRole() {
        return "Gunster";
    }
}
