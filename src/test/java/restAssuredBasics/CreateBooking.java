package restAssuredBasics;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import io.restassured.response.Response;

public class CreateBooking {

	@Test
	public void createBookingID() {
		
	Response response =	RestAssured.given()
			// .contentType("application/json")
		.contentType(ContentType.JSON)
		.body("{\r\n"
				+ "    \"firstname\" : \"Jim\",\r\n"
				+ "    \"lastname\" : \"Brown\",\r\n"
				+ "    \"totalprice\" : 111,\r\n"
				+ "    \"depositpaid\" : true,\r\n"
				+ "    \"bookingdates\" : {\r\n"
				+ "        \"checkin\" : \"2018-01-01\",\r\n"
				+ "        \"checkout\" : \"2019-01-01\"\r\n"
				+ "    },\r\n"
				+ "    \"additionalneeds\" : \"Breakfast\"\r\n"
				+ "}")
		.post("https://restful-booker.herokuapp.com/booking");
		
		System.out.println(response.asPrettyString());
		System.out.println(response.statusCode());
		
		
		
	}

}
