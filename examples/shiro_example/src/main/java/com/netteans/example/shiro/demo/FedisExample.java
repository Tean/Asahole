package com.netteans.example.shiro.demo;

import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.atomic.AtomicReference;

@Repository
public class FedisExample {
    private final Map<String, User> userMap = new ConcurrentSkipListMap<String, User>();

    public FedisExample() {
        User user = new User();
        user.setName("fuser");
        user.setPassword("1234");
        user.setRole("user");
        userMap.put(user.getName(), user);
        User guster = new User();
        guster.setName("fguester");
        guster.setPassword("1234");
        guster.setRole("guester");
        userMap.put(guster.getName(), guster);
        User admin = new User();
        admin.setName("fadmin");
        admin.setPassword("1234");
        admin.setRole("admin");
        userMap.put(admin.getName(), admin);
        User acc = new User();
        acc.setName("facc");
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
