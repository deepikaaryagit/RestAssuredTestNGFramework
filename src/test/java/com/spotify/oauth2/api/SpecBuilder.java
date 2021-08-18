package com.spotify.oauth2.api;

import org.testng.annotations.BeforeTest;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class SpecBuilder {
	
	
	public static  RequestSpecification getRequestSpecification() {
		
	     RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
		return requestSpecBuilder
						.setBaseUri(System.getProperty("BASE_URI"))
						//.setBaseUri("https://api.spotify.com/")
						.setContentType(ContentType.ANY)
						.addFilter(new AllureRestAssured())
						.log(LogDetail.ALL).build();
		
				
		
	}
	
	public static  RequestSpecification getAccountRequestSpecification() {
		
	     RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
		return requestSpecBuilder
						.setBaseUri(System.getProperty("ACCOUNT_BASE_URI"))
						//.setBaseUri("https://accounts.spotify.com")
						.setContentType(ContentType.URLENC)
						.addFilter(new AllureRestAssured())
						.log(LogDetail.ALL).build();
		
				
		
	}
	
	public static  ResponseSpecification getResponseSpecification() {
		
	    	
						  	
		ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder();
		return responseSpecBuilder
							.log(LogDetail.ALL).build();
		
	}

}
