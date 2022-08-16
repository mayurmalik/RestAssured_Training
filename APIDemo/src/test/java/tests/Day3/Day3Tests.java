package tests.Day3;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.Map;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import utilities.ExcelUtility;

public class Day3Tests {
	

	// get calls test cases using static dataprovider
	
    @Test(dataProvider = "getPostEndPointData")
    public void getMethodForPostEndPoint(String path, String postId, String expectedTitle) {

        given().
            pathParam("path", path).pathParam("postId", postId).
        when().
            get("http://localhost:3000/{path}/{postId}").
        then().log().body().
            assertThat().
            body("title", equalTo(expectedTitle));
    }
    
    
 // get calls test cases using excel data provider
   

    @Test(dataProvider = "getApiEndPointData", dataProviderClass = ExcelUtility.class,testName="verifyExcelDataProvider_CommentsEndPoint_Test")
    public void verifyExcelDataProvider_CommentsEndPoint_Test(String methodName, String serviceEndpoint, Map<String,String> headerMap, Map<String,String> queryParamMap,Map<String,Object> pathParamMap,int statusCode,String responseMessage) {
    System.out.println(pathParamMap);
    	given().
    		pathParams(pathParamMap).
        when().
            get(serviceEndpoint).
        then().log().all().
            assertThat().
            statusCode(statusCode).
           body("body", equalTo(responseMessage));
    }
    
    // test case to demonstrate how to set query parameters
    @Test
    public void queryParamSet(){
    	
    	given().contentType(ContentType.JSON).queryParam("page", "2").when().get("https://reqres.in/api/users/2").then().log().body().assertThat()
		.statusCode(200);
    }
    
    
    @DataProvider(name = "getPostEndPointData")
    public static Object[][] postEndPointData() {
        return new Object[][] {
            { "posts", "1", "API-Automation Day 1"},
            { "posts", "2", "API-Automation Day 2" },
            { "posts", "3", "API-Automation Day 3"},
            { "posts", "4", "API-Automation Day 4"}
        };
    }
    
   
}
