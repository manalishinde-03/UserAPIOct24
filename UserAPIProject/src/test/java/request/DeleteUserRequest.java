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

public class DeleteUserRequest extends CommonUtils {
	
	private Map<String, Object> requestBody = new HashMap<>();
	private Response response;
	private static final Logger logger = LogManager.getLogger(DeleteUserRequest.class);


	public Response deleteUserByIDReq(String rowNo) {

		int arrLstIndex = Integer.parseInt(rowNo)-1; //0,1
		ArrayList<Integer> userIDList = CommonUtils.getUserID();
		 System.out.println(">>> ArrayList in Delete " + userIDList);
        System.out.println("Deleting user with ID: " + userIDList.get(arrLstIndex));
		
		response = RestAssured.given()
				.auth().basic(USERNAME, PASSWORD)
				.header("Content-Type", "application/json")
				.pathParam("id",userIDList.get(arrLstIndex))
				.when()
				.delete(deleteUserByIDEndPoint +"/{id}");

		System.out.println("Response body - " + response.prettyPrint());
		logger.info("Response Status Code: " + response.getStatusCode());
	      logger.info("Response Headers: " + response.getHeaders().toString());
	      
	      if(response.getStatusCode()==200) {
	    	  
	    	  System.out.println("User " +userIDList.get(arrLstIndex)+" deleted successfully!!");
	      }
		
		return response;
	}
	
	public Response deleteUserByInvalidIDReq(String rowNo) throws NumberFormatException, IOException {
		
		Map<String, String> dataMap = ExcelReader.getTestData(EXCEL_PATH, "Delete", Integer.parseInt(rowNo));

		requestBody.put("user_id", dataMap.get("InvalidUserID"));
		System.out.println("Request Body -" +requestBody.toString());
        System.out.println("Deleting user with ID: " + requestBody.get("user_id"));
		
		response = RestAssured.given()
				.auth().basic(USERNAME, PASSWORD)
				.header("Content-Type", "application/json")
				.pathParam("id",requestBody.get("user_id"))
				.when()
				.delete(deleteUserByIDEndPoint +"/{id}");

		System.out.println("Response body - " + response.prettyPrint());
		logger.info("Response Status Code: " + response.getStatusCode());
	      logger.info("Response Headers: " + response.getHeaders().toString());
	      
		
		return response;
	}
	
	public Response deleteUserByFnameReq(String rowNo) {

		int arrLstIndex = Integer.parseInt(rowNo)-1; //0,1
		ArrayList<String> userFnameList = CommonUtils.getUserFirstName();
		 System.out.println(">>> ArrayList in Delete " + userFnameList);
        System.out.println("Deleting user with Fname: " + userFnameList.get(arrLstIndex));
		
		response = RestAssured.given()
				.auth().basic(USERNAME, PASSWORD)
				.header("Content-Type", "application/json")
				.pathParam("fname",userFnameList.get(arrLstIndex))
				.when()
				.delete(deleteUserByFNameEndPoint +"/{fname}");

		System.out.println("Response body - " + response.prettyPrint());
		logger.info("Response Status Code: " + response.getStatusCode());
	      logger.info("Response Headers: " + response.getHeaders().toString());
	      
	     	    	  
	    	  System.out.println("User " +userFnameList.get(arrLstIndex)+" deleted successfully!!");
		
		return response;
	}
	
}
