package jsonFiles;

import io.restassured.path.json.JsonPath;

public class Reusable {
	
	public static JsonPath jsonPathMethod(String json) {
		
		JsonPath path = new JsonPath(json);
		return path;
	}

}
