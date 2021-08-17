package com.spotify.oauth2.tests;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static com.spotify.oauth2.utils.FakerUtils.generateName;
import static com.spotify.oauth2.utils.FakerUtils.generateDescription;
import org.testng.annotations.Test;

import com.spotify.oauth2.api.ApiAssertions;
import com.spotify.oauth2.api.StatusCode;
import com.spotify.oauth2.api.applicationApi.PlaylistApi;
import com.spotify.oauth2.pojo.Error;
import com.spotify.oauth2.pojo.Playlist;
import com.spotify.oauth2.utils.DataLoader;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Issue;
import io.qameta.allure.Link;
import io.qameta.allure.Step;
import io.qameta.allure.Story;
import io.qameta.allure.TmsLink;
import io.restassured.response.Response;

@Epic("Spotify Oauth2.0 EPIC")
@Feature("Playlist Api Feature")
public class PlaylistTests extends BaseTest{

	@Story("Create a PlayList Story")
	@Link("https://example.org")
	@Link(name = "allure", type = "mylink")
	@TmsLink("Jira")
	@Issue("123456")
	@Issue("123")
	@Description("This is the Create PlayList Testcase from Maven")
	
	@Test(description="Create Playlist Testcase")
	public void createPlaylistWithName() {

		Playlist requestPlaylist = playlistBuilder(generateName(), generateDescription(), false);

		Response response = PlaylistApi.post(requestPlaylist);
		
		Playlist responsePlaylist = response.as(Playlist.class);
		assertPlayListEqual(requestPlaylist, responsePlaylist);
		ApiAssertions.assertStatusCode(response.statusCode(),  StatusCode.CODE_201);


	}

	@Story("Create a PlayList Story")
	@Test
	public void updatePlaylistName() {
		Playlist requestPlaylist = playlistBuilder("Updated PlayList", "Updated Description", false);

		Response response = PlaylistApi.put(DataLoader.getInstance().getPlaylistid(), requestPlaylist);

		Playlist responsePlaylist = response.as(Playlist.class);
		assertPlayListEqual(requestPlaylist, responsePlaylist);
		ApiAssertions.assertStatusCode(response.statusCode(),  StatusCode.CODE_200);

	}

	@Story("Create a PlayList Story")
	@Test
	public void getPlaylist() {

		Response response = PlaylistApi.get(DataLoader.getInstance().getPlaylistid());
		ApiAssertions.assertStatusCode(response.statusCode(),  StatusCode.CODE_200);
		assertThat(response.contentType(), equalTo("application/json; charset=utf-8"));

	}

	// Negative Testcase
	@Test
	public void notAbleToCreatePlaylistWithoutName() {
		Playlist requestPlaylist = playlistBuilder("", generateDescription(), false);

		Error responseError = PlaylistApi.post(requestPlaylist).as(Error.class);

		ApiAssertions.assertError(responseError, StatusCode.CODE_400);

	}

	// Negative Testcase
	@Test
	public void notAValidToken() {

		String inValidToken = "Bearer " + "12345";

		Playlist requestPlaylist = playlistBuilder(generateName(), generateDescription(), false);

		Error responseError = PlaylistApi.post(inValidToken, requestPlaylist).as(Error.class);
		ApiAssertions.assertError(responseError, StatusCode.CODE_401);

	}
	
	//----------------Common Methods to this test class -------------------//
	@Step
	public Playlist playlistBuilder(String name, String description, boolean _public) {
				
			return Playlist.builder().name(name).description(description)._public(_public).build();
	}
	
	@Step
	public void assertPlayListEqual(Playlist requestPlaylist, Playlist responsePlaylist) {
		assertThat(responsePlaylist.getName(), equalTo(requestPlaylist.getName()));
		assertThat(responsePlaylist.getDescription(), equalTo(requestPlaylist.getDescription()));
		assertThat(responsePlaylist.get_public(), equalTo(requestPlaylist.get_public()));

	}

	
}
