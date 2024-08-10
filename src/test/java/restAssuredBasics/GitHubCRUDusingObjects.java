package restAssuredBasics;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class GitHubCRUDusingObjects 
{
	
	String gitRepoName;
	
	@Test
	public void getInfromation()
	{
		System.out.println(RestAssured.get("https://github.com/users/naveengotur").asPrettyString());
	}
	
	@Test (enabled=false)
	public void smokeCheck()
	{
		Response resp = RestAssured.get("https://api.github.com/zen");

		System.out.println(resp.asPrettyString());

		System.out.println(resp.statusCode());

	}

	@Test (enabled=false)
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
	
	@Test (enabled=false)
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
	
	@Test (enabled=false)
	//updating the repository 
	public void updateRepo() 
	{
		Response resp = RestAssured.given()
				.log()
				.all()
				.header("Authorization", "token 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")
				.body("{\"name\":\"LearnRepos\", \"private\":false}")
				.contentType(ContentType.JSON)
				//.patch("https://api.github.com/repos/naveengotur/"+gitRepoName);
				.patch("https://api.github.com/repos/naveengotur/AniRepos");
		
		gitRepoName = resp.jsonPath().getString("name"); 
		
		Assert.assertTrue(resp.statusCode()==200);

		System.out.println("Status code" + resp.statusCode());

		System.out.println(resp.asPrettyString());

	}
	
	@Test (enabled=false)
	//deleting the repository -> 404 response [miskake done in not existing repository name]
	public void deleteRepo() 
	{
		Response resp = RestAssured.given()
				.log()
				.all()
				.header("Authorization", "token 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")
				.contentType(ContentType.JSON)
			    .delete("https://api.github.com/repos/naveengotur/AnishaRepos");
		
		System.out.println(resp.statusCode());
		Assert.assertTrue(resp.statusCode()==404);

	}
	
	@Test (enabled=false)
	//deleting the repository -> 401 response  [miskake done in token]
	public void deleteRepo2() 
	{
		Response resp = RestAssured.given()
				.log()
				.all()
				.header("Authorization", "token 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1")
				.contentType(ContentType.JSON)
			    .delete("https://api.github.com/repos/naveengotur/AnishRepos");
		
		System.out.println(resp.statusCode());
		Assert.assertTrue(resp.statusCode()==401);

	}
	
	@Test (enabled=false)
	//deleting the repository -> 204 response
	public void deleteRepo3() 
	{
		Response resp = RestAssured.given()
				.log()
				.all()
				.header("Authorization", "token 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")
				.contentType(ContentType.JSON)
			    .delete("https://api.github.com/repos/naveengotur/AnishRepos");
		
		System.out.println(resp.statusCode());
		Assert.assertTrue(resp.statusCode()==204);

	}
}
