package restAssuredBasics;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class CreateUpdateDeleteBooking {

	String tokenValue;
	
	@Test
	public void updateBookingID() 
	{

		// Token Generation
		Response tokenResponse =RestAssured
		.given()
		.log()
		.all()
		.contentType(ContentType.JSON)
		.body("{\r\n"
				+ "    \"username\" : \"admin\",\r\n"
				+ "    \"password\" : \"password123\"\r\n"
				+ "}")
		.post("https://restful-booker.herokuapp.com/auth");
		
		tokenValue = tokenResponse.jsonPath().getString("token");
		
		System.out.println("Token Generated "+tokenValue);
		
		
		// Create Booking ID
		Response response = RestAssured
				.given()
				.log()
				.all()
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
        
        //Passing the Token and  Update the Booking using the BookingID
        Response response2 = RestAssured
        .given()
        .log()
        .all()
        .contentType(ContentType.JSON)
        .header("Cookie", "token="+tokenValue)
        .body("{\r\n"
        		+ "    \"firstname\" : \"Srinivas\",\r\n"
        		+ "    \"lastname\" : \"nishanth\"\r\n"
        		+ "}")
        .patch("https://restful-booker.herokuapp.com/booking/"+response.jsonPath().getInt("bookingid")); 
        
        System.out.println(response2.asPrettyString());
		System.out.println(response2.statusCode());


		//delete booking
		Response response3 = RestAssured
		.given()
		.log()
		.all()
		.contentType(ContentType.JSON)
		.header("Cookie", "token="+tokenValue)
		.delete("https://restful-booker.herokuapp.com/booking/"+response.jsonPath().get("bookingid"));
        
		 System.out.println(response3.asPrettyString());
			System.out.println(response3.statusCode());

		}
}
