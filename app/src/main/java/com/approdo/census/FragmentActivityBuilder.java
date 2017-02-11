package com.approdo.census;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

public abstract class FragmentActivityBuilder extends FragmentActivity{
	
	protected abstract Fragment createFragment();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_census_app);
		
		
		FragmentManager fragManager = getSupportFragmentManager();
		
		// Check if the FragmentManager knows about the Fragment 
		// id we refer to
		
		Fragment theFragment = fragManager.findFragmentById(R.id.fragmentContainer);
		
		// Check if the Fragment was found
		
		if(theFragment == null){
			
			// If the Fragment wasn't found then we must create it
			
			theFragment = createFragment();
			
			// Creates and commits the Fragment transaction
			// Fragment transactions add, attach, detach, replace
			// and remove Fragments.
			
			// add() gets the location to place the Fragment into and
			// the Fragment itself.
			
			fragManager.beginTransaction()
				.add(R.id.fragmentContainer, theFragment)
				.commit();		
		}
	}
}