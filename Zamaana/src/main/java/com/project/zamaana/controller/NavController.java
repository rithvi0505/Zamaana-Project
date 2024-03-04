package com.project.zamaana.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class NavController {
	
	@GetMapping("/map-register")
	public String registerUser()
	{
		return "registerUser";
	}
	
	@GetMapping("/map-login")
	public String loginUser()
	{
		return "loginUser";
	}
	
	
	
	@GetMapping("/map-aboutLanding")
	public String aboutLanding()
	{
		return "aboutLanding";
	}
	
	@GetMapping("/map-aboutHome")
	public String aboutHome()
	{
		return "aboutHome";
	}
}
