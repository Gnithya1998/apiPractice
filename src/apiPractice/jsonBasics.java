package apiPractice;

import org.testng.Assert;

import io.restassured.path.json.JsonPath;
import jsonFiles.AddData;

public class jsonBasics {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		JsonPath jsonPath = new JsonPath(AddData.data());

		//To obtain the size of the array
		int count = jsonPath.getInt("courses.size()");
		System.out.println("Count of number of courses: " + count);

		//To obtain the value of element which is in nested json
		int totalAmount = jsonPath.getInt("dashboard.purchaseAmount");
		System.out.println("Total amount of the purchase: " + totalAmount);

		//To obtain the value of the first array element
		String firstCourseName = jsonPath.get("courses[0].title");
		System.out.println("First Title course: " + firstCourseName);

		for (int i = 0; i < count; i++) {

			String titleOfCouses = jsonPath.get("courses[" + i + "].title");
			String priceOfCouses = jsonPath.get("courses[" + i + "].price");

			System.out.println("Title: " + titleOfCouses.toString());
			System.out.println("Price: " + priceOfCouses.toString());
		}

		//Looping through all the elements of the array element
		for (int i = 0; i < count; i++) {

			String titleOfCouses = jsonPath.get("courses[" + i + "].title");

			if (titleOfCouses.equalsIgnoreCase("RPA")) {

				System.out.println("Title: " + titleOfCouses.toString());
				break;
			}

		}
		
		int sum = 0;
		
		//Looping through all the elements of the array
		for (int i = 0; i < count; i++) {

			int copiesOfCouses = Integer.valueOf(jsonPath.get("courses[" + i + "].copies"));
			int priceOfCouses = Integer.valueOf(jsonPath.get("courses[" + i + "].price"));
			
			int multiply = copiesOfCouses * priceOfCouses;
			sum += multiply;
		}
		
		Assert.assertEquals(sum, totalAmount);
		

	}

}
