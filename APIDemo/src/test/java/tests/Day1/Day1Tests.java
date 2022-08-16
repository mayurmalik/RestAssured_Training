package tests.Day1;

import static io.restassured.RestAssured.*;

import org.testng.annotations.Test;

public class Day1Tests {
	
	@Test
    public void getPosts() {	
    given().
        when().log().all().
            get("http://localhost:3000/posts").
        then().log().all().
            assertThat().
            statusCode(200);
    }
	
	@Test
    public void postPosts() {
		
		
//    given().
//        when().log().all()
//            post("http://localhost:3000/posts").body()
//        then().log().all().
//            assertThat().
//            statusCode(200);
    }
	
	

}
