package apiPractice;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import pojo.Location;
import pojo.Place;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;

public class Serialization {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		
		Place place = new Place();
		place.setAccuracy(50);
		place.setName("Bashaa Bhai");
		place.setPhone_number("(+91)7397341648");
		place.setAddress("25,iitmadras,chennai36");
		place.setWebsite("http://rahulshettyacademy.com");
		place.setLanguage("English-EN");
		
		List<String> values = new ArrayList<>();
		values.add("doggoodies");
		values.add("shop");
		
		Location location = new Location();
		location.setLat(-38.383494);
		location.setLng(33.427362);
		
		place.setTypes(values);
		place.setLocation(location);
		
		Response response = given().queryParam("key", "qaclick123").
		header("Content-Type","application/json").body(place).
		post("/maps/api/place/add/json").
		then().assertThat().statusCode(200).extract().response();
		
		String responseString = response.asPrettyString();
		System.out.println(responseString);

	}

}
