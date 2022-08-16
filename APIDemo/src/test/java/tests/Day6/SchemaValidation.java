package tests.Day6;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import tests.Day5.Friends;

public class SchemaValidation {

	private static RequestSpecification requestSpec;

	

	@BeforeClass
	public static void createRequestSpecification() {

		requestSpec = new RequestSpecBuilder().setContentType(ContentType.JSON).addPathParam("path", "friends")
				.setBaseUri("http://localhost:3000/").build();

	}

	@Test
	public void serilizationWithLombok() throws JsonProcessingException {
		
		// validating the schema for friends endpoint 

		given().spec(requestSpec).when().get("/{path}").then().log().body().assertThat().body(matchesJsonSchemaInClasspath("friends-schema.json")).statusCode(200);

		
		
		
	}
}
