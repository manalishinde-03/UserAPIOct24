package request;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import utility.CommonUtils;
import utility.ExcelReader;

public class CreateUserRequest extends CommonUtils {
	
	private Map<String, Object> requestBody = new HashMap<>();
	private Response response;
	private static final Logger logger = LogManager.getLogger(CreateUserRequest.class);
	
	// ArrayList<Integer> userIDList = new ArrayList<>();

	public Response createUserReq(String testdata) throws FileNotFoundException, IOException {

		Map<String, String> dataMap = ExcelReader.getTestData(EXCEL_PATH, "Create", Integer.parseInt(testdata));
		Map<String, Object> userAddress = new HashMap<>();
		userAddress.put("plotNumber", dataMap.get("PlotNumber"));
		userAddress.put("street", dataMap.get("Street"));
		userAddress.put("state", dataMap.get("State"));
		userAddress.put("country", dataMap.get("Country"));
		userAddress.put("zipCode", dataMap.get("Zipcode"));

		requestBody.put("user_first_name", dataMap.get("Firstname"));
		requestBody.put("user_last_name", dataMap.get("Lastname"));
		requestBody.put("user_contact_number", dataMap.get("ContactNumber"));
		requestBody.put("user_email_id", dataMap.get("Email"));
		requestBody.put("userAddress", userAddress);

		ObjectMapper objectMapper = new ObjectMapper();
		String jsonBody = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(requestBody);

		//System.out.println("Request Body in JSON :" + jsonBody);

		// Send the POST request
		response = RestAssured.given()
				.auth().basic(USERNAME, PASSWORD)
				.header("Content-Type", "application/json")
				.body(jsonBody)
				.when()
				.post(createUserEndpoint);

		System.out.println("Response body - " + response.prettyPrint());
		System.out.println("Response Status Code:  - " + response.getStatusCode());
		 logger.info("Response Status Code: " + response.getStatusCode());
	      logger.info("Response Headers: " + response.getHeaders().toString());

	    //  String userID = response.jsonPath().getString("user_id");
	      int userID = response.path("user_id");
	      String userFirstName = response.jsonPath().getString("user_first_name");
	        System.out.println("Created user ID: " + userID);
	        System.out.println("Created user FirstName: " + userFirstName);
	        
	        
	        //userIDList.add(userID);
	        
	        CommonUtils.setUserFirstName(userFirstName);
	        
	        CommonUtils.setUserID(userID);
	        
	        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> ");
	        
		return response;

	}

}
