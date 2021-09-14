package stepDefinitions;



import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import reources.APIResources;
import reources.TestDataBuild;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.assertEquals;

import java.io.IOException;

import Utilities.utils;

public class StepDefinition extends utils{
	
	RequestSpecification res;
	ResponseSpecification resspec;
	Response response;
	TestDataBuild tb = new TestDataBuild();
	static String place_id;
	
	@Given("Add place Payload with {string} {string} {string} {string}")
	public void add_place_payload_with(String name, String language, String Address , String data) throws IOException {
		
		resspec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		
		res = given().spec(RequestSpecification()).body(tb.addplacePayload(name, language, Address, data));
	    System.out.println("The place is added");
	}


	@When("user calls {string} with {string} http request")
	public void user_calls_with_post_http_request(String resource, String httpmethod) {
	    // Write code here that turns the phrase above into concrete actions
	
		APIResources resourceAPI= APIResources.valueOf(resource);
		
		if(httpmethod.equals("post")) 
			response = res.when().post(resourceAPI.getResource());
		if(httpmethod.equals("get"))
			response = res.when().get(resourceAPI.getResource());
		if(httpmethod.equals("put"))
			response = res.when().put(resourceAPI.getResource());
	}

	@Then("^API Call is success with status code is (\\d+)$")
	public void api_Call_is_success_with_status_code_is(int arg1){
		System.out.println("Verify the status code");
		
	    
	}

	@Then("^\"([^\"]*)\" in response is \"([^\"]*)\"$")
	public void in_response_is(String arg1, String arg2) {
	    // Write code here that turns the phrase above into concrete actions
		assertEquals(response.getStatusCode(), 200);
		System.out.println("The Status code is "+response.getStatusCode());
		String resp =response.asString();
		System.out.println(resp);
		System.out.println("The Scope is "+getJsonPath(response,"scope"));
		assertEquals(getJsonPath(response,"scope"), "APP");
		System.out.println("Verify the Response");
	    
	}
	
	
	@Then("verify place_id created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String expected_name , String resource) throws IOException {
	    // Write code here that turns the phrase above into concrete actions
		
		//get API Call
		place_id = getJsonPath(response,"place_id");
		System.out.println("The place id is "+ place_id);
		res = given().spec(RequestSpecification()).queryParams("place_id",place_id);
		user_calls_with_post_http_request(resource,"get");
		
		
		String name = getJsonPath(response,"name");
		System.out.println(name);
				
		String actaual_name = getJsonPath(response,"name");
		assertEquals(actaual_name,expected_name );
	}
	
	@Given("DeletePlace payload")
	public void delete_place_payload() throws IOException {
	    // Write code here that turns the phrase above into concrete actions
		
		res = given().spec(RequestSpecification()).body(tb.deleteplacePayload(place_id));
		System.out.println(place_id);
	}
	@When("user is calling  {string} with {string} http request")
	public void user_is_calling_with_http_request(String resource, String httpmethod) {
	    // Write code here that turns the phrase above into concrete actions
		user_calls_with_post_http_request(resource,httpmethod);

	}
	@Then("API is success with status code is {string}")
	public void api_is_success_with_status_code_is(String expected_status_code){
	    // Write code here that turns the phrase above into concrete actions
		int status_code = response.getStatusCode();
		System.out.println(status_code);
		System.out.println(response);
		int expected = Integer.parseInt(expected_status_code);
		assertEquals(status_code,expected);
	   
	}
	@Then("{string} in the  response is {string}")
	public void in_the_response_is(String string, String expected_scope) {
	    // Write code here that turns the phrase above into concrete actions
		
	   String  scope= getJsonPath(response,"scope");
	   assertEquals(scope,expected_scope);
	}

	
	
	
}