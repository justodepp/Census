package com.approdo.census;

//We will use the android.support.v4.app.Fragment
//support library so our app runs on older versions
//of Android

import java.util.UUID;

import android.support.v4.app.Fragment;

//Change Activity to FragmentActivity

// The FragmentManager ads Fragments to an Activity's view

public class CensusApp extends FragmentActivityBuilder {

	@Override
	protected Fragment createFragment() {
		// TODO Auto-generated method stub
		UUID contactIdNumber = (UUID) getIntent().getSerializableExtra(ContactFragment.CONTACT_ID);
		return new ContactFragment().newContactFragment(contactIdNumber);
	}	
}