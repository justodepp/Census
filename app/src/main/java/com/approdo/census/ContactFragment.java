package com.approdo.census;

import java.util.Date;
import java.util.UUID;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;

public class ContactFragment extends Fragment {
	
	public static final String CONTACT_ID = "com.approdo.census.contact_id";
	
	private Contact contact;
	private EditText contactNameEditText;
	
	private EditText contactStreetEditText;
	private EditText contactCityEditText;
	private EditText contactPhoneEditText;
	
	private CheckBox contactedCheckBox;
	
	private static final String DATE_OF_BIRTH = "Date of Birth";
	private EditText contactBirthdayEditText;
	private static final int REQUEST_DATE = 0;
	
	public static ContactFragment newContactFragment(UUID contactId){
		Bundle passedData = new Bundle();
		passedData.putSerializable(CONTACT_ID, contactId);
		ContactFragment contactFragment = new ContactFragment();
		contactFragment.setArguments(passedData);
		return contactFragment;
	}

	// Generate this with Right Click - Source - Override/Implement methods
	// This method is called when the Fragment is called for.
	// We initialize everything here.
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		UUID contactId = (UUID) getArguments().getSerializable(CONTACT_ID);
		
		contact = AllContacts.get(getActivity()).getContact(contactId);
	}

	// Used to inflate the Fragment, or show it on the screen
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		// Pass in the layout to inflate, the views parent and whether
		// to add the inflated view to the parent.
		// We mark this false because the Activity will add the view.
				
		View theView = inflater.inflate(R.layout.fragment_contact, container, false);
		
		// Get a reference to the EditText
		
		contactNameEditText = (EditText) theView.findViewById(R.id.contactNameEditText);
		
		// If text in the EditText box is edited it will change the
		// name.
		contactStreetEditText = (EditText) theView.findViewById(R.id.contactStreetEditText);
		contactCityEditText = (EditText) theView.findViewById(R.id.contactCityEditText);
		contactPhoneEditText = (EditText) theView.findViewById(R.id.contactPhoneEditText);
		
		
		TextWatcher editTextWatcher = new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				if(contactNameEditText.hasFocus() == true){
					contact.setName(s.toString());
				} else if(contactStreetEditText.hasFocus() == true){
					contact.setStreetAddress(s.toString());
				} else if(contactCityEditText.hasFocus() == true){
					contact.setCity(s.toString());
				} else if(contactPhoneEditText.hasFocus() == true){
					contact.setPhoneNumber(s.toString());
				}
				

			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				
			}
		};
		
		contactStreetEditText.addTextChangedListener(editTextWatcher);
		contactCityEditText.addTextChangedListener(editTextWatcher);
		contactPhoneEditText.addTextChangedListener(editTextWatcher);
		
		contactNameEditText.addTextChangedListener(editTextWatcher);
		
		contactedCheckBox = (CheckBox) theView.findViewById(R.id.contactedCheckBox);
		contactedCheckBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				contact.setContacted(isChecked);
			}
		});
		
		contactBirthdayEditText = (EditText) theView.findViewById(R.id.contactBirthdayEditText);
		contactBirthdayEditText.setText(contact.getDateString());
		contactBirthdayEditText.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				FragmentManager fragManager = getActivity().getSupportFragmentManager();
				DateDialogFragment dateDialog = DateDialogFragment.newInstance(contact.getDateOfBirth());
				
				dateDialog.setTargetFragment(ContactFragment.this, REQUEST_DATE);
				dateDialog.show(fragManager, DATE_OF_BIRTH);
			}
		});
		
		// Get the values for the current Contact and put them in
		// the right Components
				
		contactNameEditText.setText(contact.getName());
		contactStreetEditText.setText(contact.getStreetAddress());;
		contactCityEditText.setText(contact.getCity());
		contactPhoneEditText.setText(contact.getPhoneNumber());
		contactedCheckBox.setChecked(contact.getContacted());
		
		// Pass in the layout to inflate, the views parent and whether
		// to add the inflated view to the parent.
		// We mark this false because the Activity will add the view.
		
		return theView;
		
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		if(resultCode != Activity.RESULT_OK) return;
		
		if(requestCode == REQUEST_DATE){
			Date birthDate = (Date) data.getSerializableExtra(DateDialogFragment.CONTACT_BIRTHDAY);
			contact.setDateOfBirth(birthDate);
			contactBirthdayEditText.setText(contact.getDateString());
		}
	}
}