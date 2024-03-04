package com.project.zamaana.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.project.zamaana.entity.Users;

public interface UserRepo extends JpaRepository<Users, Integer>{
	
	public Users findByEmail(String email);

}
