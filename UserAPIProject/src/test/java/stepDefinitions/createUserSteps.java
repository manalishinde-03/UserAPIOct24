package stepDefinitions;

import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import request.CreateUserRequest;
import utility.CommonUtils;

import java.io.IOException;

import org.testng.Assert;

public class createUserSteps {
	
    private Response response;
    CreateUserRequest createUserRequest = new CreateUserRequest();

	
    @Given("User creates a POST request with valid details")
    public void userSetsThePostRequest() {
        RestAssured.baseURI = CommonUtils.BASE_URI;
    }

    @When("User sends POST request with {string} and valid endpoint")
    public void userSendsThePostRequestWith(String testdata) throws Exception {
       
    	response = createUserRequest.createUserReq(testdata);
    }
    
    @When("User sends POST request having mandatory details with {string}")
    public void userSendsThePostRequestWithMandatory(String testdata) throws Exception {
       
    	response = createUserRequest.createUserMandatoryDetailsReq(testdata);
    }

    @Then("The response status code should be {int} Created")
    public void userShouldReceiveAValidResponse(int expectedStatusCode) {
        response.getStatusCode();
		Assert.assertEquals(response.getStatusCode(),expectedStatusCode );
    }
    
    @When("User sends POST request with invalid details and {string}")
    public void userSendsThePostRequestWithInvalid(String rowNum) throws Exception {
       
    	response = createUserRequest.createUserReq(rowNum);
    }

    @Then("For invalid details response status code should be {int} Bad Request")
    public void userShouldReceiveBadReq(int expectedStatusCode) {
        response.getStatusCode();
		Assert.assertEquals(response.getStatusCode(),expectedStatusCode );
    }
    
    
    @When("User sends POST request with invalid endpoint and {string}")
    public void userSendsThePostReqInvalidEndpoint(String rowNum) throws NumberFormatException, IOException{
       
    	response = createUserRequest.createUserReqInvalidEndpoint(rowNum);
    }

    @Then("For invalid endpoint response status code should be {int} Not Found")
    public void userShouldReceiveNotFound(int expectedStatusCode) {
        response.getStatusCode();
		Assert.assertEquals(response.getStatusCode(),expectedStatusCode );
    }
    
    @Then("Validate JSON Schema for the user created")
    public void jsonSchemaValidationForUserCreated(){
    	response.then()
    	.assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("Schema/createUserSchema.json"));
    	
    }
}

