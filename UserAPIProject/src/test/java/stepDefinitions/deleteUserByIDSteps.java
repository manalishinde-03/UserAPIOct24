package stepDefinitions;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.testng.Assert;

import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import request.DeleteUserRequest;
import request.GetUserRequest;
import utility.CommonUtils;

public class deleteUserByIDSteps {
	
	DeleteUserRequest deleteUserRequest = new DeleteUserRequest();
	private Response response;
	
	@Given("User sets a DELETE request with valid details")
	public void user_sets_a_delete_request_with_valid_details() {
	   
		RestAssured.baseURI = CommonUtils.BASE_URI;
	}

	@When("User sends DELETE request with userID and valid endpoint with {string}")
	public void user_sends_delete_request_with_and_valid_endpoint(String rowNum) {
		response = deleteUserRequest.deleteUserByIDReq(rowNum);
	}
	
	@Then("The status code should be {int} OK")
	public void user_deleted_status(int expStatusCode) {
		int actualStatus = response.getStatusCode();
		
		Assert.assertEquals(actualStatus, expStatusCode);
	}

	@When("User sends DELETE request with invalid ID and {string}")
	public void user_sends_delete_request_with_invalid_id_and(String rowNum) throws NumberFormatException, IOException {
		response = deleteUserRequest.deleteUserByInvalidIDReq(rowNum);
	}
	
	@When("User sends DELETE request for non existing ID and {string}")
	public void user_sends_delete_request_alreadyDeleted(String rowNum) throws NumberFormatException, IOException {
		response = deleteUserRequest.deleteUserByInvalidIDReq(rowNum);
	}
	
	@Then("The status code should be {int} Bad Request")
	public void user_Notdeleted_status(int expStatusCode) {
		int actualStatus = response.getStatusCode();
		
		Assert.assertEquals(actualStatus, expStatusCode);
	}
	
	@Then("The status code should be {int} Not Found")
	public void user_AlreadyDeleted_status(int expStatusCode) {
		int actualStatus = response.getStatusCode();
		
		Assert.assertEquals(actualStatus, expStatusCode);
	}

	@When("User sends DELETE request FirstName with {string} and valid endpoint")
	public void user_sends_delete_request_using_first_name_and_valid_endpoint(String rowNum) {
		response = deleteUserRequest.deleteUserByIDReq(rowNum);
	}
	
	@Then("User should get status code {int} OK")
	public void deleteReq_using_Fname(int expStatusCode) {
		int actualStatus = response.getStatusCode();
		
		Assert.assertEquals(actualStatus, expStatusCode);
	}
}
