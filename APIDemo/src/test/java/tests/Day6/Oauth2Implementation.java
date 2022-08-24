package tests.Day6;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;

public class Oauth2Implementation {
	
	// i have generated my personal access token from my git hub account in developers settings
	String token = "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" ;
	
	String url = "https://github.com/mayurmalik/repos";
	
	@Test
	public void GithubApiTest() {
		
	
	given().auth().oauth2(token).when().get(url).then().log().body();
	
		
		
		
	}

}
