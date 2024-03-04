package com.project.zamaana.service;

import java.util.List;

import com.project.zamaana.entity.Songs;

public interface SongsService {

	public void addSongs(Songs song);

	public boolean songExists(String songName);

	public List<Songs> fetchAllSongs();

	public void updateSong(Songs songs);

	public Songs findSong(String songName);

}
