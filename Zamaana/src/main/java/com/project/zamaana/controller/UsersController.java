package com.project.zamaana.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.zamaana.entity.Songs;
import com.project.zamaana.entity.Users;
import com.project.zamaana.service.SongsService;
import com.project.zamaana.service.UsersService;

import jakarta.servlet.http.HttpSession;

@Controller
public class UsersController {
	
	@Autowired
	UsersService userv;
	
	@Autowired
	SongsService sserv;
	
	@PostMapping("registerUser")
	public String registerUser(@ModelAttribute Users user)
	{
		if(userv.emailExists(user.getEmail())==true)
		{
			userv.addUser(user);
			return "registerSuccess";
		}
		else
		{
			return "registerFail";
		}
	}
	
	@PostMapping("loginUser")
	public String loginUser(@RequestParam String email, @RequestParam String password, HttpSession session,Model model)
	{
		
		
		if(userv.loginUser(email,password)==true)
		{
			session.setAttribute("email",email);
			if(userv.loginRole(email).equals("customer"))		//this part is having a problem. only the else part is executing.
			{													
				if(userv.getUser(email).isPaid()==true)
				{	
					
					List<Songs> songslist=sserv.fetchAllSongs();
					model.addAttribute("songslist",songslist);
					return "homeCustomer";							//////////code here to navigate paid user to home and unpaid to payment page//////
																		/////////////////////////////////////////////////////////////////////////
				}												
				else
				{
					return "paymentCustomer";
				}
										
			}
			else {
				return  "homePublisher";
			}
		}
		else 
		{
		return "loginFail";
		}
	}
	
	
	@GetMapping("/logoutCustomer")
	public String logoutCustomer(HttpSession session)		//this is to invalidate the session
	{
		session.invalidate();
		return "loginUser";
	}
}
