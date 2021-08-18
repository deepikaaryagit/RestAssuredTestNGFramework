package com.spotify.oauth2.api.applicationApi;

import static com.spotify.oauth2.api.SpecBuilder.getRequestSpecification;
import static com.spotify.oauth2.api.SpecBuilder.getAccountRequestSpecification;
import static com.spotify.oauth2.api.SpecBuilder.getResponseSpecification;
import static io.restassured.RestAssured.given;
import static com.spotify.oauth2.api.Route.API;
import static com.spotify.oauth2.api.Route.TOKEN;
import java.util.HashMap;

import com.spotify.oauth2.pojo.Playlist;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class RestResource {
	
	public static Response post(String path, String token, Object requestPlaylist) {
		
		return given().spec(getRequestSpecification())
				.auth().oauth2(token)
				//.header("Authorization", "Bearer " + token)
				.body(requestPlaylist)
			.when()
				.post(path)
			.then().spec(getResponseSpecification())
				.extract()
				.response()	;
		
	}
	
	
	
	public static Response put(String path, String token, String playlistId, Playlist requestPlaylist)
	{
		
		return given().spec(getRequestSpecification())
				.auth().oauth2(token)
				.contentType(ContentType.ANY)
				//.header("Authorization", "Bearer " + token)
				.body(requestPlaylist)
			.when()
				.put(path)
			.then().spec(getResponseSpecification())
				.extract()
				.response();
		
	}
	
	
	public static Response get(String path, String token, String playlistId) {
		
	return	given().spec(getRequestSpecification())
			.auth().oauth2(token)
			//.header("Authorization", "Bearer " + token)
		.when()
			.get(path+playlistId)
		.then().spec(getResponseSpecification())
			.extract().response();
		
	}

	
	public static Response postAccount(HashMap<String,String> formParams) {
		
		return given().spec(getAccountRequestSpecification())
				.formParams(formParams)
			.when()
				.post(API + TOKEN)
			.then().spec(getResponseSpecification())
			.extract()
			.response();
	}
	

}
