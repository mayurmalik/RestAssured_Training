package tests.Day6;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

public class MockApiResponse {
	
	
	@Test
	public void mockApiResponse() {
		
		// fist create your mock api response using various available websites like https://designer.mocky.io/

	   given().log().all().when().get("https://run.mocky.io/v3/0170a1af-b15f-48fa-9bd4-67f849c170c2").then().log().body().assertThat().statusCode(200);
		
		
	}

}
