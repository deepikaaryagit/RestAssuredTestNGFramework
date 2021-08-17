package com.spotify.oauth2.api;

import static com.spotify.oauth2.api.SpecBuilder.getResponseSpecification;
import static io.restassured.RestAssured.given;

import java.time.Instant;
import java.util.HashMap;

import com.spotify.oauth2.api.applicationApi.RestResource;
import com.spotify.oauth2.utils.ConfigLoader;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class TokenManager {
	
	private static String access_token;
	private static Instant expiry_time;
	
	public synchronized static String getToken() {
		
		try {
			
			if(access_token==null || Instant.now().isAfter(expiry_time))
				{
					System.out.println("Renewing Token...");
					Response response = renewToken();
					access_token = response.path("access_token");
					int expiryDurationinSec = response.path("expires_in");
					expiry_time = Instant.now().plusSeconds(expiryDurationinSec - 300);
				}
			else {
					System.out.println("Token is good to use...");
				}
			}
		catch(Exception ex)
		{
			throw new RuntimeException("Abort!!! , Failed to get token");
			
			
		}
		
		return access_token;
	}
	
	public static Response renewToken() {
		
		HashMap<String, String> formParams = new HashMap<>();
		formParams.put("grant_type", ConfigLoader.getInstance().getGrantType());
		formParams.put("refresh_token", ConfigLoader.getInstance().getRefreshToken());
		formParams.put("client_id", ConfigLoader.getInstance().getClientId());
		formParams.put("client_secret", ConfigLoader.getInstance().getClientSecret());
		
		
		
		Response response =RestResource.postAccount(formParams);
		if(response.statusCode()!=200)
		{
			throw new RuntimeException("ABORT, Not able to Renew Token");
			
		}
			
		return response;	
			
		
		
		
		
		
	}

}
