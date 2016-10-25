package com.test.client.service;

import com.test.common.models.User;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.hateoas.PagedResources;
import org.springframework.hateoas.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("web")
public interface UserClient {
    @RequestMapping(method = RequestMethod.GET, value = "/users/{id}")
    User findById(@RequestParam("id") Long id);

    @RequestMapping(method = RequestMethod.GET, value = "/users/search/findByName")
    User findByName(@RequestParam("name") String name);

    @RequestMapping(method = RequestMethod.GET, value = "/user/names/{name}")
    User findUser(@RequestParam("name") String name);


    @RequestMapping(method = RequestMethod.GET, value = "/users")
    PagedResources<User> findAll();

    @RequestMapping(method = RequestMethod.GET, value = "/users/search/findByNameLike")
    Resource<List<User>> findByNameLike(@RequestParam("name") String name);

    @RequestMapping(method = RequestMethod.GET, value = "/users/search/findByNameContaining")
    Resource<List<User>> findByNameContaining(@RequestParam("name") String name);

    @RequestMapping(method = RequestMethod.POST, value = "/users",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    void createUser(@RequestBody User user);

}
