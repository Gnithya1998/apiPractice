package apiPractice;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import pojo.GetCourses;
import pojo.WebAutomation;

import static io.restassured.RestAssured.*;

import java.util.List;

public class OAuth_ClientCredential {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		
		String response = given().formParam("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com").
		formParam("client_secret", "erZOWM9g3UtwNRj340YYaK_W").formParam("grant_type", "client_credentials").
		formParam("scope", "trust").when().post("/oauthapi/oauth2/resourceOwner/token").
		then().extract().response().asString();
		
		JsonPath path = new JsonPath(response);
		String token = path.get("access_token");
		
		GetCourses gc = given().queryParam("access_token", token).when().get("/oauthapi/getCourseDetails").as(GetCourses.class);
		
		System.out.println(gc.getInstructor());
		System.out.println(gc.getExpertise());
		
		List<WebAutomation> web = gc.getCourses().getWebAutomation();
		for(int i=0; i<web.size(); i++) {
			
			System.out.println(web.get(i).getCourseTitle());
		}
		
		

	}

}
