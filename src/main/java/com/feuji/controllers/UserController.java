package com.feuji.controllers;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.feuji.models.User;
import com.feuji.services.CustomUserDetailsService;
import com.feuji.services.UserService;


@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	UserService userService;
	
	//sample home page
	@GetMapping("/home")
	public String home() {
		return "this is home page";
	}
	
	//all user
//	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/")
	public List<User> getAllUser()
	{
		return userService.getAllUser();
	}
	
	//single user
	@GetMapping("/{userName}")
	public User getUser(@PathVariable("userName") String userName)
	{
		return userService.getUser(userName);
	}
	
	//add user
	//requestbody deserialize our json data in object and mapping also 
	@PostMapping("/")
	public User addUser(@RequestBody User user)
	{
		return userService.addUser(user);
	}
	
	@GetMapping("/add")
	public User addingUser() {
		return userService.addingUser();
	}

}
