package com.project.zamaana.controller;

import java.util.List;

import org.hibernate.internal.build.AllowSysOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.zamaana.entity.Songs;
import com.project.zamaana.service.SongsService;

import jakarta.servlet.http.HttpSession;

@Controller
public class SongsController {
	
	@Autowired
	SongsService sserv;
	
	@PostMapping("/addSongs")
	public String addSongs(@ModelAttribute Songs song, HttpSession session)
	{
		if(session.getAttribute("email")!=null) 		//logged out penetration handled
		{
			if(sserv.songExists(song.getSongName())==true)		//has to to false or what, just see once
			{
				sserv.addSongs(song);
				return "addSongSuccess";
			}
			else
			{
				return "addSongFail";
			}
		}
		else {
			
			return "loginUser";
		}
	}
	
	@GetMapping("/viewSongs")
	public String viewSongs(Model model,HttpSession session)  //we use model whenever we want to get things from the database to the view using thymeleaf.
	{
		System.out.println(session.getAttribute("email"));
		
		if(session.getAttribute("email")!=null)			//logged out penetration handled
		{
		List<Songs> songslist=sserv.fetchAllSongs();
		model.addAttribute("songslist",songslist);
		return "viewSongsPublisher";
		}
		else {
			
			return "loginUser";
		}
	}
	
	//////////////////////////////////////////////////////////////////////////// have to work on this
	//this homeCustomer is to be displayed just after payment is successful////////////////////////
	
	@GetMapping("/homeCustomer")				//this has to be removed or something, this was just a temporary code before I added payment gateway
	public String homeCustomer(Model model,HttpSession session)
	{
		if(session.getAttribute("email")!=null) 
		{
		List<Songs> songslist=sserv.fetchAllSongs();
		model.addAttribute("songslist",songslist);
		return "homeCustomer";
		}
		else {
			
			return "loginUser";
		}
	}
	
	//////////////////////////////////////////////////////////////////////////////
	
	@PostMapping("/searchSong")														//Search Song Feature experiment
	public String searchSong(@RequestParam String songName, Model model,HttpSession session)
	{
		if(session.getAttribute("email")!=null) {
			if(sserv.findSong(songName)!=null)
			{
				Songs song=sserv.findSong(songName);
				model.addAttribute("song",song);
				return "songFound";
				
			}
			else {
			
			return "songNotFound";
			}
		}
		else {
			return "loginUser";
		}
	}

}
