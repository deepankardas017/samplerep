
import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;

import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class SampleTest {
	
	@Test
	public void getActivities(){
		Response r = when()
			.get("https://fakerestapi.azurewebsites.net/api/Activities");
		System.out.println(r.getStatusCode());
		System.out.println(r.body().asString());
		
	}
	
	@Test
	public void postActivites(){
		Activities activities = new Activities(31, "activity31", "2018-01-24", true);
		Response r = given()
					.contentType(ContentType.JSON)
					.body(activities).
				
				when()
				.post("https://fakerestapi.azurewebsites.net/api/Activities");
			System.out.println(r.getStatusCode());
			System.out.println(r.body().asString());
			System.out.println(r.path("ID").toString());
			System.out.println(r.path("Title").toString());
	}
	
	@Test
	
	public void updateActivities(){
		Activities activities32 = new Activities(32,"activity32");
		Response r = given()
				     .contentType(ContentType.JSON)
				     .body(activities32).
				     when().put("https://fakerestapi.azurewebsites.net/api/Activities/1");
		System.out.println(r.getStatusCode());
		System.out.println(r.body().asString());
		System.out.println(r.path("ID").toString());
		System.out.println(r.path("Title").toString());
	}

}
