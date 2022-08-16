package tests.Day4;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Day4Tests {

	private static RequestSpecification requestSpec;

	private static ResponseSpecification responseSpec;

	@BeforeClass
	public static void createRequestSpecification() {

		requestSpec = new RequestSpecBuilder().setContentType(ContentType.JSON).addPathParam("path", "posts")
				.setBaseUri("http://localhost:3000/").build();

	}

	@BeforeClass
	public static void createResponseSpecification() {

		responseSpec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).expectBody("author", equalTo("Sachin")).build();
	}

	@Test
	public void day3TestWithRequestSpec() {

		given().spec(requestSpec).when().get("/{path}/1").then().log().body().assertThat().body("title", equalTo("API-Automation Day 1"));

	}
	
	@Test
	public void day3TestWithRequestAndResponseSpec() {

		given().spec(requestSpec).when().get("/{path}/2").then().spec(responseSpec);

	}
	
}
