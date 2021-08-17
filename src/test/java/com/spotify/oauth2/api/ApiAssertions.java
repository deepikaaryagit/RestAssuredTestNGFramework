package com.spotify.oauth2.api;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import com.spotify.oauth2.pojo.Error;

import io.qameta.allure.Step;

public class ApiAssertions {
	
	@Step
	public static void assertStatusCode(int actualStatusCode, StatusCode statusCode) {

		assertThat(actualStatusCode, equalTo(statusCode.getCode()));

	}
	@Step
	public static void assertError(Error responseError, StatusCode statusCode) {

		assertThat(responseError.getError().getStatus(), equalTo(statusCode.getCode()));
		assertThat(responseError.getError().getMessage(), equalTo(statusCode.getMessage()));

	}

}
