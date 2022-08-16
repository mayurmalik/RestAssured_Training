package tests.Day2;

import static io.restassured.RestAssured.*;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.itextpdf.text.log.SysoCounter;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

import static org.hamcrest.Matchers.*;

public class Day2Tests {

	@BeforeTest
	public void setup() {

		RestAssured.baseURI = "https://reqres.in/";
	}

	// JsonPath , logging of response  and hamcrest matcher related test cases for day2 assignment
	
	@Test
	public void logRequestAndResponseBody() {

	   given().log().all().when().get("/api/users/").then().log().body().assertThat().statusCode(200);
		
		
	}

	@Test
	public void hamCrestMatcher() {

		
		// we can perform multiple hamcrest matcher validation by using and keyword
		
		given().contentType(ContentType.JSON).when().get("/api/users/2").then().log().body().assertThat()
		.body("data.id",equalTo(2)).and().body("data.first_name",equalTo("Janet"));
		
		
		// also we have other available methods for hamcrest matcher library like greaterThan, lessThan,containsString,
		//startsWith, endsWith
		
		
	given().contentType(ContentType.JSON).when().get("/api/users/2").then().assertThat()
	.body("data.id", greaterThan(1));
	
	given().contentType(ContentType.JSON).when().get("/api/users/2").then().assertThat()
	.body("data.id", lessThan(5));
	
	given().contentType(ContentType.JSON).when().get("/api/users/2").then().assertThat()
	.body("support.url", containsString("reqres"));
	
		
	}
	

	@Test
	public void jsonPathValidation() {

		String response =given().log().all().when().get("/api/users/3").then().statusCode(200).extract().response().prettyPrint();
			
		JsonPath extractor = new JsonPath(response);
		
		// here we are getting the all fields value of the response using jsonpath
		
		int id = extractor.getInt("data.id");
		
		String email = extractor.getString("data.email");
		
		String first_name = extractor.getString("data.first_name");
		
		String last_name = extractor.getString("data.last_name");
		
		System.out.println("id value fethced using json path is "+id );
		System.out.println("email value fetched using json path is "+email );
		System.out.println("first name fetched using json path is "+first_name );
		System.out.println("last name value fetched using json path is "+last_name );
				
		
	}
	
	//nested json handling using jsopnPath
	
	@Test
	public void nestedJsonValidation() {

		String response =given().when().get("/api/unknown").then().log().body().statusCode(200).extract().response().asString();
			
		JsonPath extractor = new JsonPath(response);
		
	// getting the all records count inside data object
		
		int data_count = extractor.getInt("data.size()");
		
		System.out.println("all data counts are"+ data_count);
		
		// here we are printing the name and year of all the records inside  the data object 
		
		for(int i=0 ; i<data_count ; i++) {
			
			System.out.println("name is " + extractor.getString("data["+i+"].name"));
			
			
		    System.out.println("year is " + extractor.getString("data["+i+"].year"));
		}
		
	
	}
	
	

}
