package waysToCreatePostRequestBody;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

public class waystoCreateRequestBody {

	// @Test()
	void testpostusingHashmap() {
		HashMap data = new HashMap();
		data.put("name", "scot");
		data.put("location", "hyd");
		data.put("phone", "8897053277");

		String[] courses = { "c", "c++" };
		data.put("courses", courses);

		System.out.println(data.toString());

		given().contentType("application/json").body(data)

				.when().post("http://localhost:3000/students")

				.then().statusCode(201).log().all().body("location", equalTo("hyd")).body("courses[0]", equalTo("c"))
				.body("courses[1]", equalTo("c++"));

	}

	// @Test()
	void testpostusingJsonLibrary() {
		JSONObject data = new JSONObject();
		data.put("name", "scot");
		data.put("location", "mumbai");
		data.put("phone", "8897053277");

		String[] courses = { "core", "java" };
		data.put("courses", courses);

		given().contentType("application/json").body(data.toString())

				.when().post("http://localhost:3000/students")

				.then().statusCode(201).log().all().body("location", equalTo("mumbai"));
	}

	// @Test()
	void testpostusingPojoObject() {

		PojoClass data = new PojoClass();

		data.setName("dinky");
		String courses[] = { "Selenium", "java" };
		data.setCources(courses);
		data.setLocation("Pune");
		data.setPhone("9014154738");

		given().contentType("application/json").body(data)

				.when().post("http://localhost:3000/students")

				.then().statusCode(201).log().all().body("location", equalTo("Pune"));
	}

	@Test()
	void testpostusingexternalJsonFile() throws FileNotFoundException {

		File f = new File(".\\data.json");
		FileReader rd = new FileReader(f);
		JSONTokener jt = new JSONTokener(rd);
		JSONObject data = new JSONObject(jt);		

		given().contentType("application/json").body(data.toString())

				.when().post("http://localhost:3000/students")

				.then().statusCode(201).log().all().body("location", equalTo("US"));
	}
}
