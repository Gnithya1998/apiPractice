package apiPractice;
 
import org.testng.annotations.Test;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import static io.restassured.RestAssured.*;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import jsonFiles.EmployeeData;
import pojo.Employee;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EmployeeAPI {
	
	@Test
	public void EmployeeAPITest() {
		
		RequestSpecification request = new RequestSpecBuilder().setBaseUri("https://dummy.restapiexample.com/api/v1")
				.addHeader("Content-Type","application/json").build();
		
		ResponseSpecification response = new ResponseSpecBuilder().expectStatusCode(200).build();
		
		//Employee data = new Employee();
		
		//post
		Employee empData = given().spec(request).log().all()
				.header("Content-Type","application/json")
				.body(EmployeeData.createEmployee("Nisha", 50452,23))
				.post("/create")
				.as(Employee.class);
		
		String empID =  empData.getData().getId();	
		//get
		given().spec(request).log().all()
		.pathParam("id", empID)
		.header("Content-Type","application/json")
		.get("/employee/{empID}")
		.then().log().all()
		.spec(response);
		
		//put
		
		//get
		
		//delete
		
		System.out.println(empID);
		
	}

}
