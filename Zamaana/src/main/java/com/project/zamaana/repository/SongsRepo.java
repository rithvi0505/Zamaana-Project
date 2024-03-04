package com.project.zamaana.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.zamaana.entity.Songs;

public interface SongsRepo extends JpaRepository<Songs, Integer> {

	public Songs findBySongName(String songName);
}
