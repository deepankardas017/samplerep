package com.ca.landlord.dtos;

public class Landlords {
	
	private String firstName;
	private String lastName;
	private boolean trusted;
	
	public Landlords(){
		
	}
	
	public Landlords(String firstName, String lastName){
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public Landlords(String firstName, String lastName, boolean trusted){
		this.firstName = firstName;
		this.lastName = lastName;
		this.trusted = trusted;
	}
	
	public String getFirstName(){
		return firstName;
	}
	public void setFirstName(String firstName){
		this.firstName = firstName;
	}

	public String getLastName(){
		return lastName;
	}
	public void setLastName(String lastName){
		this.lastName = lastName;
	}

	public boolean isTrusted() {
		return trusted;
	}
	public void setTrusted(boolean trusted) {
		this.trusted = trusted;
	}
	

}
