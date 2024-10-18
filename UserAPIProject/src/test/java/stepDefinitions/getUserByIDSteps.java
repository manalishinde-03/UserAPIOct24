package stepDefinitions;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.testng.Assert;

import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import request.GetUserRequest;
import utility.CommonUtils;

public class getUserByIDSteps {
	
	GetUserRequest getUserRequest = new GetUserRequest();
	private Response response;
	
	@Given("User creates GET request with valid user ID")
	public void user_creates_get_request_with_valid_user_id() {
		RestAssured.baseURI = CommonUtils.BASE_URI;
	}

	@When("User sends GET request with {string} and valid endpoint")
	public void user_sends_get_request_and_valid_endpoint(String testdata) throws FileNotFoundException, IOException {
	    
		response = getUserRequest.getUserByIDReq(testdata);
	}

	@Then("The response status code should be {int} OK")
	public void the_response_status_code_should_be_ok(int expectedStatusCode) {
		response.getStatusCode();
		Assert.assertEquals(response.getStatusCode(),expectedStatusCode );
	}
	
	@Given("User creates GET request with invalid user ID")
	public void user_creates_get_request_with_invalid_user_id() {
		RestAssured.baseURI = CommonUtils.BASE_URI;
	}
	@When("User sends GET request with invalid user ID and {string}")
	public void user_sends_get_request_with_and_valid_endpoint(String testdata) throws FileNotFoundException, IOException {
	    
		response = getUserRequest.getUserByInvalidIDReq(testdata);
	}
	
	@Then("The response status code should be {int} Bad Request")
	public void the_response_status_code_should_be_BadReq(int expectedStatusCode) {
		response.getStatusCode();
		Assert.assertEquals(response.getStatusCode(),expectedStatusCode );
	}

	@Then("Validate the JSON Schema for retrived user details")
	public void validate_json_schema_for_the_retrived_user_details() {
		response.then()
    	.assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("Schema/getUserByFirstNameSchema.json"));
	}
	
	@Given("User creates GET request for all users")
	public void user_creates_get_request_for_all_users() {
		RestAssured.baseURI = CommonUtils.BASE_URI;
	}

	@When("User sends GET request to get all user details with valid endpoint")
	public void user_sends_get_request_to_get_all_user_details_with_valid_endpoint() {
	    
		response = getUserRequest.getAllUsersDetails();
	}

	@Given("User creates GET request with valid First Name")
	public void user_creates_get_request_with_valid_first_name() {
		RestAssured.baseURI = CommonUtils.BASE_URI;
	}

	@When("User sends GET request to fetch user with {string}")
	public void user_sends_get_request_to_fetch_user_with(String testdata) throws IllegalArgumentException, IOException {
	    
		response = getUserRequest.getUserByFirstName(testdata);
	}

	@Given("User creates GET request with invalid First Name")
	public void user_creates_get_request_with_invalid_first_name() {
		RestAssured.baseURI = CommonUtils.BASE_URI;
	}

	@When("User sends GET request with {string} and invalid FirstName and")
	public void user_sends_get_request_with_and_invalid_first_name_and(String testdata) throws IllegalArgumentException, IOException {
		response = getUserRequest.getUserByInvalidFirstName(testdata);
	}

	@Then("The response status code should be {int} Not Found")
	public void the_response_status_code_should_be_not_found(int expectedStatusCode) {
	   
		response.getStatusCode();
		Assert.assertEquals(response.getStatusCode(),expectedStatusCode);
	}
}
