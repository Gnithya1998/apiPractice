package apiPractice;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import jsonFiles.AddData;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.testng.Assert;

public class StaticJsonFile {
	
	
	//Adding json file to the body
	//Change the content of the file to String--> first change to byte then to string
	//

	public static void main(String[] args) throws IOException {

		RestAssured.baseURI = "https://rahulshettyacademy.com";
		String response = given().log().all().queryParam("key", "qaclick123").header("Content-type", "application/json")
				.body(new String(Files.readAllBytes(Paths.get("C:\\Users\\Team EERO\\Documents\\addPlace.json"))))
				.when().post("/maps/api/place/add/json")
				.then().log().all().assertThat()
				.statusCode(200).body("scope", equalTo("APP")).header("Server", "Apache/2.4.52 (Ubuntu)").extract()
				.response().asString();
		
		System.out.println("Response: " + response );
		JsonPath js = new JsonPath(response);
		String idString = js.getString("place_id");
		
		given().log().all().queryParam("key", "qaclick123").body("{  \"place_id\":\""+idString+"\",\n\"address\":\"70 Summer walk, USA\",\n    \"key\":\"qaclick123\"\n}")
		.when().put("/maps/api/place/update/json")
		.then().log().all().assertThat().statusCode(200).body("msg", equalTo("Address successfully updated"));
		
		String reString = given().log().all().queryParam("key", "qaclick123").queryParam("place_id", idString)
				.when().get("/maps/api/place/get/json")
				.then().log().all().assertThat().statusCode(200).extract().response().asString();
		
		JsonPath path = new JsonPath(reString);
		Assert.assertEquals(path.getString("address"), "70 Summer walk, USA");

	}

}
