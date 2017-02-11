package com.approdo.census;

import android.support.v4.app.Fragment;

public class ContactListActivity extends FragmentActivityBuilder{

	@Override
	protected Fragment createFragment() {
		// TODO Auto-generated method stub
		
		return new FragmentContactList();
	}
}