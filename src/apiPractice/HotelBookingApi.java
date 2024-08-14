package apiPractice;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestLogSpecification;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import jsonFiles.AddData;
import jsonFiles.AddHotelData;
import pojo.HotelBookingCreation;

public class HotelBookingApi {
	
	@Test
	public void bookingAPI() {
		
		//RestAssured.baseURI = "https://restful-booker.herokuapp.com";
		AddHotelData data = new AddHotelData();
		
		RequestSpecification request = new RequestSpecBuilder().setBaseUri("https://restful-booker.herokuapp.com")
				.build();
		
		ResponseSpecification response = new ResponseSpecBuilder().expectStatusCode(200).build();
		
		//Obtaining the authentication token
		String responseString = given().spec(request).header("Content-Type","application/json").body(AddData.bookingAuth())
		.when().post("/auth")
		.then().log().all()
		.spec(response).extract().response().asString();
		
		JsonPath path = new JsonPath(responseString);
		String tokenString = path.getString("token");
		System.out.println("token: " + tokenString);
		
		
		//Creating hotelBooking
		String bookingResponse = given().log().all().spec(request).header("Content-Type","application/json").body(data.bookingData("Nikki", "kalrani", "2023-01-05", "2023-01-10", "Dinner"))
		.when().post("/booking")
		.then().log().all().spec(response).extract().response().asString();
		
		JsonPath path2 = new JsonPath(bookingResponse);
		int bookingId = path2.getInt("bookingid");
		System.out.println("BookingID: " + bookingId);
		
		//Retrieving booking Data
		HotelBookingCreation creation = given().log().all().spec(request).pathParam("Id", bookingId)
		.when().get("/booking/{Id}").as(HotelBookingCreation.class);
		
		String checkInString = creation.getBookingdates().getCheckin();
		String checkOutString = creation.getBookingdates().getCheckout();
		System.out.println("check In: " + checkInString);
		System.out.println("check Out: " + checkOutString);
		
		//Delete booking
		given().log().all().spec(request).pathParam("Id", bookingId)
		.header("Content-Type","application/json").header("Cookie","token=" + tokenString)
		.when().delete("/booking/{Id}")
		.then().assertThat().statusCode(201);
		
		
	}

}
