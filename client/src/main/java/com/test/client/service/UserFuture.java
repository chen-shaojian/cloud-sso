package com.test.client.service;

import com.test.common.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


import java.util.concurrent.CompletableFuture;

@Component
public class UserFuture {
    @Autowired
    private UserService userService;

    @AsyncTimed
    public CompletableFuture<User> getUserByName(String name) {
        return CompletableFuture.supplyAsync(() -> {
            return userService.getUserByName(name);
        });
    }
}
