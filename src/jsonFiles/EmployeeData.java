package jsonFiles;

public class EmployeeData {
	
	public static String createEmployee(String name, int salary, int age) {
		
		return "{\"name\":\""+name+"\",\"salary\":\""+salary+"\",\"age\":\""+age+"\"}";
	}
	

}
