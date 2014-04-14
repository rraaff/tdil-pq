package com.tdil.peugeotservice.android;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
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
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.mobsandgeeks.saripaar.Rule;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.Validator.ValidationListener;
import com.mobsandgeeks.saripaar.annotation.ConfirmPassword;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.Password;
import com.mobsandgeeks.saripaar.annotation.Regex;
import com.mobsandgeeks.saripaar.annotation.TextRule;
import com.tdil.peugeotservice.R;
import com.tdil.peugeotservice.android.gui.BeanMappingFunction;
import com.tdil.peugeotservice.android.gui.BeanMappingListAdapter;
import com.tdil.peugeotservice.android.rest.client.HttpMethod;
import com.tdil.peugeotservice.android.rest.client.IRestClientObserver;
import com.tdil.peugeotservice.android.rest.client.IRestClientTask;
import com.tdil.peugeotservice.android.rest.client.RESTClientTask;
import com.tdil.peugeotservice.android.rest.client.RESTConstants;
import com.tdil.peugeotservice.android.rest.client.RestParams;
import com.tdil.peugeotservice.android.rest.model.AddressTypeBean;
import com.tdil.peugeotservice.android.rest.model.AddressTypeBeanCollection;
import com.tdil.peugeotservice.android.rest.model.DocumentTypeBean;
import com.tdil.peugeotservice.android.rest.model.DocumentTypeCollection;
import com.tdil.peugeotservice.android.rest.model.LoginResponse;
import com.tdil.peugeotservice.android.rest.model.PersonBean;
import com.tdil.peugeotservice.android.rest.model.StateBean;
import com.tdil.peugeotservice.android.rest.model.StateBeanCollection;
import com.tdil.peugeotservice.android.utils.Messages;

/**
 * Activity which displays a login screen to the user, offering registration as
 * well.
 */
public class RegisterActivity extends ActionBarActivity implements IRestClientObserver, ValidationListener {

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

	private IRestClientTask mAuthTask = null;
	// Values for email and password at the time of the login attempt.
	private String mDocType;
	private String mDocNumber;
	private String mPassword;

	private int mYear;
    private int mMonth;
    private int mDay;
    private Validator validator;
    static final int DATE_DIALOG_ID = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Thread.setDefaultUncaughtExceptionHandler(new UnCaughtException(this));
		//Remove title bar
		//this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		//Remove notification bar
		//this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

		validator = new Validator(this);
	    validator.setValidationListener(this);
		
		setContentView(R.layout.activity_register);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		
		document = (TextView) findViewById(R.id.documentNumber);
		
		firstName = (TextView) findViewById(R.id.firstName);
		
		lastName = (TextView) findViewById(R.id.lastName);
		
		male = (RadioButton) findViewById(R.id.chkMale);
		male.setChecked(true);
		female = (RadioButton) findViewById(R.id.chkFemale);
		email = (TextView) findViewById(R.id.email);
		
		password = (TextView)findViewById(R.id.password);
		retypePassword = (TextView)findViewById(R.id.retypePassword);
		
		areaCode = (TextView) findViewById(R.id.areaCode);
		
		mobile = (TextView) findViewById(R.id.mobile);
		
		state = (Spinner) findViewById(R.id.state);
		
		city = (TextView) findViewById(R.id.city);
		
		street1 = (TextView) findViewById(R.id.street1);
		
		street2 = (TextView) findViewById(R.id.street2);
		
		addressType = (Spinner) findViewById(R.id.addressType);
		
		postalCode = (TextView) findViewById(R.id.postalCode);
		
		optIn = (CheckBox) findViewById(R.id.optIn);
		optIn.setChecked(true);
		
