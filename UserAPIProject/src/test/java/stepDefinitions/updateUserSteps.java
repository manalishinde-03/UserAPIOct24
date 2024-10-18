package stepDefinitions;

import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import request.CreateUserRequest;
import request.UpdateUserRequest;
import utility.CommonUtils;

import org.testng.Assert;

public class updateUserSteps {
	
    private Response response;
    UpdateUserRequest updateUserRequest = new UpdateUserRequest();

	
    @Given("User creates PUT request with request body having new valid email ID")
    public void userSetsThePostRequest() {
        RestAssured.baseURI = CommonUtils.BASE_URI;
    }

    @When("User sends PUT request with {string} and valid endpoint")
    public void userSendsThePostRequestWith(String testdata) throws Exception {
       
    	response = updateUserRequest.updateUserReq(testdata);
    }

    @Then("User should receive status code {int} OK")
    public void userShouldReceiveAValidResponse(int expectedStatusCode) {
        response.getStatusCode();
		Assert.assertEquals(response.getStatusCode(),expectedStatusCode );
    }
    
    @When("User sends PUT request with No Authorization and {string}")
    public void userSendsThePutReqWithoutAuth(String testdata) throws Exception {
       
    	response = updateUserRequest.updateUserReqNoAuth(testdata);
    }

    @Then("User should receive status code {int} Unauthorized")
    public void userShouldReceiveUnauthorized(int expectedStatusCode) {
        response.getStatusCode();
		Assert.assertEquals(response.getStatusCode(),expectedStatusCode );
    }
    
    @Then("Validate JSON Schema for the updated user")
    public void jsonSchemaValidationForUserCreated(){
    	response.then()
    	.assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath("Schema/updateUserSchema.json"));
    	
    }
}

