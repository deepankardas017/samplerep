package com.ca.landlord.testscripts;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import org.testng.Assert;
//import static org.hamcrest.MatcherAssert.assertThat;
//import static org.hamcrest.Matchers.*;
import org.testng.annotations.Test;

import com.ca.landlord.dtos.LandLordDTO;
import com.ca.landlord.util.FilesUtility;

import io.restassured.http.ContentType;

import io.restassured.response.Response;

public class TestLandLoard {

	@Test(priority=0)
	public void testGetLandLords() {
		try{
			Response  response = given().contentType (ContentType.JSON).
					when().
					get("http://localhost:8080/landlords/1MySAfU0");
			System.out.println(response.getStatusCode());
			System.out.println(response.asString());
			Assert.assertEquals(response.getStatusCode(), 200);
		}catch(Exception | AssertionError exception){
			exception.printStackTrace();
			throw exception;
		}
		
		
	}
	@Test(priority=1)
	public void testCreateLandLoard(){
		try{
			LandLordDTO landLordDTO = new LandLordDTO();
			landLordDTO.setFirstName("deepankar");
			landLordDTO.setLastName("das");
			landLordDTO.setTrusted(true);
			Response  response = given().
					contentType(ContentType.JSON).
					body(landLordDTO).
					when().
					post("http://localhost:8080/landlords");
			System.out.println(response.getStatusCode());
			System.out.println(response.asString());
			String name = response.path("firstName").toString();
			Assert.assertEquals(response.path("firstName").toString(), "deepankar");
		}catch(Exception | AssertionError exception){
			exception.printStackTrace();
			throw exception;
		}


	}
	@Test(priority=2)
	public void testUpdateLandLord(){
		//Assert.assertEquals(false, true);
		//Posts post = new Posts();
		Response response = given().contentType(ContentType.JSON).
				body(FilesUtility.fileContentToString("D:/muna/Java/LandLord-API/src/main/resources/payloads/updateLandLords.json")).
				when().put("http://localhost:8080/landlords/1MySAfU0");
		System.out.println(response.getStatusCode());
		System.out.println(response.asString());
	}

	/*@Test(priority=3)
	public void testDeleteLandLord(){
		Assert.assertEquals(false, true);
		Response response = given().contentType (ContentType.JSON).when ().delete ("http://localhost:8080/landlords/RyRPK4aQ");
		System.out.println(response.getStatusCode());
		System.out.println(response.asString());

	}*/
}
