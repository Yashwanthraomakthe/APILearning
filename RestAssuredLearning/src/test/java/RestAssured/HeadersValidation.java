package RestAssured;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.Test;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class HeadersValidation {

	@Test(priority = 1)
	void testheaders() {
		given()
			.when()
				.get("https://www.google.com/")
			.then()
				.header("Content-Type", "text/html; charset=ISO-8859-1")
				.header("Server", "gws")
				.statusCode(200);
				//.log().all()
				//.log().body()
				//.log().headers();
				//.log().cookies();

	}

	@Test(priority = 2)
	void getheaders() {
		Response response = given().when().get("https://www.google.com/");

		Headers myheaders = response.getHeaders();
		for (Header h : myheaders) {
			System.out.println(h.getName() + "-----------" + h.getValue());
		}

	}

}
