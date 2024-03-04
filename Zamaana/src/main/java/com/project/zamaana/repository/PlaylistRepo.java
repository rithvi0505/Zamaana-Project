package com.project.zamaana.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.zamaana.entity.Playlist;

public interface PlaylistRepo extends JpaRepository<Playlist, Integer>{
	

	List<Playlist> findByUserEmail(String email);

	

}
