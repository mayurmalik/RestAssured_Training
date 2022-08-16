package tests.Day5;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Day5Tests {
	
	private static RequestSpecification requestSpec;

	
	
	// creating the test case to implement the lombok plugin funtionality 
	
	@BeforeClass
	public static void createRequestSpecification() {

		requestSpec = new RequestSpecBuilder().setContentType(ContentType.JSON).addPathParam("path", "friends")
				.setBaseUri("http://localhost:3000/").build();

	}


	@Test
	public void deserilizationWithLombok() {

		Response response = given().spec(requestSpec).when().get("/{path}/23").then().log().body().extract().response();
		
		Friends friend = response.getBody().as(Friends.class);
		
		System.out.println(friend.toString());
		
		// getting the value of json keys using alreday created getter methods with lombok plugin 
		
		System.out.println(friend.getAge());
		System.out.println(friend.getFirstname());
		System.out.println(friend.getId());
		

	}
	
	@Test
	public void serilizationWithLombok() throws JsonProcessingException {
		
		Friends friend = new Friends();
		
		friend.setAge(35);
		friend.setFirstname("chinks");
		friend.setId(435);
		friend.setLastname("racks");
		
		ObjectMapper objectMapper = new ObjectMapper();
		String json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(friend);
		
		String response =given().spec(requestSpec).body(json).when().post("/{path}").then().log().body().extract().response().asString();
		
		JsonPath extractor = new JsonPath(response);
		
		int id = extractor.getInt("id");
		
		// again hitting the post request with same id to verify server should return the error
		
		friend.setAge(35);
		friend.setFirstname("manu");
		friend.setId(id);
		friend.setLastname("malik");

		
		Response response1 =given().spec(requestSpec).body(friend).when().post("/{path}").then().log().body().extract().response();
		

		Assert.assertEquals(response1.getStatusCode(), 500);
		
	}
	
	

}
