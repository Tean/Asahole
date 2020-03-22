package com.netteans.examples.gof;

public class AdminRole implements IRole {

    @Override
    public String getRole() {
        return "Admin";
    }
}
