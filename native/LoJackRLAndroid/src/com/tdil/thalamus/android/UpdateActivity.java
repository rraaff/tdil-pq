package com.tdil.thalamus.android;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import org.json.JSONObject;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.tdil.lojack.rl.R;
import com.tdil.thalamus.android.gui.BeanMappingFunction;
import com.tdil.thalamus.android.gui.BeanMappingListAdapter;
import com.tdil.thalamus.android.rest.client.HttpMethod;
import com.tdil.thalamus.android.rest.client.IRestClientObserver;
import com.tdil.thalamus.android.rest.client.RESTClientTask;
import com.tdil.thalamus.android.rest.client.RESTConstants;
import com.tdil.thalamus.android.rest.client.RestParams;
import com.tdil.thalamus.android.rest.model.AddressTypeBean;
import com.tdil.thalamus.android.rest.model.AddressTypeBeanCollection;
import com.tdil.thalamus.android.rest.model.DocumentTypeBean;
import com.tdil.thalamus.android.rest.model.DocumentTypeCollection;
import com.tdil.thalamus.android.rest.model.LoginResponse;
import com.tdil.thalamus.android.rest.model.PersonBean;
import com.tdil.thalamus.android.rest.model.StateBean;
import com.tdil.thalamus.android.rest.model.StateBeanCollection;
import com.tdil.thalamus.android.utils.Messages;

/**
 * Activity which displays a login screen to the user, offering registration as
 * well.
 */
public class UpdateActivity extends Activity implements IRestClientObserver {

	private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

	/*
	 * public static final String URL_WEBSITE = "http://www.lojack-app.com.ar/";
	 * public static final String URL_ANDROID_VERSION = URL_WEBSITE +
	 * "android_version.txt";
	 */

	/**
	 * The default email to populate the email field with.
	 */
	public static final String EXTRA_EMAIL = "com.example.android.authenticatordemo.extra.EMAIL";

	private RESTClientTask mAuthTask = null;
	// Values for email and password at the time of the login attempt.
	private String mDocType;
	private String mDocNumber;
	private String mPassword;

	private boolean documentTypesLoaded = false;
	private boolean statesLoaded = false;
	private boolean addressTypesLoaded = false;
	private PersonBean person;
	