		birthDate = (TextView) findViewById(R.id.birthDate);
		birthDate.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				 showDialog(DATE_DIALOG_ID);
			}
		});

		documentType = (Spinner) findViewById(R.id.documentType);
		documentType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				DocumentTypeBean item = (DocumentTypeBean) arg0
						.getItemAtPosition(arg2);
				RegisterActivity.this.mDocType = String.valueOf(item.getId());

			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
			}
		});
		// load document types
		new RESTClientTask(this, HttpMethod.GET, new IRestClientObserver() {
			@Override
			public void sucess(IRestClientTask task) {
				Gson gson = new Gson();
				DocumentTypeCollection col = gson.fromJson(task.getResult(),
						DocumentTypeCollection.class);
				BeanMappingListAdapter<DocumentTypeBean> adapter = new BeanMappingListAdapter<DocumentTypeBean>(
						RegisterActivity.this,
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
				documentType.setAdapter(adapter);
			}

			@Override
			public void error(IRestClientTask task) {
				Messages.connectionErrorMessage(RegisterActivity.this);
			}
		}, RESTConstants.DOCUMENT_TYPES, null, null).executeSerial((Void) null);

		states = (Spinner) findViewById(R.id.state);
		new RESTClientTask(this, HttpMethod.GET, new IRestClientObserver() {
			@Override
			public void sucess(IRestClientTask task) {
				Gson gson = new Gson();
				StateBeanCollection col = gson.fromJson(task.getResult(),
						StateBeanCollection.class);
				BeanMappingListAdapter<StateBean> adapter = new BeanMappingListAdapter<StateBean>(
						RegisterActivity.this,
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
			}

			@Override
			public void error(IRestClientTask task) {
				Messages.connectionErrorMessage(RegisterActivity.this);
			}
		}, RESTConstants.STATES, null, null).executeSerial((Void) null);

		addressTypes = (Spinner) findViewById(R.id.addressType);
		new RESTClientTask(this, HttpMethod.GET, new IRestClientObserver() {
			@Override
			public void sucess(IRestClientTask task) {
				Gson gson = new Gson();
				AddressTypeBeanCollection col = gson.fromJson(task.getResult(),
						AddressTypeBeanCollection.class);
				BeanMappingListAdapter<AddressTypeBean> adapter = new BeanMappingListAdapter<AddressTypeBean>(
						RegisterActivity.this,
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
			}

			@Override
			public void error(IRestClientTask task) {
				Messages.connectionErrorMessage(RegisterActivity.this);
			}
		}, RESTConstants.ADDRESS_TYPES, null, null).executeSerial((Void) null);

		findViewById(R.id.updateButton).setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						validator.validate();
					}
				});
	}

    @Override
    public void preValidation() {
    }
    @Override
    public void onFailure(View failedView, Rule<?> failedRule) {
    	 String message = failedRule.getFailureMessage();
         if (failedView instanceof EditText) {
             failedView.requestFocus();
             ((EditText) failedView).setError(message);
         } else {
             Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
         }
    }
    @Override
    public void onSuccess() {
    	updatePerson();
    }
    @Override
    public void onValidationCancelled() {
    }

	protected void updatePerson() {
		PersonBean personBean = new PersonBean();
		personBean.setDocument(document.getText().toString());
		personBean.setPassword(password.getText().toString());
		DocumentTypeBean documentTypeBean = (DocumentTypeBean)documentType.getSelectedItem();
		personBean.setDocumentType(documentTypeBean.getId());
		personBean.setFirstName(firstName.getText().toString());
		personBean.setLastName(lastName.getText().toString());
		personBean.setEmail(email.getText().toString());
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
		AddressTypeBean addressType = (AddressTypeBean)addressTypes.getSelectedItem();
		personBean.setAddressType(addressType.getName());
		personBean.setPostalCode(postalCode.getText().toString());
		personBean.setOptIn(optIn.isChecked());
		
		Gson gson = new Gson();
		String json = gson.toJson(personBean);
		new RESTClientTask(this, HttpMethod.POST, new IRestClientObserver() {
			@Override
			public void sucess(IRestClientTask task) {
				try {
					if (task.getStatusCode() != 201) {
						JSONObject jsonObj = new JSONObject(task.getResult());
						JSONObject errors = jsonObj.getJSONObject("errors");
						JSONObject entryErrors = errors.optJSONObject("entry");
						if (entryErrors != null) {
							String key = entryErrors.getString("key");
							String value = entryErrors.getString("value");
							if (key.equals("credential.principal")) {
								if (value.equals("CredentialAlreadyExists")) {
									document.setError("El numero de documento ya existe");
								} else {
									email.setError("El email ya existe");
								}	
							}
						} else {
							JSONArray entry = errors.getJSONArray("entry");
							for (int i = 0; i < entry.length(); i++) {
								JSONObject err = entry.getJSONObject(i);
								String key = err.getString("key");
								String value = err.getString("value");
								if (key.equals("credential.principal=credential.principal.CredentialAlreadyExists")) {
									if (value.equals("profile.document=AlreadyExists")) {
										document.setError("El numero de documento ya existe");
									} else {
										email.setError("El email ya existe");
									}	
								}
							}
						}
					} else {
						// TODO analizar la respuesta para mostrar un mensaje u otro
						new AlertDialog.Builder(RegisterActivity.this)
					       .setIcon(R.drawable.ic_launcher)
					       .setTitle("Registracion")
					       .setMessage("Se ha registrado")
					       .setPositiveButton("OK", new DialogInterface.OnClickListener() {
					               public void onClick(DialogInterface dialog, int whichButton) {
					            	   RegisterActivity.this.finish();
					               }
					       }).show();
					}
				} catch (JsonSyntaxException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			@Override
			public void error(IRestClientTask task) {
				Messages.connectionErrorMessage(RegisterActivity.this);
			}
		}, RESTConstants.CREATE_USER, new RestParams(), json).executeSerial((Void) null);
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

	@Regex(order = 6, pattern = "[0-9]{4}-[0-9]{2}-[0-9]{2}", message = "La fecha de nacimiento es invalida")
	private TextView birthDate;

	private Spinner states;

	private Spinner addressTypes;

	@TextRule(order = 1, minLength = 1, maxLength = 50, message = "Ingrese el nombre.")
	@Regex(order = 2, pattern = "[A-Za-z ']+", message = "El nombre es invalido")
	private TextView firstName;

	@TextRule(order = 3, minLength = 1, maxLength = 50, message = "Ingrese el apellido.")
	@Regex(order = 4, pattern = "[A-Za-z ']+", message = "El apellido es invalido")
	private TextView lastName;
	
	@TextRule(order = 2, minLength = 6, maxLength = 10, message = "Ingrese la clave.")
	@Password(order=3, message="clave")
	private TextView password;

	@TextRule(order = 4, minLength = 6, maxLength = 10, message = "Ingrese la nueva clave.")
	@ConfirmPassword(order = 4, message= "Las claves no coinciden")
	private TextView retypePassword;

	private RadioButton male;

	private RadioButton female;

	@Email(order=5, message="Ingrese un email")
	private TextView email;

	@TextRule(order = 5, maxLength = 5, message = "Ingrese hasta 5 digitos.")
	@Regex(order = 6, pattern = "[0-9]*", message = "Ingrese solo numeros")
	private TextView areaCode;

	@TextRule(order = 7, maxLength = 11, message = "Ingrese hasta 11 digitos.")
	@Regex(order = 8, pattern = "[0-9]*", message = "Ingrese solo numeros")
	private TextView mobile;

	private Spinner state;

	@TextRule(order = 9, maxLength = 50, message = "Ingrese hasta 50 caracteres.")
	private TextView street1;

	@TextRule(order = 10, maxLength = 50, message = "Ingrese hasta 50 caracteres.")
	private TextView street2;

	private Spinner addressType;

	@TextRule(order = 10, maxLength = 10, message = "Ingrese hasta 10 caracteres.")
	private TextView postalCode;

	private CheckBox optIn;

	private TextView city;

	@TextRule(order = 10, minLength=1, maxLength = 11, message = "Ingrese hasta 11 caracteres.")
	private TextView document;

	private Spinner documentType;
	
    protected void onPrepareDialog(int id, Dialog dialog) {
        switch (id) {
 
        case DATE_DIALOG_ID:
            ((DatePickerDialog) dialog).updateDate(mYear, mMonth, mDay);
            break;
        }
    }   

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		// TODO Auto-generated method stub
		super.onConfigurationChanged(newConfig);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		//getMenuInflater().inflate(R.menu.activity_login, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		return MenuLogic.handleOnOptionsItemSelected(this, item);
	}

	@Override
	public void error(IRestClientTask task) {
		Messages.connectionErrorMessage(RegisterActivity.this);
		this.mAuthTask = null;
	}

	@Override
	public void sucess(IRestClientTask task) {
		Gson gson = new Gson();
		LoginResponse resp = gson.fromJson(task.getResult(),
				LoginResponse.class);
		if (resp.getLogged()) {
			Intent intent = new Intent(this, IndexActivity.class);
			startActivity(intent);
			finish();
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
