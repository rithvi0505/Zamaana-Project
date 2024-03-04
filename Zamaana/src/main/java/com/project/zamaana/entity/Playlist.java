package com.project.zamaana.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@Entity
public class Playlist {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	int id;
	
	private String playlistName;
	
	@ManyToMany
	private List<Songs> song;
	
	private String userEmail;

	public Playlist() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Playlist(int id, String playlistName, List<Songs> song, String userEmail) {
		super();
		this.id = id;
		this.playlistName = playlistName;
		this.song = song;
		this.userEmail = userEmail;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPlaylistName() {
		return playlistName;
	}

	public void setPlaylistName(String playlistName) {
		this.playlistName = playlistName;
	}

	public List<Songs> getSong() {
		return song;
	}

	public void setSong(List<Songs> song) {
		this.song = song;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	@Override
	public String toString() {
		return "Playlist [id=" + id + ", playlistName=" + playlistName + ", song=" + song + ", userEmail=" + userEmail
				+ "]";
	}
	

	
	

}
