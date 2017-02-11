package com.approdo.census;

import java.util.ArrayList;
import java.util.UUID;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

public class ContactViewPager extends FragmentActivity{
	private ViewPager theViewPager;
	private ArrayList<Contact> contactList;
	
	
	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		
		theViewPager = new ViewPager(this);
		theViewPager.setId(R.id.viewPager);
		
		setContentView(theViewPager);
		contactList = AllContacts.get(this).getContactList();
		
		FragmentManager fragManager = getSupportFragmentManager();
		
		theViewPager.setAdapter(new FragmentStatePagerAdapter(fragManager) {
			
			@Override
			public int getCount() {
				return contactList.size();
			}
			
			@Override
			public Fragment getItem(int position) {
				Contact theContact = contactList.get(position);
				return ContactFragment.newContactFragment(theContact.getIdNumber());
			}
		});
		
		UUID contactId = (UUID) getIntent().getSerializableExtra(ContactFragment.CONTACT_ID);
		for(int i = 0; i < contactList.size(); i++){
			if(contactList.get(i).getIdNumber().equals(contactId)){
				theViewPager.setCurrentItem(i);
				break;
			}
		}
		
		theViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int position) {
				setTitle("Citizen #" + position);				
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	}
}