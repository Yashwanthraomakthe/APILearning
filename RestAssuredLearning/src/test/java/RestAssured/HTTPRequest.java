package RestAssured;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.HashMap;

import org.testng.annotations.Test;

public class HTTPRequest {

	int id;

	@Test(priority =1)
	void getUser() {
		given()

				.when().get("https://reqres.in/api/users?page=2")

				.then()

				.statusCode(200).body("page", equalTo(2)).log().all();

	}

	@Test(priority =2)
	void createUser() {

		HashMap data = new HashMap();
		data.put("name", "Yash");
		data.put("job", "Tester");

		id = given().contentType("application/json").body(data)

				.when().post("https://reqres.in/api/users").jsonPath().getInt("id");

		System.out.println("Generated id is " + id);

	}

	@Test(priority =3 ,dependsOnMethods="createUser()")
	void updateUser() {

		HashMap data = new HashMap();
		data.put("name", "rao");
		data.put("job", "Dev");

		given()
		.contentType("application/json")
		.body(data)

		.when()
		.put("https://reqres.in/api/users/"+ id)
		
		
		.then()
		.statusCode(200);

	}
	
	@Test(priority =4)
	void deleteUser() {
		
		given()

		.when()
		.delete("https://reqres.in/api/users/" + id)

		.then()
		.statusCode(204);
				

	}
}