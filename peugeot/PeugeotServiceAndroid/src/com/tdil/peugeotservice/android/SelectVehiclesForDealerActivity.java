package com.tdil.peugeotservice.android;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.mobsandgeeks.saripaar.Rule;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.Validator.ValidationListener;
import com.mobsandgeeks.saripaar.annotation.Email;
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
import com.tdil.peugeotservice.android.rest.model.EmailBean;
import com.tdil.peugeotservice.android.rest.model.RESTResponse;
import com.tdil.peugeotservice.android.rest.prevent.model.DealerBean;
import com.tdil.peugeotservice.android.rest.prevent.model.VehicleValueObjectBean;
import com.tdil.peugeotservice.android.rest.prevent.model.VehicleValueObjectBeanCollection;
import com.tdil.peugeotservice.android.utils.Login;
import com.tdil.peugeotservice.android.utils.Messages;

@SuppressLint("ResourceAsColor")
public class SelectVehiclesForDealerActivity extends ActionBarActivity implements ValidationListener {

	public static final String DEALER = "DEALER";
	private DealerBean dealer;
	
	ListView list;
	SelectVehiclesForDealerListAdapter adapter;
	public ArrayList<VehicleValueObjectBean> CustomListViewValuesArr = new ArrayList<VehicleValueObjectBean>();
	
	@Email(message ="Ingrese un email válido", order = 2)
	@TextRule(order = 1, minLength = 4, message = "Ingrese el email donde desea recibir el aviso.")
	private TextView email;
	
	private Spinner vehiclesSpinner;
	
	private VehicleValueObjectBean selectedVehicle;
	
	private Validator validator;
	
	/**
	 * The default email to populate the email field with.
	 */
	// Values for email and password at the time of the login attempt.

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Thread.setDefaultUncaughtExceptionHandler(new UnCaughtException(this));
		setContentView(R.layout.select_vehicles_for_dealers_activity);

		validator = new Validator(this);
		validator.setValidationListener(this);
		
		Bundle extras = getIntent().getExtras();
		dealer = (DealerBean)extras.getSerializable(DEALER);
	
		this.getSupportActionBar().setTitle(Login.getLoggedUser(this).getName());
		
		vehiclesSpinner = (Spinner) findViewById(R.id.vehiclesForDealersSpinner);
		email = (TextView) findViewById(R.id.emailForServiceEditText);
		
		((TextView)findViewById(R.id.dealerNameTextView)).setText(dealer.getName() != null ? dealer.getName() : "-");
		((TextView)findViewById(R.id.dealerAddressTextView)).setText(dealer.getAddress() != null ? dealer.getAddress() : "-");

		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		// busco el email
		new RESTClientTask(this, HttpMethod.GET, new IRestClientObserver() {
			@Override
			public void sucess(IRestClientTask task) {
				Gson gson = new Gson();
				EmailBean em = gson.fromJson(task.getResult(),
						EmailBean.class);
				email.setText(em.getEmail());
			}

			@Override
			public void error(IRestClientTask task) {
				Messages.connectionErrorMessage(SelectVehiclesForDealerActivity.this);
			}
		}, RESTConstants.GET_EMAIL_FOR_ADVICE, new RestParams(), null).executeSerial((Void) null);
		
		new RESTClientTask(this, HttpMethod.GET, new IRestClientObserver() {
			@Override
			public void sucess(IRestClientTask task) {
				Gson gson = new Gson();
				VehicleValueObjectBeanCollection col = gson.fromJson(task.getResult(),
						VehicleValueObjectBeanCollection.class);
				VehicleValueObjectBean all = new VehicleValueObjectBean();
				all.setDomain("TODOS");
				all.setDescription("TODOS");
				all.setId(0);
				col.addFirst(all);
				BeanMappingListAdapter<VehicleValueObjectBean> adapter = new BeanMappingListAdapter<VehicleValueObjectBean>(
						SelectVehiclesForDealerActivity.this,
						android.R.layout.simple_spinner_item, col.getList(),
						new BeanMappingFunction<VehicleValueObjectBean>() {
							public String key(VehicleValueObjectBean t) {
								return String.valueOf(t.getId());
							};

							@Override
							public String value(VehicleValueObjectBean t) {
								return t.getDescription();
							}
						});
				vehiclesSpinner.setEnabled(true);
				vehiclesSpinner.setClickable(true);
				vehiclesSpinner.setAdapter(adapter);
			}

			@Override
			public void error(IRestClientTask task) {
				Messages.connectionErrorMessage(SelectVehiclesForDealerActivity.this);
			}
		}, RESTConstants.MY_VEHICLES, new RestParams(), null).executeSerial((Void) null);
		
		vehiclesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				final VehicleValueObjectBean item = (VehicleValueObjectBean) arg0
						.getItemAtPosition(arg2);
				selectedVehicle = item;
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
			}
		});
		
		findViewById(R.id.updateVehiclesDealersButton).setOnClickListener(
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
    	updateVehiclesDealers();
    }

	@Override
    public void onValidationCancelled() {
    }
	
	private void updateVehiclesDealers() {
		new RESTClientTask(this, HttpMethod.GET, new IRestClientObserver() {
			@Override
			public void sucess(IRestClientTask task) {
				Gson gson = new Gson();
				RESTResponse resp = gson.fromJson(task.getResult(), RESTResponse.class);
				if (resp.getOk()) {
					new AlertDialog.Builder(SelectVehiclesForDealerActivity.this)
					.setIcon(R.drawable.ic_launcher)
					.setTitle("Seleccion de concesionaria")
					.setMessage("Se ha cambiado la concesionaria")
					.setPositiveButton("OK",
							new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,
								int whichButton) {
							SelectVehiclesForDealerActivity.this.finish();
						}
					}).show();
				} else {
					new AlertDialog.Builder(SelectVehiclesForDealerActivity.this)
					.setIcon(R.drawable.ic_launcher)
					.setTitle("Seleccion de concesionaria")
					.setMessage("Ha occurrido un error, por favor intentelo nuevamente")
					.setPositiveButton("OK",
							new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,
								int whichButton) {
						}
					}).show();
				}
			}
			
			@Override
			public void error(IRestClientTask task) {
				Messages.connectionErrorMessage(SelectVehiclesForDealerActivity.this);
			}
		}, RESTConstants.CHANGE_DEALER, new RestParams(RESTConstants.P_DEALER, dealer.getId())
			.put(RESTConstants.P_VEHICLE, selectedVehicle.getId())
			.put(RESTConstants.P_EMAIL, email.getText().toString()), null)
		.executeSerial((Void) null);
	}



	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		// TODO Auto-generated method stub
		super.onConfigurationChanged(newConfig);
	}

	public Toast textView(View findViewById) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		getMenuInflater().inflate(R.menu.menu_without_apps, menu);
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
