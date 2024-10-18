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

public class UpdateUserRequest extends CommonUtils {
	
	private Map<String, Object> requestBody = new HashMap<>();
	private Response response;
	private static final Logger logger = LogManager.getLogger(UpdateUserRequest.class);

	public Response updateUserReq(String rowNo) throws FileNotFoundException, IOException {

		Map<String, String> dataMap = ExcelReader.getTestData(EXCEL_PATH, "Update", Integer.parseInt(rowNo));
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
		int arrLstIndex = Integer.parseInt(rowNo)-1; //0,1
		ArrayList<Integer> userIDList = CommonUtils.getUserID();
		 System.out.println(">>> ArrayList in Update " + userIDList);
        System.out.println("Updating user with ID: " + userIDList.get(arrLstIndex));

		response = RestAssured.given()
				.auth().basic(USERNAME, PASSWORD)
				.header("Content-Type", "application/json")
				.pathParam("id",userIDList.get(arrLstIndex))
				.body(jsonBody)
				.when()
				.put(updateUserEndpoint +"/{id}");

		
		System.out.println("Response body - " + response.prettyPrint());
		System.out.println("Response Status Code:  - " + response.getStatusCode());
		 logger.info("Response Status Code: " + response.getStatusCode());
	      logger.info("Response Headers: " + response.getHeaders().toString());

	      System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> ");
		return response;

	}

	public Response updateUserReqNoAuth(String testdata) throws NumberFormatException, IOException {

		Map<String, String> dataMap = ExcelReader.getTestData(EXCEL_PATH, "Update", Integer.parseInt(testdata));
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

		response = RestAssured.given()
				
				.header("Content-Type", "application/json")
				.pathParam("id",1)
				.body(jsonBody)
				.when()
				.put(updateUserEndpoint +"/{id}");

		
		System.out.println("Response body - " + response.prettyPrint());
		System.out.println("Response Status Code:  - " + response.getStatusCode());
		
		return response;
	}

}
