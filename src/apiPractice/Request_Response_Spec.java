package apiPractice;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.Location;
import pojo.Place;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;

public class Request_Response_Spec {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				.addQueryParam("key", "qaclick123").addHeader("Content-Type", "application/json").build();

		ResponseSpecification res = new ResponseSpecBuilder().expectStatusCode(200).build();

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

		RequestSpecification requestSpec = given().spec(req).body(place);
		Response response = requestSpec.when().post("/maps/api/place/add/json").then().spec(res).extract().response();

		System.out.println(response.asPrettyString());

	}

}
