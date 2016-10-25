package com.test.client.controller;

import com.test.common.models.User;
import com.test.client.service.UserClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@RestController
public class TestController {
    private static Logger logger = LoggerFactory.getLogger(TestController.class);

    @Autowired
    RestTemplate restTemplate;
    @Autowired
    private UserClient userClient;

    @RequestMapping("/test1")
    Collection<User> test1() {
        PagedResources<User> users = userClient.findAll();
        Collection<User> list = users.getContent();
        return list;
    }

    @RequestMapping("/test2")
    Resource<List<User>> test2() {
        Resource<List<User>> users = userClient.findByNameLike("*");
        return users;
    }

    @RequestMapping("/test3")
    Resource<List<User>> test3() {
        Resource<List<User>> users = userClient.findByNameContaining("");
        return users;
    }

    @RequestMapping("/test4")
    User test4() {
        User user1 = userClient.findByName("user");
        User user2 = userClient.findUser("user");
        return user2;
    }
}
