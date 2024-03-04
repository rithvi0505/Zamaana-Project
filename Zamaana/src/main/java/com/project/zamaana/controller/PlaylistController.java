package com.project.zamaana.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.zamaana.entity.Playlist;
import com.project.zamaana.entity.Songs;
import com.project.zamaana.service.PlaylistService;
import com.project.zamaana.service.SongsService;

import jakarta.servlet.http.HttpSession;

@Controller
public class PlaylistController {
	
	@Autowired
	PlaylistService pserv;
	@Autowired
	SongsService sserv;
	
	@GetMapping("/createPlaylist")
	public String createPlaylist(Model model,HttpSession session)
	{
		if(session.getAttribute("email")!=null)		//logged out penetration handled
		{
		List<Songs> songslist=sserv.fetchAllSongs();
		model.addAttribute("songslist",songslist);
		return "createPlaylist";
		}
		else
		{
			return "loginUser";
		}
	}
	
	@PostMapping("/createPlaylist")
	public String createPlaylist(@ModelAttribute Playlist plist,HttpSession session)
	{
		if(session.getAttribute("email")!=null)		//logged out penetration handled
		{
			System.out.println(plist);
			
			String email=(String)session.getAttribute("email");						//fetching email of the playlist creator	
			pserv.createPlaylist(plist,email);
			
			//I have created the playlist. Now I have to get the playlist, updated in the songs too
			List<Songs> songslist=plist.getSong();
			for(Songs songs:songslist)
			{
				songs.getPlaylist().add(plist);		//since we dont have a setPlaylist, and we only have getPlaylist, I'll add the plist in the List
				sserv.updateSong(songs);
			}
			
			return "createPlaylistSuccessful";
		}
		else
		{
			return "loginUser";
		}
	}
	
	@GetMapping("/viewPlaylist")
	public String viewPlaylist(Model model,HttpSession session)
	{
		if(session.getAttribute("email")!=null) 		//logged out penetration handled
		{
		List<Playlist> playlist=pserv.fetchAllPlaylists();
		model.addAttribute("playlist",playlist);
		return "viewPlaylist";
		}
		else {
			return "loginUser";
		}
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/createPlaylistCustomer")
	public String createPlaylistCustomer(Model model,HttpSession session)
	{
		if(session.getAttribute("email")!=null) 		//logged out penetration handled
		{
		
		List<Songs> songslist=sserv.fetchAllSongs();
		model.addAttribute("songslist",songslist);
		return "createPlaylistCustomer";
		}
		else
		{
			return "loginUser";
		}
	}
	
	@PostMapping("/createPlaylistCustomer")
	public String createPlaylistCustomer(@ModelAttribute Playlist plist, HttpSession session,RedirectAttributes redirectAttributes)  //redirectAttributes
	{
		if(session.getAttribute("email")!=null) 		//logged out penetration handled
		{
			System.out.println(plist);
			
			String email=(String)session.getAttribute("email");
			pserv.createPlaylist(plist,email);
			
			//I have created the play list. Now I have to get the play list, updated in the songs too
			List<Songs> songslist=plist.getSong();
			for(Songs songs:songslist)
			{
				songs.getPlaylist().add(plist);		//since we don't have a setPlaylist, and we only have getPlaylist, I'll add the plist in the List
				sserv.updateSong(songs);
			}
			
			 redirectAttributes.addFlashAttribute("message", "Playlist created successfully!");
		        return "redirect:/viewMyPlaylistCustomer"; // Redirect to the user's playlist page
		    
		}
		else {
			
			return "loginUser";
		}
	}
	
	@GetMapping("/viewPlaylistCustomer")
	public String viewPlaylistCustomer(Model model,HttpSession session)
	{
		if(session.getAttribute("email")!=null)							//logged out penetration handled
		{
		List<Playlist> playlist=pserv.fetchAllPlaylists();
		model.addAttribute("playlist",playlist);
		return "viewPlaylistCustomer";
		}
		else {
			return "loginUser";
		}
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	@GetMapping("/viewMyPlaylistCustomer")
	public String viewMyPlaylistCustomer(Model model, HttpSession session)
	{
		if(session.getAttribute("email")!=null) {						//logged out penetration handled
			
			String email=(String)session.getAttribute("email");
			
			
			List<Playlist> myPlaylist=pserv.fetchMyPlaylists(email);
			model.addAttribute("myPlaylist",myPlaylist);
			return "viewMyPlaylistCustomer";
		}
		else
		{
			return "loginUser";
		}
		
	}
	

}
