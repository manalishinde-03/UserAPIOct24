package request;

import utility.CommonUtils;
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

public class GetUserRequest extends CommonUtils {

	private Map<String, Object> requestBody = new HashMap<>();
	private Response response;
	private static final Logger logger = LogManager.getLogger(GetUserRequest.class);

	public Response getUserByIDReq(String rowNo) throws FileNotFoundException, IOException {

//		Map<String, String> dataMap = ExcelReader.getTestData(EXCEL_PATH, "Get", Integer.parseInt(rowNo));
//		
//		 // String userId = testData.get("user_id");
//		
//		requestBody.put("user_id", dataMap.get("UserID"));
//		System.out.println("Request Body -" +requestBody.toString());
//		
//		String userId = String.valueOf(Math.round(Double.parseDouble((String) requestBody.get("user_id"))));

		int arrLstIndex = Integer.parseInt(rowNo) - 1; // 0,1
		ArrayList<Integer> userIDList = CommonUtils.getUserID();
		System.out.println(">>> ArrayList in Get " + userIDList);
		System.out.println("GET user with ID: " + userIDList.get(arrLstIndex));

		// Send the GET request
		response = RestAssured.given().auth().basic(USERNAME, PASSWORD).header("Content-Type", "application/json")
				.pathParam("id", userIDList.get(arrLstIndex)).when().get(getUserByIDEndpoint + "/{id}");

		System.out.println("Response body - " + response.prettyPrint());
		logger.info("Response Status Code: " + response.getStatusCode());
		logger.info("Response Headers: " + response.getHeaders().toString());

		if (response.getStatusCode() == 200) {

			System.out.println("GET By ID Request Successful for User: " + userIDList.get(arrLstIndex));
		}

		return response;

	}

	public Response getUserByInvalidIDReq(String testdata) throws FileNotFoundException, IOException {

		Map<String, String> dataMap = ExcelReader.getTestData(EXCEL_PATH, "Get", Integer.parseInt(testdata));

		requestBody.put("user_id", dataMap.get("UserID"));
		System.out.println("Request Body -" + requestBody.toString());

		// Send the GET request
		response = RestAssured.given().auth().basic(USERNAME, PASSWORD).header("Content-Type", "application/json")
				.pathParam("id", requestBody.get("user_id")).when().get(getUserByIDEndpoint + "/{id}");

		System.out.println("Response body - " + response.prettyPrint());
		System.out.println("Response Status Code:  - " + response.getStatusCode());
		// logger.info("Response Status Code: " + response.getStatusCode());
		logger.info("Response Headers: " + response.getHeaders().toString());

		return response;

	}

	public Response getAllUsersDetails() {

		response = RestAssured.given().auth().basic(USERNAME, PASSWORD).header("Content-Type", "application/json")
				.when().get(getAllUsersEndPoint);

		System.out.println("Response body - " + response.prettyPrint());
		System.out.println("Response Status Code:  - " + response.getStatusCode());
		// logger.info("Response Status Code: " + response.getStatusCode());
		logger.info("Response Headers: " + response.getHeaders().toString());

		return response;
	}

	public Response getUserByFirstName(String rowNo) throws IllegalArgumentException, IOException {

//		Map<String, String> dataMap = ExcelReader.getTestData(EXCEL_PATH, "Sheet2", Integer.parseInt(testdata));
//		
//		 // String userId = testData.get("user_id");
//		
//		requestBody.put("user_first_name", dataMap.get("FirstName"));
//		System.out.println("Request Body -" +requestBody.toString());

		int arrLstIndex = Integer.parseInt(rowNo) - 1; // 0,1
		ArrayList<String> userFnameList = CommonUtils.getUserFirstName();
		System.out.println(">>> ArrayList in GET " + userFnameList);
		System.out.println("Get user with Fname: " + userFnameList.get(arrLstIndex));

		// Send the GET request
		response = RestAssured.given().auth().basic(USERNAME, PASSWORD).header("Content-Type", "application/json")
				.pathParam("firstName", userFnameList.get(arrLstIndex)).when()
				.get(getUserByFirstNameEndPoint + "/{firstName}");

		System.out.println("Response body - " + response.prettyPrint());
		logger.info("Response Status Code: " + response.getStatusCode());
		logger.info("Response Headers: " + response.getHeaders().toString());

		if (response.getStatusCode() == 200) {

			System.out.println("GET By FName Request Successful for User: " + userFnameList.get(arrLstIndex));
		}

		return response;
	}

	public Response getUserByInvalidFirstName(String testdata) throws IllegalArgumentException, IOException {

		Map<String, String> dataMap = ExcelReader.getTestData(EXCEL_PATH, "Get", Integer.parseInt(testdata));

		requestBody.put("user_first_name", dataMap.get("FirstName"));
		System.out.println("Request Body -" + requestBody.toString());

		// Send the GET request
		response = RestAssured.given().auth().basic(USERNAME, PASSWORD).header("Content-Type", "application/json")
				.pathParam("firstName", requestBody.get("user_first_name")).when()
				.get(getUserByFirstNameEndPoint + "/{firstName}");

		System.out.println("Response body - " + response.prettyPrint());
		System.out.println("Response Status Code:  - " + response.getStatusCode());
		// logger.info("Response Status Code: " + response.getStatusCode());
		logger.info("Response Headers: " + response.getHeaders().toString());

		return response;
	}

}
