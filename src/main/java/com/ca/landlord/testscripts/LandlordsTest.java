package com.ca.landlord.testscripts;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;  //1

import com.ca.landlord.dtos.Landlords;

import io.restassured.RestAssured;	//1
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;	
import static org.hamcrest.Matchers.*;	

public class LandlordsTest extends BaseTest{
	
	
	@Test(priority=0)
	public void getLandlords(){
		
		when()
			.get("/landlords")
		.then()
			.statusCode(200)
			.body("", is(empty()));
		
	}
	
	@DataProvider(name = "validLandlords")
	public Object[][] createData(){
		return new Object[][]{
			{new Landlords("deep","das")},
			{new Landlords("deepankar","das",true)}
		};	
	}
	
	@Test(priority=1, dataProvider = "validLandlords", groups = "critical")
	public void postLandlords01(Landlords landlords){
		
		String id = given()
			.contentType(ContentType.JSON)
			.body(landlords)
		.when()
			.post("/landlords")
		.then()
			.statusCode(201)
			.body("firstName", is(landlords.getFirstName()))
			.body("lastName", is(landlords.getLastName()))
			.body("trusted", is(landlords.isTrusted()))
			.body("apartments", is(empty()))
		.extract()
			.path("id");
			
		given()
			.pathParam("id",id)
		.when()
			.get("/landlords/{id}")
		.then()
			.statusCode(200)
			.body("id", is(id))
			.body("firstName", is(landlords.getFirstName()))
			.body("lastName", is(landlords.getLastName()))
			.body("trusted", is(landlords.isTrusted()))
			.body("apartments", is(empty()));
				
	}
	
	@Test(priority=1)
	public void postLandlords02(){
		
		Landlords landlords = new Landlords("deep","das",true);
		String id = given()
			.contentType(ContentType.JSON)
			.body(landlords)
		.when()
			.post("/landlords")
		.then()
			.statusCode(201)
			.body("firstName", is(landlords.getFirstName()))
			.body("lastName", is(landlords.getLastName()))
			.body("trusted", is(true))
			.body("apartments", is(empty()))
		.extract()
			.path("id");
			
		given()
			.pathParam("id",id)
		.when()
			.get("/landlords/{id}")
		.then()
			.statusCode(200)
			.body("id", is(id))
			.body("firstName", is(landlords.getFirstName()))
			.body("lastName", is(landlords.getLastName()))
			.body("trusted", is(true))
			.body("apartments", is(empty()));
				
	}
	
/*	@Test(priority=1)
	public void postLandlordsNegative01(){
		
		Landlords landlords = new Landlords("","");
		given()
			.contentType(ContentType.JSON)
			.body(landlords)
		.when()
			.post("/landlords")
		.then()
			.statusCode(400)
			.body("message", is("Fields are with validation errors"))
			.body("fieldErrorDTOs[0].fieldName", is("firstName"))
			.body("fieldErrorDTOs[0].fieldError", is("First name can not be empty"))
			.body("fieldErrorDTOs[1].fieldName", is("lastName"))
			.body("fieldErrorDTOs[1].fieldError", is("Last name can not be empty"));
				
	}*/
	
	@Test(priority=2)
	public void putLandlords(){
		
		Landlords landlords = new Landlords("deep","das",true);
		
		String id = given()
			.contentType(ContentType.JSON)
			.body(landlords)
		.when()
			.post("/landlords")
		.then()
			.statusCode(201)
		.extract()
			.path("id");
		
		Landlords landlordsUpdate = new Landlords("deepankar", "das");
		given()
			.contentType(ContentType.JSON)
			.body(landlordsUpdate)
			.pathParam("id", id)
		.when()
			.put("/landlords/{id}")
		.then()
			.statusCode(200)
			.body("message", is("LandLord with id: "+id+" successfully updated"));
		
		given()
			.pathParam("id", id)
		.when()
			.get("/landlords/{id}")
		.then()
			.statusCode(200)
			.body("firstName", is(landlordsUpdate.getFirstName()))
			.body("lastName", is(landlordsUpdate.getLastName()))
			.body("trusted", is(false));
				
	}
	
	@Test(priority=3)
	public void deieteLandlords(){
		
		Landlords landlords = new Landlords("deep","das");
		String id = given()
			.contentType(ContentType.JSON)
			.body(landlords)
		.when()
			.post("/landlords")
		.then()
			.statusCode(201)
		.extract()
			.path("id");
		
		given()
			.pathParam("id", id)
		.when()
			.delete("/landlords/{id}")
		.then()
			.statusCode(200)
			.body("message", is("LandLord with id: "+id+" successfully deleted"));
		
		given()
			.pathParam("id", id)
		.when()
			.get("/landlords/{id}")
		.then()
			.statusCode(404)
			.body("message", is("There is no LandLord with id: "+id));
			
		
	}

}
