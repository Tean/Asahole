package com.netteans.example.gof;

public class AdminRole implements IRole {

    @Override
    public String getRole() {
        return "Admin";
    }
}
