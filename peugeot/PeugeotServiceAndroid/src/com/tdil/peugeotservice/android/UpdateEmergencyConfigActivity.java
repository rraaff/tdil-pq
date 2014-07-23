package com.tdil.peugeotservice.android;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.codec.binary.StringUtils;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.mobsandgeeks.saripaar.Rule;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.Validator.ValidationListener;
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
import com.tdil.peugeotservice.android.rest.model.ContactDataV1Bean;
import com.tdil.peugeotservice.android.rest.model.LoginResponse;
import com.tdil.peugeotservice.android.rest.model.RESTResponse;
import com.tdil.peugeotservice.android.rest.model.RelationBean;
import com.tdil.peugeotservice.android.rest.model.VehiclePhoneBean;
import com.tdil.peugeotservice.android.rest.prevent.model.ContactDataBean;
import com.tdil.peugeotservice.android.utils.Login;
import com.tdil.peugeotservice.android.utils.Messages;

/**
 * Activity which displays a login screen to the user, offering registration as
 * well.
 */
public class UpdateEmergencyConfigActivity extends PeugeotActivity implements ValidationListener {

    private Validator validator;
    static final int DATE_DIALOG_ID = 1;
    
    @TextRule(order = 1, minLength = 1, maxLength = 150, message = "Ingrese el nombre.")
    @Regex(order = 2, pattern = "[A-Za-z ']+", message = "El nombre es invalido")
    private TextView contact1name;
	
	@TextRule(order = 7, maxLength = 20, message = "Ingrese hasta 20 digitos.")
	@Regex(order = 8, pattern = "[0-9]{2}+-[0-9]{8,15}", message = "Ingrese un numero en formato 54-00000000")
	private TextView contact1phone;

	@TextRule(order = 1, minLength = 1, maxLength = 20, message = "Ingrese la palabra de seguridad.")
    @Regex(order = 2, pattern = "[A-Za-z ']+", message = "La palabra de seguridad es inv�lida")
    private TextView securityWord;
	
	@TextRule(order = 1, minLength = 1, maxLength = 100, message = "Ingrese su cobertura m�dica.")
    private TextView healthInsurance;

	@TextRule(order = 1, maxLength = 150, message = "Ingrese el nombre.")
    @Regex(order = 2, pattern = "[A-Za-z ']*", message = "El nombre es invalido")
    private TextView contact2name;
	private Spinner contact2relationSpinner;
	@TextRule(order = 7, maxLength = 20, message = "Ingrese hasta 20 digitos.")
	@Regex(order = 8, pattern = "[0-9]{2}+-[0-9]{8,15}", message = "Ingrese un numero en formato 54-00000000")
	private TextView contact2phone;
	
	@TextRule(order = 1, maxLength = 150, message = "Ingrese el nombre.")
    @Regex(order = 2, pattern = "[A-Za-z ']*", message = "El nombre es invalido")
    private TextView contact3name;
	private Spinner contact3relationSpinner;
	@TextRule(order = 7, maxLength = 20, message = "Ingrese hasta 20 digitos.")
	@Regex(order = 8, pattern = "[0-9]{2}+-[0-9]{8,15}", message = "Ingrese un numero en formato 54-00000000")
	private TextView contact3phone;
	
	private ContactDataV1Bean actual;
	private CheckBox samePhoneForAllCheckbox;
	private CheckBox samePhoneForEachVehicleCheckbox;
	private EditText uniqueVehiclePhone;
	private EditText sameVehiclePhone;
	private LinearLayout allVehiclesPhonesLayer;
	private LinearLayout moreThanOneVehicleLayer;
	private LinearLayout onlyOneVehicleLayer;
	
	private Map<String, EditText> domainsToPhones = new HashMap<String, EditText>();
	
	private String contact1relationSelected;
	private String contact2relationSelected;
	private String contact3relationSelected;
	private LinearLayout allPhonesContainer;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Thread.setDefaultUncaughtExceptionHandler(new UnCaughtException(this));
		validator = new Validator(this);
	    validator.setValidationListener(this);
	    
