package apiPractice;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import jsonFiles.AddData;
import jsonFiles.Reusable;

import static io.restassured.RestAssured.*;

public class AddBookLibrary {
	
	@Test(dataProvider = "idData")
	public void testAddBook(String isbnString, String aisleString) {
		
		RestAssured.baseURI = "http://216.10.245.166";
		
		String responseString = given().header("Content-Type","application/json").
		body(AddData.addBook(isbnString,aisleString)).
		when().
		post("/Library/Addbook.php"). 
		then().log().all(). 
		assertThat(). 
		statusCode(200).
		extract().response().asString();
		
		JsonPath path = Reusable.jsonPathMethod(responseString);
		String idString = path.get("ID");
		System.out.println(idString);
		
		given().header("Content-Type","application/json").
		body(AddData.deleteBook(idString)).
		when().
		delete("/Library/DeleteBook.php").
		then().log().all().
		assertThat().
		statusCode(200);
	}
	
	@DataProvider(name = "idData")
	public Object[][] getData() {
		
		return new Object[][] {{"asdf","9999"},{"zxcv","8888"},{"mnoe","7777"}};
	}
	

}
