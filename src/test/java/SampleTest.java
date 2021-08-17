import org.testng.annotations.Test;


import static io.restassured.RestAssured.given;
public class SampleTest {
	
	@Test
	public void getPlaylists() {
		
		given()
		.baseUri("https://api.spotify.com/")
			.header("Authorization", "Bearer BQAzSgYKomruQjr0os4UPLCkeYP0S-pAviE9xBwNcLrewZP8ANBNuk54o6y3a4VK-e3QN9tl0x2GIng1icWAK8aLhRaBnB5TWCBgOFSsxOVDjCLfFb4ZXxUh-4X_nsUP_B6meSOrCEpGeShP4Sde8937-t28k64HFsBtU5VhKiIbhIcrNaCV1u_q8qJNICzCkWskQVFCY6yVqIvF_wKfQG39Gx2h9ZlRCqt1XF2-Q2Kw")
			.log()
			.all()
		.when()
			.get("/v1/playlists/4cUZWtk6ms28XysWhFV09g")
		.then()
			.log()
			.all();
	}

}
