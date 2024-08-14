package apiPractice;

import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import java.io.File;

import org.testng.Assert;

public class JiraAPI {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		RestAssured.baseURI = "http://localhost:8090";		
		SessionFilter session = new SessionFilter();
		
		//Login scenario
		String loginResponse = given().relaxedHTTPSValidation().header("Content-Type","application/json").
		body("{\"username\":\"gnithya98\",\"password\":\"Nithya@30\"}").filter(session).
		when().post("/rest/auth/1/session").
		then().assertThat().statusCode(200).extract().response().asString();
		
		String commandString = "Hi, How are you?";
		
		//Add command scenario
		String addCommandResponse = given().log().all().pathParam("key", "10001").
				header("Content-Type","application/json").
				body("{\"body\":\"" + commandString + "\",\"visibility\":{\"type\":\"role\",\"value\":\"Administrators\"}}").filter(session).
				when().post("/rest/api/2/issue/{key}/comment").
				then().assertThat().statusCode(201).extract().response().asPrettyString();
		
		JsonPath jPath = new JsonPath(addCommandResponse);
		String commandIdValue = jPath.getString("id");
		
		//Add attachment to the ticket scenario
		given().pathParam("key", "10001").header("X-Atlassian-Token","no-check").filter(session).
		header("Content-Type","multipart/form-data").multiPart("file", new File("C:\\Users\\Team EERO\\Documents\\Jira.txt")).
		when().post("/rest/api/2/issue/{key}/attachments").
		then().assertThat().statusCode(200);
		
		//Get the response of the ticket
		String getResponse = given().filter(session).pathParam("key", "10001").queryParam("fields", "comment").
		when().get("/rest/api/2/issue/{key}").
		then().extract().response().asString();
		
		System.out.println(getResponse);
		
		JsonPath jp = new JsonPath(getResponse);
		int commandsCount = jp.getInt("fields.comment.comments.size()");
		for(int i=0; i<commandsCount; i++) {
			
			String idString = jp.get("fields.comment.comments["+i+"].id");
			if(idString.equalsIgnoreCase(commandIdValue)) {
				
				String msgString = jp.get("fields.comment.comments["+i+"].body");
				Assert.assertEquals(msgString, commandString);
				System.out.println("Assertion completed");
			}
		}
		
		
		

	}

}
