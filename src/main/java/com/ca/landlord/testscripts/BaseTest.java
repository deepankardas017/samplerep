package com.ca.landlord.testscripts;

import org.testng.annotations.BeforeClass;

import io.restassured.RestAssured;

public class BaseTest {

	@BeforeClass
	public void init(){
		RestAssured.baseURI = "http://localhost:8080";
	}
	
}
