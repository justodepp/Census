package com.approdo.census;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import android.util.Log;

public class Contact {
	
	private String name, phoneNumber, streetAddress, city;
	
	private UUID idNumber;
	private boolean contacted = false;
	
	private Date dateOfBirth;
	
	public Contact(){
		idNumber = UUID.randomUUID();
		dateOfBirth = new Date();
	}

	public String getStreetAddress() {
		return streetAddress;
	}

	public void setStreetAddress(String streetAddress) {		
		Log.e("CENSUS", "ADDRESS CHANGED TO " + streetAddress);
		this.streetAddress = streetAddress;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		Log.e("CENSUS", "CITY CHANGED TO " + city);
		this.city = city;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	public void setPhoneNumber(String phoneNumber) {
		Log.e("CENSUS", "PHONE CHANGED TO " + phoneNumber);
		this.phoneNumber = phoneNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		Log.e("CENSUS", "ADDRESS CHANGED TO " + name);
		this.name = name;
	}

	public boolean getContacted() {
		return contacted;
	}

	public void setContacted(boolean contacted) {
		Log.e("CENSUS", "CONTACTED CHANGED TO " + contacted);
		this.contacted = contacted;
	}

	public UUID getIdNumber() {
		return idNumber;
	}
	
	public void setIdNumber(UUID idNumber) {
		this.idNumber = idNumber;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	
	public String getDateString(){
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dateOfBirth);
		
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH) + 1;
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		
		return day+"/"+month+"/"+year;
	}
}