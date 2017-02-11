package com.approdo.census;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.DatePicker;
import android.widget.DatePicker.OnDateChangedListener;

//A DialogFragment is used to display a dialog window
//that floats over the current Activity

public class DateDialogFragment extends DialogFragment{
	
	// Key for the birthday that is passed between activities
	
	public static final String CONTACT_BIRTHDAY = "com.approdo.census.contact_birthday";
	
	private Date contactBirthday;

	// Called to set up the DialogFragment
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		
		// Get the birthdate that was passed to this Activity
		
		contactBirthday = (Date) getArguments().getSerializable(CONTACT_BIRTHDAY);
		
		// Used to work with the date
		
		Calendar calendar = Calendar.getInstance();
		
		calendar.setTime(contactBirthday);
		
		// Convert date to month, day and year
		
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		
		// Inflate the Dialog
		
		View theView = getActivity().getLayoutInflater()
				.inflate(R.layout.dialog_date, null);
		
		// Access the DatePicker in dialog_date.xml
		
		DatePicker datePicker = (DatePicker) theView.findViewById(R.id.birthday_picker);
		
		// If the birth date changes prepare to pass the new value
		
		datePicker.init(year, month, day, new OnDateChangedListener(){

			@Override
			public void onDateChanged(DatePicker theView, int year, int month,
					int day) {
				
				contactBirthday = new GregorianCalendar(year, month, day).getTime();   
				
				// Put the new value in the Bundle for passing
				// to other activities
				
				getArguments().putSerializable(CONTACT_BIRTHDAY, contactBirthday);
				
			}
			
		});
		
		// Set up the dialog box by setting the custom view to hold the 
		// contents of the dialog. Also set title, the type of button,
		// what to do when it is clicked and then the creation command
		
		return new AlertDialog.Builder(getActivity())
			.setView(theView)
			.setTitle(R.string.contact_birthday)
			.setPositiveButton(android.R.string.ok,
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							
							// Notify of a change to the birth date
							
							sendResult(Activity.RESULT_OK);
							
						}
				
			})
			.create();
	}
	
	public static DateDialogFragment newInstance(Date date){
		
		// Bundle holds the value for birth date
		
		Bundle dataPassed = new Bundle(); 
		
		// Put the key value pair in the Bundle
		
		dataPassed.putSerializable(CONTACT_BIRTHDAY, date);
		
		// Create the DateDialogFragment and attach the birth date
		
		DateDialogFragment dateFragment = new DateDialogFragment();
		
		dateFragment.setArguments(dataPassed);
		
		return dateFragment;
		
	}
	
	private void sendResult(int resultCode){
		
		// Check that the target Fragment was set up in ContactFragment
		
		if(getTargetFragment() == null) return;
		
		// If we have the target set up are intention to provide
		// it with the birthdate 
		
		Intent theIntent = new Intent();
		
		theIntent.putExtra(CONTACT_BIRTHDAY, contactBirthday);
		
		// Get the target and have it receive the data
		
		getTargetFragment()
			.onActivityResult(getTargetRequestCode(), resultCode, theIntent);
		
	}
	
}