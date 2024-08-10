package restAssuredBasics;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class GetInformation {

	
	 @Test
		 public void getInfo()
	 {
		 
		 Response resp = RestAssured.get("https://my-json-server.typicode.com/typicode/demo/posts");
		 
		 System.out.println(resp.asPrettyString());
		 
		 System.out.println(resp.contentType());
		 
		 System.out.println(resp.getStatusCode());
		 
		 System.out.println(resp.getTime());
		 
		 System.out.println(resp.statusLine());
		 
		/* resp.jsonPath();
		 
		 RestAssured.post();
		 
		 RestAssured.delete();
		 
		 RestAssured.put();
		 
		 RestAssured.patch();
		 */

		 
	 }
	
	 
	
}
