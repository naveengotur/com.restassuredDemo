package restAssuredBasics;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class GetBookingID {

	@Test
	public void getBookingID() {

		Response response = RestAssured.given()
				// .contentType("application/json")
				.contentType(ContentType.JSON)
				.body("{\r\n" + "    \"firstname\" : \"Jim\",\r\n" + "    \"lastname\" : \"Brown\",\r\n"
						+ "    \"totalprice\" : 111,\r\n" + "    \"depositpaid\" : true,\r\n"
						+ "    \"bookingdates\" : {\r\n" + "        \"checkin\" : \"2018-01-01\",\r\n"
						+ "        \"checkout\" : \"2019-01-01\"\r\n" + "    },\r\n"
						+ "    \"additionalneeds\" : \"Breakfast\"\r\n" + "}")
				.post("https://restful-booker.herokuapp.com/booking");

		System.out.println(response.asPrettyString());
		System.out.println(response.statusCode());

		JsonPath json = response.jsonPath();
        System.out.println(json.getInt("bookingid"));
        //  or instead of writing above 2 lines below 1 line is equal
        System.out.println(response.jsonPath().getInt("bookingid"));
        
	}
}