	private int mYear;
    private int mMonth;
    private int mDay;
    static final int DATE_DIALOG_ID = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_update);
		
		birthDate = (TextView) findViewById(R.id.birthDate);
		birthDate.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				 showDialog(DATE_DIALOG_ID);
			}
		});

		final Spinner spinner = (Spinner) findViewById(R.id.documentType);
		spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				DocumentTypeBean item = (DocumentTypeBean) arg0
						.getItemAtPosition(arg2);
				UpdateActivity.this.mDocType = String.valueOf(item.getId());

			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
			}
		});
		// load document types
		new RESTClientTask(this, HttpMethod.GET, new IRestClientObserver() {
			@Override
			public void sucess(RESTClientTask task) {
				Gson gson = new Gson();
				DocumentTypeCollection col = gson.fromJson(task.getResult(),
						DocumentTypeCollection.class);
				BeanMappingListAdapter<DocumentTypeBean> adapter = new BeanMappingListAdapter<DocumentTypeBean>(
						UpdateActivity.this,
						android.R.layout.simple_spinner_item, col.getList(),
						new BeanMappingFunction<DocumentTypeBean>() {
							public String key(DocumentTypeBean t) {
								return String.valueOf(t.getId());
							};

							@Override
							public String value(DocumentTypeBean t) {
								return t.getName();
							}
						});
				spinner.setEnabled(false);
				spinner.setClickable(false);
				spinner.setAdapter(adapter);
				documentTypesLoaded = true;
				setPersonData();
			}

			@Override
			public void error(RESTClientTask task) {
				Messages.connectionErrorMessage(UpdateActivity.this);
			}
		}, RESTConstants.DOCUMENT_TYPES, null, null).execute((Void) null);

		states = (Spinner) findViewById(R.id.state);
		new RESTClientTask(this, HttpMethod.GET, new IRestClientObserver() {
			@Override
			public void sucess(RESTClientTask task) {
				Gson gson = new Gson();
				StateBeanCollection col = gson.fromJson(task.getResult(),
						StateBeanCollection.class);
				BeanMappingListAdapter<StateBean> adapter = new BeanMappingListAdapter<StateBean>(
						UpdateActivity.this,
						android.R.layout.simple_spinner_item,
						new ArrayList<StateBean>(col.getList()),
						new BeanMappingFunction<StateBean>() {
							public String key(StateBean t) {
								return String.valueOf(t.getId());
							};

							@Override
							public String value(StateBean t) {
								return t.getName();
							}
						});
				states.setAdapter(adapter);
				statesLoaded = true;
				setPersonData();
			}

			@Override
			public void error(RESTClientTask task) {
				Messages.connectionErrorMessage(UpdateActivity.this);
			}
		}, RESTConstants.STATES, null, null).execute((Void) null);

		addressTypes = (Spinner) findViewById(R.id.addressType);
		new RESTClientTask(this, HttpMethod.GET, new IRestClientObserver() {
			@Override
			public void sucess(RESTClientTask task) {
				Gson gson = new Gson();
				AddressTypeBeanCollection col = gson.fromJson(task.getResult(),
						AddressTypeBeanCollection.class);
				BeanMappingListAdapter<AddressTypeBean> adapter = new BeanMappingListAdapter<AddressTypeBean>(
						UpdateActivity.this,
						android.R.layout.simple_spinner_item,
						new ArrayList<AddressTypeBean>(col.getList()),
						new BeanMappingFunction<AddressTypeBean>() {
							public String key(AddressTypeBean t) {
								return String.valueOf(t.getName());
							};

							@Override
							public String value(AddressTypeBean t) {
								return t.getDescription();
							}
						});
				addressTypes.setAdapter(adapter);
				addressTypesLoaded = true;
				setPersonData();
			}

			@Override
			public void error(RESTClientTask task) {
				Messages.connectionErrorMessage(UpdateActivity.this);
			}
		}, RESTConstants.ADDRESS_TYPES, null, null).execute((Void) null);

		// load personData
		new RESTClientTask(this, HttpMethod.GET, new IRestClientObserver() {
			@Override
			public void sucess(RESTClientTask task) {
				Gson gson = new Gson();
				PersonBean person = gson.fromJson(task.getResult(),
						PersonBean.class);
				UpdateActivity.this.person = person;
				setPersonData();
			}

			@Override
			public void error(RESTClientTask task) {
				Messages.connectionErrorMessage(UpdateActivity.this);
			}
		}, RESTConstants.GET_USER, null, null).execute((Void) null);

		findViewById(R.id.updateButton).setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						updatePerson();
					}
				});
	}

	protected void updatePerson() {
		PersonBean personBean = new PersonBean();
		personBean.setFirstName(firstName.getText().toString());
		personBean.setLastName(lastName.getText().toString());
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, mYear);
		cal.set(Calendar.MONTH, mMonth);
		cal.set(Calendar.DATE, mDay);
		personBean.setBirthDate(SIMPLE_DATE_FORMAT.format(cal.getTime()));
		if (male.isChecked()) {
			personBean.setGender("Male");
		} else {
			personBean.setGender("Female");
		}
		personBean.setPhoneAreaCode(areaCode.getText().toString());
		personBean.setPhoneNumber(mobile.getText().toString());
		personBean.setCountryId(1);
		personBean.setStateId(((StateBean)state.getSelectedItem()).getId());
		personBean.setStreet1(street1.getText().toString());
		personBean.setStreet2(street2.getText().toString());
		personBean.setCity(city.getText().toString());
		personBean.setPostalCode(postalCode.getText().toString());
		personBean.setOptIn(optIn.isChecked());
		
		Gson gson = new Gson();
		String json = gson.toJson(personBean);
		new RESTClientTask(this, HttpMethod.POST, new IRestClientObserver() {
			@Override
			public void sucess(RESTClientTask task) {
				Gson gson = new Gson();
				// TODO analizar la respuesta para mostrar un mensaje u otro
				new AlertDialog.Builder(UpdateActivity.this)
	               .setIcon(R.drawable.ic_launcher)
	               .setTitle("Modificacion de datos")
	               .setMessage("Se han modificado los datos")
	               .setPositiveButton("OK", new DialogInterface.OnClickListener() {
	                       public void onClick(DialogInterface dialog, int whichButton) {
	                    	   UpdateActivity.this.finish();
	                       }
	               }).show();
			}
			@Override
			public void error(RESTClientTask task) {
				Messages.connectionErrorMessage(UpdateActivity.this);
			}
		}, RESTConstants.SAVE_USER, new RestParams(), json).execute((Void) null);
	}

	private void setPersonData() {
		if (person != null) {
			if (documentTypesLoaded && statesLoaded && addressTypesLoaded) {
				final Spinner documentType = (Spinner) findViewById(R.id.documentType);
				BeanMappingListAdapter<DocumentTypeBean> doc = (BeanMappingListAdapter<DocumentTypeBean>)documentType.getAdapter();
				documentType.setSelection(doc.getPosition(person.getDocumentType()));
						
				final TextView document = (TextView) findViewById(R.id.documentNumber);
				document.setText(person.getDocument());
				
				firstName = (TextView) findViewById(R.id.firstName);
				firstName.setText(person.getFirstName());
				
				lastName = (TextView) findViewById(R.id.lastName);
				lastName.setText(person.getLastName());
				
				male = (RadioButton) findViewById(R.id.chkMale);
				female = (RadioButton) findViewById(R.id.chkFemale);
				if (person.getGender().equalsIgnoreCase("Male")) {
					male.setChecked(true);
					female.setChecked(false);
				} else {
					male.setChecked(false);
					female.setChecked(true);
				}
				email = (TextView) findViewById(R.id.email);
				email.setText(person.getEmail());
				try {
					birthDate2 = (TextView)findViewById(R.id.birthDate);
					Calendar cal=Calendar.getInstance();
					System.out.println(person.getBirthDate());
					cal.setTime(SIMPLE_DATE_FORMAT.parse(person.getBirthDate()));
					mYear=cal.get(Calendar.YEAR);
					mMonth=cal.get(Calendar.MONTH);
					mDay=cal.get(Calendar.DAY_OF_MONTH);
					birthDate2.setText(person.getBirthDate());
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				areaCode = (TextView) findViewById(R.id.areaCode);
				areaCode.setText(person.getPhoneAreaCode());
				
				mobile = (TextView) findViewById(R.id.mobile);
				mobile.setText(person.getPhoneNumber());
				
				state = (Spinner) findViewById(R.id.state);
				BeanMappingListAdapter<StateBean> stateList = (BeanMappingListAdapter<StateBean>)state.getAdapter();
				state.setSelection(stateList.getPosition(person.getStateId()));
				
				city = (TextView) findViewById(R.id.city);
				city.setText(person.getCity());
				
				street1 = (TextView) findViewById(R.id.street1);
				street1.setText(person.getStreet1());
				
				street2 = (TextView) findViewById(R.id.street2);
				street2.setText(person.getStreet2());
				
				addressType = (Spinner) findViewById(R.id.addressType);
				BeanMappingListAdapter<AddressTypeBean> addressTypeList = (BeanMappingListAdapter<AddressTypeBean>)addressType.getAdapter();
				addressType.setSelection(addressTypeList.getPosition(person.getAddressType()));
				
				postalCode = (TextView) findViewById(R.id.postalCode);
				postalCode.setText(person.getPostalCode());
				
				optIn = (CheckBox) findViewById(R.id.optIn);
				optIn.setChecked(person.isOptIn());
				
			}
		}
	}
	
	@Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
 
        case DATE_DIALOG_ID:
            return new DatePickerDialog(this,
                mDateSetListener,
                mYear, mMonth, mDay);
        }
        return null;
    }
	
	private DatePickerDialog.OnDateSetListener mDateSetListener =
	        new DatePickerDialog.OnDateSetListener() {
	 
	        public void onDateSet(DatePicker view, int year, int monthOfYear,
	                int dayOfMonth) {
	            mYear = year;
	            mMonth = monthOfYear;
	            mDay = dayOfMonth;
	            TextView birthDate = (TextView)findViewById(R.id.birthDate);
	            Calendar cal = Calendar.getInstance();
	            cal.set(Calendar.YEAR, year);
	            cal.set(Calendar.MONTH, mMonth);
	            cal.set(Calendar.DATE, mDay);
	            birthDate.setText(SIMPLE_DATE_FORMAT.format(cal.getTime()));
	        }
	    };

	private TextView birthDate;

	private Spinner states;

	private Spinner addressTypes;

	private TextView firstName;

	private TextView lastName;

	private RadioButton male;

	private RadioButton female;

	private TextView email;

	private TextView birthDate2;

	private TextView areaCode;

	private TextView mobile;

	private Spinner state;

	private TextView street1;

	private TextView street2;

	private Spinner addressType;

	private TextView postalCode;

	private CheckBox optIn;

	private TextView city;
    protected void onPrepareDialog(int id, Dialog dialog) {
        switch (id) {
 
        case DATE_DIALOG_ID:
            ((DatePickerDialog) dialog).updateDate(mYear, mMonth, mDay);
            break;
        }
    }   

	public void register() {
		if (mAuthTask != null) {
			return;
		}

		// Store values at the time of the login attempt.
		mDocNumber = ((EditText) findViewById(R.id.documentNumber)).getText()
				.toString();
		mPassword = ((EditText) findViewById(R.id.password)).getText()
				.toString();

		boolean cancel = false;
		View focusView = null;
		showProgress(true);
		mAuthTask = new RESTClientTask(this, HttpMethod.GET, this,
				RESTConstants.LOGIN, new RestParams(
						RESTConstants.P_DOCUMENT_TYPE, mDocType).put(
						RESTConstants.P_DOCUMENT_NUMBER, mDocNumber).put(
						RESTConstants.P_PASSWORD, mPassword), null);
		mAuthTask.execute((Void) null);
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		// TODO Auto-generated method stub
		super.onConfigurationChanged(newConfig);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		getMenuInflater().inflate(R.menu.activity_login, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle item selection
		switch (item.getItemId()) {
		case R.id.menu_go_home:
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	@Override
	public void error(RESTClientTask task) {
		Messages.connectionErrorMessage(UpdateActivity.this);
		this.mAuthTask = null;
	}

	@Override
	public void sucess(RESTClientTask task) {
		Gson gson = new Gson();
		LoginResponse resp = gson.fromJson(task.getResult(),
				LoginResponse.class);
		if (resp.getLogged()) {
			Intent intent = new Intent(this, HomeActivity.class);
			startActivity(intent);
			finish();
		} else {
			System.out.println("not logged");
		}
		this.mAuthTask = null;
	}

	/**
	 * Shows the progress UI and hides the login form.
	 */
	@TargetApi(Build.VERSION_CODES.ECLAIR_MR1)
	private void showProgress(final boolean show) {
		// On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
		// for very easy animations. If available, use these APIs to fade-in
		// the progress spinner.

	}

}
