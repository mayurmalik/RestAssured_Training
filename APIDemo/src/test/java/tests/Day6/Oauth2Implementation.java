package tests.Day6;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;

public class Oauth2Implementation {
	
	
	
	@Test
	public void serilizationWithLombok() throws JsonProcessingException {
		
		// validating the schema for friends endpoint 

		//given().spec(requestSpec).when().get("/{path}").then().log().body().assertThat().body(matchesJsonSchemaInClasspath("friends-schema.json")).statusCode(200);

		
		
		
	}

}
