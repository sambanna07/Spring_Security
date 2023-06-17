package com.feuji.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {
	
	
	@GetMapping("/signin")
	public String signin() {
		return "login.html";
	}
	
//	@GetMapping("/logout")
//	public String logout() {
//		return "login.html";
//	}

}
