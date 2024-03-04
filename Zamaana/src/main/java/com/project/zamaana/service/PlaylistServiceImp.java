package com.project.zamaana.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.zamaana.entity.Playlist;
import com.project.zamaana.entity.Songs;
import com.project.zamaana.repository.PlaylistRepo;

@Service
public class PlaylistServiceImp implements PlaylistService {

	@Autowired
	PlaylistRepo prepo;
	
	
	@Override
	public void createPlaylist(Playlist plist,String email) {
		
		plist.setUserEmail(email);
		prepo.save(plist);
		
	}

	@Override
	public List<Playlist> fetchAllPlaylists() {
		
		return prepo.findAll();
	}

	@Override
	public List<Playlist> fetchMyPlaylists(String email) {
		
		List<Playlist> playlist=prepo.findByUserEmail(email);
		return playlist;
	}

	
	

	
	
	
}
