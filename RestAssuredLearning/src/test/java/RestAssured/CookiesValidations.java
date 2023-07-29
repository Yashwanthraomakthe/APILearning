package RestAssured;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

public class CookiesValidations {

	@Test(priority = 1)
	void testCookies() {
		given()

				.when().get("https://www.google.com/")

				.then().cookie("AEC", "Ad49MVHiSNy7GyhmyYwq-uVmy_gCHbejKkpurKmGQEd4Q6SSEkbulYI5ZkE").statusCode(200);
		// this will fail every time because cookies value will chane on each run

	}

	@Test(priority = 2)
	void getSpecificCookiValue() {

		Response response = given()

				.when().get("https://www.google.com/");
		String cookieValue = response.getCookie("AEC");
		System.out.println("Value of AEC cookie is " + cookieValue);

	}

	@Test(priority = 3)
	void getallCookiesInfo() {

		Response response = given()

				.when().get("https://www.google.com/");
		Map<String, String> cookieValues = response.getCookies();
		System.out.println("Value of all cookies info are as below");
		for (String k : cookieValues.keySet()) {
			String cookieValue = response.getCookie(k);
			System.out.println(k + "--------------" + cookieValue);
		}

	}
}
