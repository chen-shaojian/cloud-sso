package com.test.client.controller;

import com.test.common.models.User;
import com.test.client.service.UserClient;
import com.test.client.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserController {
    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    UserService userService;
    @Autowired
    UserClient userClient;

    @RequestMapping("/index")
    public String index(ModelMap model, Principal user) throws Exception{
        model.addAttribute("user", user);
        return "user/index";
    }

    @RequestMapping(value="/{name}")
    public String show(ModelMap model,@PathVariable String name) {
        //使用Rest + Redis
        User user = userService.getUser(name);
        model.addAttribute("user",user);
        return "user/show";
    }

    @RequestMapping(value="/names/{name}")
    public String usershow(ModelMap model,@PathVariable String name) {
        //使用Rest + fallbackMethod
        User user = userService.getUserByName(name);
        model.addAttribute("user",user);
        return "user/show";
    }

}
