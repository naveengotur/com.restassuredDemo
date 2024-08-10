package restAssuredBasics;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class CreateUpdateDeleteBookingWithDifferentTest {

	String tokenValue;

	String bookingID;

	@BeforeClass
	public void createToken() {

		// Token Generation
		Response tokenResponse = RestAssured.given().log().all().contentType(ContentType.JSON)
				.body("{\r\n" + "    \"username\" : \"admin\",\r\n" + "    \"password\" : \"password123\"\r\n" + "}")
				.post("https://restful-booker.herokuapp.com/auth");

		tokenValue = tokenResponse.jsonPath().getString("token");

		System.out.println("Token Generated " + tokenValue);

	}

	@Test(priority = 2)
	public void updateBooking() {

		// Passing the Token and Update the Booking using the BookingID
		Response response = RestAssured.given().log().all().contentType(ContentType.JSON)
				.header("Cookie", "token=" + tokenValue)
				.body("{\r\n" + "    \"firstname\" : \"Srinivas\",\r\n" + "    \"lastname\" : \"nishanth\"\r\n" + "}")
				.patch("https://restful-booker.herokuapp.com/booking/" + bookingID);

		Assert.assertTrue(response.statusCode() == 200);

		// Assert.assertEquals(response.statusCode(), 200);

	}

	@Test(priority = 3)
	public void deleteBooking() {

		// delete booking
		Response response = RestAssured.given().log().all().contentType(ContentType.JSON)
				.header("Cookie", "token=" + tokenValue)
				.delete("https://restful-booker.herokuapp.com/booking/" + bookingID);

		System.out.println(response.asPrettyString());

		// 201
		Assert.assertTrue(response.statusCode() == 201);

		// Assert.assertEquals(response.statusCode(), 201);

	}

	@Test(priority = 1)
	public void createBooking() {
		// Create Booking ID
		Response response = RestAssured.given().log().all()
				// .contentType("application/json")
				.contentType(ContentType.JSON)
				.body("{\r\n" + "    \"firstname\" : \"Jim\",\r\n" + "    \"lastname\" : \"Brown\",\r\n"
						+ "    \"totalprice\" : 111,\r\n" + "    \"depositpaid\" : true,\r\n"
						+ "    \"bookingdates\" : {\r\n" + "        \"checkin\" : \"2018-01-01\",\r\n"
						+ "        \"checkout\" : \"2019-01-01\"\r\n" + "    },\r\n"
						+ "    \"additionalneeds\" : \"Breakfast\"\r\n" + "}")
				.post("https://restful-booker.herokuapp.com/booking");

		bookingID = response.jsonPath().get("bookingid").toString();

		Assert.assertTrue(response.statusCode() == 200);

		// Assert.assertEquals(response.statusCode(), 200);

	}
}
