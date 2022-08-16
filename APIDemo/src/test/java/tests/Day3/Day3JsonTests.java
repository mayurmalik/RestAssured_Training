package tests.Day3;


import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;

public class Day3JsonTests {

    @Test
    public void verifyFriendAddedSimple_Test() {
        JSONObject friendInfo = new JSONObject();
        friendInfo.put("firstname","Mayur");
        friendInfo.put("lastname","Malik");
        friendInfo.put("id",53);
        friendInfo.put("age",28);
        
    	given().
    		contentType(ContentType.JSON).
    		body(friendInfo.toString()).
    	when().
    		post("http://localhost:3000/friends").
    	then().
    		assertThat().
    		statusCode(201);
    	
    	// hitting the get call to verify the above created resource
    	given().
		contentType(ContentType.JSON).
	when().
		get("http://localhost:3000/friends/53").
	then().
		assertThat().
		statusCode(200).body("firstname",equalTo("Mayur"));
    	
    }
    
    @Test
    public void verifyFriendAddedComplexJson_Test() {
        JSONObject addressInfo = new JSONObject();
        addressInfo.put("houseNo","120");
        addressInfo.put("streetName","muzaffarnagar");       
        JSONObject friendInfo = new JSONObject();
        friendInfo.put("firstname","Prajit");
        friendInfo.put("lastname","Sharma");
        friendInfo.put("id",1020);
        friendInfo.put("age",30);
        friendInfo.put("address",addressInfo);    
    	given().
    		contentType(ContentType.JSON).
    		body(friendInfo.toString()).
    	when().
    		post("http://localhost:3000/friends").
    	then().log().body().
    		assertThat().
    		statusCode(201);
    	
    	// hitting the get call to verify the above created resource
    	given().
		contentType(ContentType.JSON).
	when().
		get("http://localhost:3000/friends/1020").
	then().
		assertThat().
		statusCode(200).body("firstname",equalTo("Prajit")).and().body("address.streetName",equalTo("muzaffarnagar"));
    	
    }

    @Test
    public void verifyFriendAddedComplexJsonArray_Test() {
    	JSONArray address = new JSONArray();
        JSONObject primaryAddress = new JSONObject();
        primaryAddress.put("houseNo","120");
        primaryAddress.put("streetName","vc road");  
        JSONObject secondaryAddress = new JSONObject();
        secondaryAddress.put("houseNo","121");
        secondaryAddress.put("streetName","snc road");
        address.put(0,primaryAddress);
        address.put(1,secondaryAddress);
        JSONObject friendInfo = new JSONObject();
        friendInfo.put("firstname","Manu");
        friendInfo.put("lastname","Malik");
        friendInfo.put("id",108);
        friendInfo.put("age",30);
        friendInfo.put("address",address);    
    	given().
    		contentType(ContentType.JSON).
    		body(friendInfo.toString()).
    	when().post("http://localhost:3000/friends").
    	then().log().body().statusCode(201);
    	
    	// hitting the get call to verify the above created resource
    	given().
		contentType(ContentType.JSON).
	when().
		get("http://localhost:3000/friends/108").
	then().
		assertThat().
		statusCode(200).body("firstname",equalTo("Manu")).and().body("address[0].streetName",equalTo("vc road"));
    	
    }
   
}
