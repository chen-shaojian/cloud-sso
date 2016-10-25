package com.test.client.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.test.common.models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class UserService {
	 @Autowired
	 RestTemplate restTemplate;
	 @Autowired
	 private UserClient userClient;
	@Autowired
	private UserRedis userRedis;
	private static final String keyHead = "client:getUserByName:";

	 
	 @HystrixCommand(fallbackMethod = "getUserFallback")
	 public User getUserByName(String name) {
		 Map<String, Object> params = new HashMap<>();
		 params.put("name", name);
		 //User user = restTemplate.getForObject("http://web/users/search/findByName?name={name}", User.class, params);
		 User user = userClient.findUser(name);
		 return user;
	 }

	public User getUser(String name) {
		User user = userRedis.get(keyHead + name);
		if(user == null){
			user = userClient.findUser(name);
			if(user != null)
				userRedis.add(keyHead + name, 30L, user);
		}
		return user;
	}

	 private User getUserFallback(String name) {
		 User user = new User();
		 user.setName(name + " not find");
		 user.setEmail("user email");
		 user.setSex(0);
		 user.setCreate(new Date());

		 Unit unit = new Unit();
		 unit.setName("unit name");

		 user.setUnit(unit);

		 Role role = new Role();
		 role.setName("role name");

		 Set<Role> roles = new HashSet<>();
		 roles.add(role);

		 user.setRoles(roles);

		 return user;
	 }
}
