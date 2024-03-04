package com.project.zamaana.service;

import com.project.zamaana.entity.Users;

public interface UsersService {
	
	public String addUser(Users user);

	public boolean emailExists(String email);

	public boolean loginUser(String email, String password);

	public String loginRole(String email);

	public Users getUser(String email);

	public void updateUser(Users user);

	
	
	

}