		setContentView(R.layout.update_contact_data_activity);
		setTypeface(this, R.id.loadingInfoText);
		setTypeface(this, R.id.contact1NameEditText);
		setTypeface(this, R.id.contact1PhoneEditText);
		setTypeface(this, R.id.securityWordEditText);
		setTypeface(this, R.id.	healthInsuranceEditText);
		setTypeface(this, R.id.	TextView01);
		setTypeface(this, R.id.contact2NameEditText);
		setTypeface(this, R.id.contact2PhoneEditText);
		setTypeface(this, R.id.TextView02);
		setTypeface(this, R.id.contact3NameEditText);
		setTypeface(this, R.id.contact3PhoneEditText);
		setTypeface(this, R.id.	updateEmergenccyConfigButton);
		setTypeface(this, R.id.sendAlertButton);
		
		setTypeface(this, R.id.samePhoneForAllCheckbox);
		setTypeface(this, R.id.uniqueVehiclePhone);
		setTypeface(this, R.id.sameVehiclePhone);
		setTypeface(this, R.id.samePhoneForEachVehicleCheckbox);
		
		
		customizeActionBar();
		
		uniqueVehiclePhone = (EditText) findViewById(R.id.uniqueVehiclePhone);
		sameVehiclePhone = (EditText) findViewById(R.id.sameVehiclePhone);
		samePhoneForAllCheckbox = (CheckBox) findViewById(R.id.samePhoneForAllCheckbox);
		samePhoneForEachVehicleCheckbox = (CheckBox) findViewById(R.id.samePhoneForEachVehicleCheckbox);
		allVehiclesPhonesLayer = (LinearLayout)findViewById(R.id.allVehiclesPhonesLayer);
		moreThanOneVehicleLayer = (LinearLayout)findViewById(R.id.moreThanOneVehicleLayer);
		onlyOneVehicleLayer = (LinearLayout)findViewById(R.id.onlyOneVehicleLayer);
		
		if (domainsToPhones.size() == 1) {
			validator.put(uniqueVehiclePhone, new Rule<EditText>("Ingrese un numero en formato 54-00000000") {
				public boolean isValid(EditText view) {
					if (!samePhoneForAllCheckbox.isChecked()) {
						Pattern pattern = Pattern.compile("^[0-9]{2}+-[0-9]{8,15}$");
						String toValidate = view.getText().toString();
		    			Matcher m = pattern.matcher(toValidate);
		    			return m.find();
					}
					return true;
				};
			});
		} else {
			validator.put(sameVehiclePhone, new Rule<EditText>("Ingrese un numero en formato 54-00000000") {
				public boolean isValid(EditText view) {
					if (!samePhoneForAllCheckbox.isChecked() && samePhoneForEachVehicleCheckbox.isChecked()) {
						Pattern pattern = Pattern.compile("^[0-9]{2}+-[0-9]{8,15}$");
						String toValidate = view.getText().toString();
		    			Matcher m = pattern.matcher(toValidate);
		    			return m.find();
					}
					return true;
				};
			});
		}
		
