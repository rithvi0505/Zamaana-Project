package com.project.zamaana.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.zamaana.entity.Songs;
import com.project.zamaana.repository.SongsRepo;

@Service
public class SongsServiceImp implements SongsService{

	@Autowired
	SongsRepo srepo;
	
	@Override
	public void addSongs(Songs song) {
	
		srepo.save(song);
	}

	@Override
	public boolean songExists(String songName) {
		if(srepo.findBySongName(songName)==null)		 //In the SongRepository interface, findBySongname is indeed a user-defined method.
														//It follows Spring Data JPA's query method naming convention. By naming the method 
														//in this specific way (findBy followed by the name of the entity attribute, in this case, 
														//songname), Spring Data JPA automatically generates the appropriate query to find a Song 
														//entity by its songname attribute.
		{
			return true;
		}
		else {
			return false;
		}
		
	}

	@Override
	public List<Songs> fetchAllSongs() {
		
		return srepo.findAll();
		
	}

	@Override
	public void updateSong(Songs songs) {
		
		srepo.save(songs);
		
	}

	@Override
	public Songs findSong(String songName) {
		
		if(srepo.findBySongName(songName)!=null)
		{
			return srepo.findBySongName(songName);
		}
		else
		{
		return null;
		}
	}
}
