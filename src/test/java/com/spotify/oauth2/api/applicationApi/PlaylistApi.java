package com.spotify.oauth2.api.applicationApi;

import static com.spotify.oauth2.api.Route.BASE_PATH;
import static com.spotify.oauth2.api.Route.PLAYLISTS;
import static com.spotify.oauth2.api.Route.USERS;
import static com.spotify.oauth2.api.TokenManager.getToken;

import com.spotify.oauth2.pojo.Playlist;
import com.spotify.oauth2.utils.ConfigLoader;

import io.qameta.allure.Step;
import io.restassured.response.Response;
public class PlaylistApi {
	
	
	@Step
	public static Response post(Playlist requestPlaylist) {
		return RestResource.post(BASE_PATH + USERS + "/" + ConfigLoader.getInstance().getUserId() + PLAYLISTS, getToken(), requestPlaylist);
		
	}
	
	public static Response post(String token, Playlist requestPlaylist) {
		
		return RestResource.post(BASE_PATH + USERS + "/"+ ConfigLoader.getInstance().getUserId() + PLAYLISTS, token, requestPlaylist);
		
		
	}
	
	public static Response put(String playlistId, Playlist requestPlaylist)
	{
		
		return RestResource.put(BASE_PATH + USERS +"/"+ConfigLoader.getInstance().getUserId() + PLAYLISTS, getToken(),playlistId, requestPlaylist);
	}
	
	
	public static Response get(String playlistId) {
		
		return RestResource.get(BASE_PATH + PLAYLISTS +"/" , getToken(), playlistId)	;
	
		
	}
	
	

}
