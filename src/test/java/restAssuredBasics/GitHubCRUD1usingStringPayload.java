package restAssuredBasics;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class GitHubCRUD1usingStringPayload 
{
	
	String gitRepoName;
	
	@Test
	public void smokeCheck() {
		Response resp = RestAssured.get("https://api.github.com/zen");

		System.out.println(resp.asPrettyString());

		System.out.println(resp.statusCode());

	}

	@Test 
	// creating the repository in github
	public void createRepo() {
		Response resp = RestAssured.given().log().all()
				.header("Authorization", "token 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")
				.body("{\"name\":\"AnishRepos\", \"private\":true}")
				.contentType(ContentType.JSON)
				.post("https://api.github.com/user/repos");

		System.out.println("Status code" + resp.statusCode());

		System.out.println(resp.asPrettyString());

	}
	@Test 
	//creating the repository in public
	public void createRepo2() {
		Response resp = RestAssured.given().log().all()
				.header("Authorization", "token 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")
				.body("{\"name\":\"AniRepos\", \"private\":false}")
				.contentType(ContentType.JSON)
				.post("https://api.github.com/user/repos");

		System.out.println("Status code" + resp.statusCode());

		System.out.println(resp.asPrettyString());

	}
	@Test 
	//updating the repository 
	public void updateRepo() {
		Response resp = RestAssured.given().log().all()
				.header("Authorization", "token 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")
				.body("{\"name\":\"LearnRepos\", \"private\":false}")
				.contentType(ContentType.JSON)
				//.patch("https://api.github.com/repos/naveeng/"+gitRepoName);
				.patch("https://api.github.com/repos/naveeng/LearnRepos");
		
		gitRepoName = resp.jsonPath().getString("name");
		
		Assert.assertTrue(resp.statusCode()==200);

		System.out.println("Status code" + resp.statusCode());

		System.out.println(resp.asPrettyString());

	}
	
}
