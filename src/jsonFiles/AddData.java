package jsonFiles;

public class AddData {

	public static String addPlace() {
		
		return "{\"location\":{\"lat\":-38.383494,\"lng\":33.427362},"
				+ "\"accuracy\":50,\"name\":\"BashaaBhai\",\"phone_number\":\"(+91)7397341648\","
				+ "\"address\":\"25,iitmadras,chennai36\",\"types\":[\"doggoodies\",\"shop\"],"
				+ "\"website\":\"http://rahulshettyacademy.com\",\"language\":\"English-EN\"}";
	}
	
	public static String data() {
		
		return "{ \r\n"
				+ "  \"dashboard\":{\r\n"
				+ "    \"purchaseAmount\":\"1162\",\r\n"
				+ "    \"website\":\"rahulshettyacademy.com\"},\r\n"
				+ "  \"courses\":[\r\n"
				+ "      {\r\n"
				+ "        \"title\":\"Selenium java\",\r\n"
				+ "        \"price\":\"50\",\r\n"
				+ "        \"copies\":\"6\"\r\n"
				+ "      },\r\n"
				+ "      {\r\n"
				+ "        \"title\":\"Cypress\",\r\n"
				+ "        \"price\":\"40\",\r\n"
				+ "        \"copies\":\"4\"\r\n"
				+ "      },\r\n"
				+ "      {\r\n"
				+ "        \"title\":\"RPA\",\r\n"
				+ "        \"price\":\"45\",\r\n"
				+ "        \"copies\":\"10\"\r\n"
				+ "      },\r\n"
				+ "      {\r\n"
				+ "        \"title\":\"Appium\",\r\n"
				+ "        \"price\":\"36\",\r\n"
				+ "        \"copies\":\"7\"\r\n"
				+ "      }\r\n"
				+ "      \r\n"
				+ "    ]\r\n"
				+ "}";
	}
	
	public static String addBook(String isbn, String aisle) {
		
		return "{\"name\":\"LearnSeleniumwithJava\",\"isbn\":\"" + isbn +"\",\"aisle\":\"" + aisle + "\",\"author\":\"Johnfoe\"}";
	}
	
	public static String deleteBook(String id) {
		
		return "{\"ID\":\"" + id + "\"}";
	}
	
	public static String bookingAuth() {
		
		
		return "{\"username\":\"admin\",\"password\":\"password123\"}";
		
		
	}

}
