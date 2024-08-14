package automationExerciseAPI;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import org.testng.annotations.Test;

public class GetAllProducts {
	
	@Test
	public void allProducts() {
		
		RestAssured.baseURI = "https://automationexercise.com";
		
		String response = given().
		when().get("/api/productsList").
		then().assertThat().statusCode(200).extract().response().asString();
		
		JsonPath path = new JsonPath(response);
		
		int ProdSize = path.getInt("products.size()");		
		for(int i=0; i<ProdSize; i++) {
			
			System.out.println(path.get("products["+i+"].name").toString());
		}
	}

}
