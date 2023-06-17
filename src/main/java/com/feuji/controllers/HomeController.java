package com.feuji.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/public")
public class HomeController {
	
	//sample home page
	@GetMapping("/home")
	public String home() {
		return "this is home page";
	}
	
	//sample home page
	@GetMapping("/login")
	public String login() {
		return "this is login page";
	}
	
	//sample home page
	@GetMapping("/register")
	public String register() {
		return "this is register page";
	}

}
