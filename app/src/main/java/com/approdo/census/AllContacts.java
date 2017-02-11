 package com.approdo.census;

import java.util.ArrayList;
import java.util.UUID;

import android.content.Context;

public class AllContacts {
	
	private static AllContacts allContacts;
	private Context applicationContext;
	private ArrayList<Contact> contactList;

	private AllContacts(Context applicationContext) {
		// TODO Auto-generated constructor stub
		this.applicationContext = applicationContext;
		contactList = new ArrayList<Contact>();
		
		Contact paulSmith = new Contact();
		paulSmith.setName("Paul Smith");
		paulSmith.setStreetAddress("123 Main St");
		paulSmith.setContacted(true);
		contactList.add(paulSmith);
		
		Contact sallySmith = new Contact();
		sallySmith.setName("Sally Smith");
		sallySmith.setStreetAddress("125 Main St");
		sallySmith.setContacted(false);
		contactList.add(sallySmith);
		
		Contact markSmith = new Contact();
		markSmith.setName("Mark Smith");
		markSmith.setStreetAddress("127 Main St");
		markSmith.setContacted(false);
		contactList.add(markSmith);
	}
	
	public static AllContacts get(Context context){
		
		if(allContacts == null){
			allContacts = new AllContacts(context.getApplicationContext());
		}
		return allContacts;
	}
	
	public ArrayList<Contact> getContactList(){
		return contactList;
	}
	
	public Contact getContact(UUID id){
		for(Contact theContact : contactList){
			if(theContact.getIdNumber().equals(id)){
				return theContact;
			}
		}
		return null;
	}

}
