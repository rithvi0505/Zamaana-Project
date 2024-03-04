package com.project.zamaana.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.zamaana.entity.Users;
import com.project.zamaana.repository.UserRepo;

@Service
public class UsersServiceImp implements UsersService{

	@Autowired
	UserRepo urepo;
	
	
	@Override
	public String addUser(Users user) {
		
		urepo.save(user);
		return null;
	}


	@Override
	public boolean emailExists(String email) {
		
		if(urepo.findByEmail(email)==null)
		{
			return true;
		}
		else {
		return false;
		}
	}


	@Override
	public boolean loginUser(String email, String password) {
		
		if(urepo.findByEmail(email)!=null) //making sure that the user trying to login has firstly registered  or not,
											//if the email exists, then only validate the user
		{
			String dbPassword=urepo.findByEmail(email).getPassword();
			
			if(!dbPassword.equals(password))
			{
				return false;
			}
			else {
			
			return true;
			}
		}
		else {
			
			return false;		//if the email doesnt exist, then return false
		}
		
	}


	@Override
	public String loginRole(String email) {
		
		return urepo.findByEmail(email).getRole();
	}


	@Override
	public Users getUser(String email) {				//this method is associated to verification of users being paid or unpaid
	
		return urepo.findByEmail(email);
	
	}


	@Override
	public void updateUser(Users user) {
		urepo.save(user);
		
	}

}
