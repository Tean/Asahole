package com.netteans.example.shiro.demo;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Component
public class UserExample {
    private final List<User> userList = new ArrayList<>();

    public UserExample() {
        User user = new User();
        user.setName("user");
        user.setPassword("1234");
        user.setRole("user");
        userList.add(user);
        User guster = new User();
        guster.setName("guester");
        guster.setPassword("1234");
        guster.setRole("guester");
        userList.add(guster);
        User admin = new User();
        admin.setName("admin");
        admin.setPassword("1234");
        admin.setRole("admin");
        userList.add(admin);
    }

    public User find(String username) {
        AtomicReference<User> user = new AtomicReference<>();
        userList.forEach(u -> {
            if (u.getName().equalsIgnoreCase(username))
                user.set(u);
        });
        return user.get();
    }
}
