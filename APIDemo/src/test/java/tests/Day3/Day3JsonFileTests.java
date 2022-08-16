package tests.Day3;


import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.File; 

import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;

public class Day3JsonFileTests {

    @Test
    public void verifySimpleJSONFile_Test() {
    	File file = new File("src/test/resources/Simple.json");

    	String response = given().
    		contentType(ContentType.JSON).
    		body(file).
    	when().
    		post("http://localhost:3000/friends").
    	then().
    		assertThat().
    		statusCode(201).extract().response().prettyPrint();
    	
    	JsonPath extractor = new JsonPath(response);
    	
    	String id = extractor.getString("id");
    	
    	// fetching the above created resource id and using that id in get call to fetch the recently created user details.
    	
    	// hitting the get call to verify the above created resource
    	given().
		contentType(ContentType.JSON).
	when().
		get("http://localhost:3000/friends/"+id+"").
	then().
		assertThat().
		statusCode(200).body("firstname",equalTo("Manu")).and().body("id",equalTo(161));
    }
    
    @Test
    public void verifyComplexJSONFile_Test() {
    	File file = new File("src/test/resources/Complex.json");

    	given().
    		contentType(ContentType.JSON).
    		body(file).
    	when().
    		post("http://localhost:3000/friends").
    	then().
    		assertThat().
    		statusCode(201);
    	
    	// hitting the get call to verify the above created resource
    	given().
		contentType(ContentType.JSON).
	when().
		get("http://localhost:3000/friends/188").
	then().
		assertThat().
		statusCode(200).body("address[1].streetName",equalTo("hinjewadi road")).and().body("id",equalTo(188));
    	
    }
   
}
