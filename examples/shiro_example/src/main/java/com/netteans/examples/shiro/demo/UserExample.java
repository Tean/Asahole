package com.netteans.examples.shiro.demo;

import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.atomic.AtomicReference;

@Repository
public class UserExample {
    private final Map<String, User> userMap = new ConcurrentSkipListMap<String, User>();

    public UserExample() {
        User user = new User();
        user.setName("user");
        user.setPassword("1234");
        user.setRole("user");
        userMap.put(user.getName(), user);
        User guster = new User();
        guster.setName("guester");
        guster.setPassword("1234");
        guster.setRole("guester");
        userMap.put(guster.getName(), guster);
        User admin = new User();
        admin.setName("admin");
        admin.setPassword("1234");
        admin.setRole("admin");
        userMap.put(admin.getName(), admin);
        User acc = new User();
        acc.setName("acc");
        acc.setPassword("acc");
        acc.setRole("admin");
        userMap.put(acc.getName(), acc);
    }

    public User find(String username) {
        AtomicReference<User> user = new AtomicReference<>();
        userMap.forEach((k, v) -> {
            if (k.equalsIgnoreCase(username)) {
                user.set(v);
            }
        });
        return user.get();
    }
}
