package tests;

import org.testng.annotations.BeforeSuite;

import io.restassured.response.Response;
import io.restassured.response.ResponseOptions;
import utilities.AuthenticationToken;
import utilities.RestAssuredEngine;

public class BaseTest {

	public ResponseOptions<Response> response;
	RestAssuredEngine restAssuredEngine;
	protected String secureToken = "";
	
    @BeforeSuite
    public void beforeSuite(){
    	AuthenticationToken token = new AuthenticationToken();
    	String tokenstr = token.getAuthenticationToken();
    	restAssuredEngine = new RestAssuredEngine(tokenstr);
    }
    

}
