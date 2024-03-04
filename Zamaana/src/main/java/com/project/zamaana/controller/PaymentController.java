package com.project.zamaana.controller;

import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.zamaana.entity.Songs;
import com.project.zamaana.entity.Users;
import com.project.zamaana.service.SongsService;
import com.project.zamaana.service.UsersService;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import com.razorpay.Utils;

import jakarta.servlet.http.HttpSession;

@Controller
public class PaymentController {
	
	@Autowired
	UsersService userv;
	
	@Autowired
	SongsService sserv;
	
	@PostMapping("/createOrder")
	@ResponseBody						//ResponseBody, not RequestBody
	
	public String createOrder(HttpSession session)
	{
		if(session.getAttribute("email")!=null) {
				Order order=null;
				
				try {
				RazorpayClient razorpay = new RazorpayClient("rzp_test_NCQKKcYiOJtb7w", "LTWZGf1EhE86hh5NTbjFYWzp");
		
				JSONObject orderRequest = new JSONObject();
				orderRequest.put("amount",50000);
				orderRequest.put("currency","INR");
				orderRequest.put("receipt", "receipt#1");
				JSONObject notes = new JSONObject();
				notes.put("notes_key_1","Tea, Earl Grey, Hot");
				orderRequest.put("notes",notes);
		
				 order = razorpay.orders.create(orderRequest);
				}
				catch(Exception e)
				{
					System.out.println("Something Went Wrong! Payment Failed");
				}
				
				return order.toString();
		}
		else {
			
			return "loginUser";	//this has to be fixed// When the user has logged out, but somehow reaches the payment page, it should redirect to login page
			
		}
	}
	
	
	@PostMapping("/verify")
	@ResponseBody
	
	public boolean verifyPayment(@RequestParam  String orderId, @RequestParam String paymentId, @RequestParam String signature) {
	    try {
	        // Initialize Razorpay client with your API key and secret
	        RazorpayClient razorpayClient = new RazorpayClient("rzp_test_NCQKKcYiOJtb7w", "LTWZGf1EhE86hh5NTbjFYWzp");
	        // Create a signature verification data string
	        String verificationData = orderId + "|" + paymentId;

	        // Use Razorpay's utility function to verify the signature
	        boolean isValidSignature = Utils.verifySignature(verificationData, signature, "LTWZGf1EhE86hh5NTbjFYWzp");

	        return isValidSignature;
	    } catch (RazorpayException e) {
	        e.printStackTrace();
	        return false;
	    }
	}
	
	
	/////////////////////////////////////////////this endpoint should directly provide the songs when homeCustomer is loaded/////////
	@GetMapping("payment-success")
	public String paymentSuccess(Model model,HttpSession session)
	{
		System.out.println("I am in paymentSuccess");
		
		

		if(session.getAttribute("email")!=null) {
			
			
					System.out.println("I am inside session");		//there is a flaw. Logged in user can bypass the payment with the "homecustomer end point
					
					String email=(String)session.getAttribute("email");
					Users user=userv.getUser(email);
					user.setPaid(true);
					userv.updateUser(user);
					
					System.out.println("I have passed updateUser");
					
					List<Songs> songslist=sserv.fetchAllSongs();
					model.addAttribute("songslist",songslist);
					return "homeCustomer";
			
		}
		else {
			
			System.out.println("I am inside else");
				return "loginUser";
			}
	}
	

	
	

}