		samePhoneForAllCheckbox.setOnClickListener(
			new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					if (samePhoneForAllCheckbox.isChecked()) {
						if (domainsToPhones.size() == 1) {
							onlyOneVehicleLayer.setVisibility(View.GONE);
						} else {
							moreThanOneVehicleLayer.setVisibility(View.GONE);
						}
					} else {
						if (domainsToPhones.size() == 1) {
							onlyOneVehicleLayer.setVisibility(View.VISIBLE);
						} else {
							moreThanOneVehicleLayer.setVisibility(View.VISIBLE);
						}
					}
				}
			});
		
		samePhoneForEachVehicleCheckbox.setOnClickListener(
			new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					if (samePhoneForEachVehicleCheckbox.isChecked()) {
						sameVehiclePhone.setVisibility(View.VISIBLE);
						allVehiclesPhonesLayer.setVisibility(View.GONE);
					} else {
						sameVehiclePhone.setVisibility(View.GONE);
						allVehiclesPhonesLayer.setVisibility(View.VISIBLE);
					}
				}
			});
		
		allPhonesContainer = (LinearLayout) findViewById(R.id.allVehiclesPhonesLayer);

		contact1name = (TextView) findViewById(R.id.contact1NameEditText);
		final List<RelationBean> allRelations = RelationBean.allRelations();
		BeanMappingListAdapter<RelationBean> adapter = new BeanMappingListAdapter<RelationBean>(
				UpdateEmergencyConfigActivity.this,
				android.R.layout.simple_spinner_item, allRelations,
				new BeanMappingFunction<RelationBean>() {
					public String key(RelationBean t) {
						return t.getKey();
					};

					@Override
					public String value(RelationBean t) {
						return t.getValue();
					}
				});
		
		
		contact1phone = (TextView) findViewById(R.id.contact1PhoneEditText);
		securityWord = (TextView) findViewById(R.id.securityWordEditText);
		healthInsurance = (TextView) findViewById(R.id.healthInsuranceEditText);
		
		final List<RelationBean> filteredRelations = RelationBean.filteredRelations();
		BeanMappingListAdapter<RelationBean> filteredadapter = new BeanMappingListAdapter<RelationBean>(
				UpdateEmergencyConfigActivity.this,
				android.R.layout.simple_spinner_item, filteredRelations,
				new BeanMappingFunction<RelationBean>() {
					public String key(RelationBean t) {
						return t.getKey();
					};
					
					@Override
					public String value(RelationBean t) {
						return t.getValue();
					}
				});
		contact2name = (TextView) findViewById(R.id.contact2NameEditText);
		contact2relationSpinner = (Spinner) findViewById(R.id.contact2RelationSpinner);
		contact2relationSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				RelationBean item = (RelationBean) arg0
						.getItemAtPosition(arg2);
				UpdateEmergencyConfigActivity.this.contact2relationSelected = item.getKey();
			}
			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
			}
		});
		contact2relationSpinner.setAdapter(filteredadapter);
		contact2phone = (TextView) findViewById(R.id.contact2PhoneEditText);
		
		contact3name = (TextView) findViewById(R.id.contact3NameEditText);
		contact3relationSpinner = (Spinner) findViewById(R.id.contact3RelationSpinner);
		contact3relationSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				RelationBean item = (RelationBean) arg0
						.getItemAtPosition(arg2);
				UpdateEmergencyConfigActivity.this.contact3relationSelected = item.getKey();
			}
			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
			}
		});
		contact3relationSpinner.setAdapter(filteredadapter);
		contact3phone = (TextView) findViewById(R.id.contact3PhoneEditText);

		new RESTClientTask(this, HttpMethod.GET, new IRestClientObserver() {
			@Override
			public void sucess(IRestClientTask task) {
				Gson gson = new Gson();
				ContactDataV1Bean contactDataBean = gson.fromJson(task.getResult(),
						ContactDataV1Bean.class);
				
				actual = contactDataBean;
				Set<String> vehiclesPhones = new HashSet<String>();
				for (VehiclePhoneBean vpb : contactDataBean.getVehiclesPhones()) {
					TextView myEditText = new TextView(UpdateEmergencyConfigActivity.this); // Pass it an Activity or Context
					myEditText.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT)); // Pass two args; must be LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT, or an integer pixel value.
					myEditText.setText(vpb.getDomain());
					// TODO ver pablo el color
					myEditText.setTextColor(Color.rgb(227,27,35));
					setTypeface(UpdateEmergencyConfigActivity.this, myEditText);
					allPhonesContainer.addView(myEditText);
					
					EditText aVehiclePhoneText = (EditText)getLayoutInflater().inflate(R.layout.edit_text, null);
					aVehiclePhoneText.setText(vpb.getPhone());
					vehiclesPhones.add(vpb.getPhone());
					setTypeface(UpdateEmergencyConfigActivity.this, aVehiclePhoneText);
					allPhonesContainer.addView(aVehiclePhoneText);
					// agrego el validador
					validator.put(aVehiclePhoneText, new Rule<EditText>("Ingrese un numero en formato 54-00000000") {
						public boolean isValid(EditText view) {
							if (!samePhoneForAllCheckbox.isChecked() && !samePhoneForEachVehicleCheckbox.isChecked()) {
								Pattern pattern = Pattern.compile("^[0-9]{2}+-[0-9]{8,15}$");
								String toValidate = view.getText().toString();
				    			Matcher m = pattern.matcher(toValidate);
				    			return m.find();
							}
							return true;
						};
					});
					domainsToPhones.put(vpb.getDomain(), aVehiclePhoneText);
				}
				if (vehiclesPhones.size() == 1) {
					uniqueVehiclePhone.setText(contactDataBean.getVehiclesPhones().get(0).getPhone());
					sameVehiclePhone.setText(contactDataBean.getVehiclesPhones().get(0).getPhone());
					samePhoneForEachVehicleCheckbox.setChecked(true);
					allVehiclesPhonesLayer.setVisibility(View.GONE);
				}  
				if (contactDataBean.getContact1phone() != null && contactDataBean.getContact1phone().length() > 0) {
					vehiclesPhones.add(contactDataBean.getContact1phone());
					contact1phone.setText(contactDataBean.getContact1phone());
				}
				if (contactDataBean.getVehiclesPhones().size() == 1) {
					contact1phone.setText(vehiclesPhones.iterator().next());
					moreThanOneVehicleLayer.setVisibility(View.GONE);
					onlyOneVehicleLayer.setVisibility(View.VISIBLE);
					if (vehiclesPhones.size() == 1) {
						onlyOneVehicleLayer.setVisibility(View.GONE);
						samePhoneForAllCheckbox.setChecked(true);
					} else {
						samePhoneForAllCheckbox.setChecked(false);
					}
				} else {
					moreThanOneVehicleLayer.setVisibility(View.VISIBLE);
					onlyOneVehicleLayer.setVisibility(View.GONE);
					if (vehiclesPhones.size() == 1) {
						contact1phone.setText(vehiclesPhones.iterator().next());
						moreThanOneVehicleLayer.setVisibility(View.GONE);
						samePhoneForAllCheckbox.setChecked(true);
					} else {
						samePhoneForAllCheckbox.setChecked(false);
					}
				}
				
				
				contact1name.setText(contactDataBean.getContact1name());
				int index = 0;
				securityWord.setText(contactDataBean.getContact1secword());
				healthInsurance.setText(contactDataBean.getContact1healthi());
				
				contact2name.setText(contactDataBean.getContact2name());
				index = 0;
				for (RelationBean bean : filteredRelations) {
					if (bean.getKey().equals(contactDataBean.getContact2relation())) {
						UpdateEmergencyConfigActivity.this.contact2relationSpinner
								.setSelection(index);
					}
					index = index + 1;
				}
				contact2phone.setText(contactDataBean.getContact2phone());
				
				contact3name.setText(contactDataBean.getContact3name());
				index = 0;
				for (RelationBean bean : filteredRelations) {
					if (bean.getKey().equals(contactDataBean.getContact3relation())) {
						UpdateEmergencyConfigActivity.this.contact3relationSpinner
								.setSelection(index);
					}
					index = index + 1;
				}
				contact3phone.setText(contactDataBean.getContact3phone());
			}

			@Override
			public void error(IRestClientTask task) {
				Messages.connectionErrorMessage(UpdateEmergencyConfigActivity.this);
			}
		}, RESTConstants.GET_CONTACT_DATA_V1, new RestParams(), null).executeSerial((Void) null);
		
		findViewById(R.id.updateEmergenccyConfigButton).setOnClickListener(
			new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					validator.validate();
				}
			});
	}

    @Override
    public void preValidation() {
    	/*Pattern pattern = Pattern.compile("[0-9]{2}+-[0-9]{7,11}");
    	String message = "Ingrese un numero en formato 54-00000000";
    	if(!samePhoneForAllCheckbox.isChecked()) {
    		if (actual.getVehiclesPhones().size() == 1) {
    			String toValidate = uniqueVehiclePhone.getText().toString();
    			Matcher m = pattern.matcher(toValidate);
    			if (!m.find()) {
    				EditText failedView = uniqueVehiclePhone;
    				failedView.requestFocus();
    	            ((EditText) failedView).setError(message);
    			}
    		} else {
    			if (samePhoneForEachVehicleCheckbox.isChecked()) {
    				String toValidate = sameVehiclePhone.getText().toString();
        			Matcher m = pattern.matcher(toValidate);
        			if (!m.find()) {
        				EditText failedView = uniqueVehiclePhone;
        				failedView.requestFocus();
        	            ((EditText) failedView).setError(message);
        			}
    			} else {
    				for (Map.Entry<String, EditText> entry : domainsToPhones.entrySet()) {
    					String toValidate = entry.getValue().getText().toString();
    					Matcher m = pattern.matcher(toValidate);
            			if (!m.find()) {
            				EditText failedView = entry.getValue();
            				failedView.requestFocus();
            	            ((EditText) failedView).setError(message);
            			}
					}
    			}
    		}
    	}*/
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
    	updateConfigData();
    }
    @Override
    public void onValidationCancelled() {
    }

	protected void updateConfigData() {
		ContactDataV1Bean contactDataBean = actual;
		contactDataBean.setContact1phone(contact1phone.getText().toString());
		contactDataBean.setContact1secword(securityWord.getText().toString());
		contactDataBean.setContact1healthi(healthInsurance.getText().toString());
		
		contactDataBean.setContact2name(contact2name.getText().toString());
		contactDataBean.setContact2relation(contact2relationSelected);
		contactDataBean.setContact2phone(contact2phone.getText().toString());
		
		contactDataBean.setContact3name(contact3name.getText().toString());
		contactDataBean.setContact3relation(contact3relationSelected);
		contactDataBean.setContact3phone(contact3phone.getText().toString());
		if (samePhoneForAllCheckbox.isChecked()) {
			for (VehiclePhoneBean vpb : actual.getVehiclesPhones()) {
				vpb.setPhone(contact1phone.getText().toString());
			}
		} else {
			if (actual.getVehiclesPhones().size() == 1) {
				actual.getVehiclesPhones().get(0).setPhone(uniqueVehiclePhone.getText().toString());
			} else {
				if (samePhoneForEachVehicleCheckbox.isChecked()) {
					for (VehiclePhoneBean vpb : actual.getVehiclesPhones()) {
						vpb.setPhone(sameVehiclePhone.getText().toString());
					}
				} else {
					for (VehiclePhoneBean vpb : actual.getVehiclesPhones()) {
						vpb.setPhone(domainsToPhones.get(vpb.getDomain()).getText().toString());
					}
				}
			}
		}
		
		Gson gson = new Gson();
		String json = gson.toJson(contactDataBean);
		new RESTClientTask(this, HttpMethod.POST, new IRestClientObserver() {
			@Override
			public void sucess(IRestClientTask task) {
				Gson gson = new Gson();
				RESTResponse resp = gson.fromJson(task.getResult(), RESTResponse.class);
				if (resp.getOk()) {
					LoginResponse login = Login.getLoggedUser(UpdateEmergencyConfigActivity.this);
					login.setMustCompleteEmergencyData(false);
					Login.setLoggedUser(UpdateEmergencyConfigActivity.this, login);
					new AlertDialog.Builder(UpdateEmergencyConfigActivity.this)
		               .setIcon(R.drawable.ic_launcher)
		               .setTitle("Modificacion de datos")
		               .setMessage("Se han modificado los datos")
		               .setPositiveButton("OK", new DialogInterface.OnClickListener() {
		                       public void onClick(DialogInterface dialog, int whichButton) {
		                    	   UpdateEmergencyConfigActivity.this.finish();
		                       }
		               }).show();
				} else {
					new AlertDialog.Builder(UpdateEmergencyConfigActivity.this)
		               .setIcon(R.drawable.ic_launcher)
		               .setTitle("Modificacion de datos")
		               .setMessage("Ha ocurrido un error modificando los datos")
		               .setPositiveButton("OK", new DialogInterface.OnClickListener() {
		                       public void onClick(DialogInterface dialog, int whichButton) {
		                       }
		               }).show();
				}
			}
			@Override
			public void error(IRestClientTask task) {
				Messages.connectionErrorMessage(UpdateEmergencyConfigActivity.this);
			}
		}, RESTConstants.POST_CONTACT_DATA_V1, new RestParams(), json).executeSerial((Void) null);
	}


	
	
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		// TODO Auto-generated method stub
		super.onConfigurationChanged(newConfig);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		getMenuInflater().inflate(R.menu.menu_full, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		return MenuLogic.handleOnOptionsItemSelected(this, item);
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
