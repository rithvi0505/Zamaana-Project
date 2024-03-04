package com.project.zamaana.service;

import java.util.List;

import com.project.zamaana.entity.Playlist;
import com.project.zamaana.entity.Songs;

public interface PlaylistService {

	public void createPlaylist(Playlist plist,String email);

	public List<Playlist> fetchAllPlaylists();

	public List<Playlist> fetchMyPlaylists(String email);





	



}
