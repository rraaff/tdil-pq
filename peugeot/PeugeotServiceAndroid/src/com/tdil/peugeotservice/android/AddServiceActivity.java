package com.tdil.peugeotservice.android;

import java.util.Calendar;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.mobsandgeeks.saripaar.Rule;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.Validator.ValidationListener;
import com.mobsandgeeks.saripaar.annotation.Regex;
import com.mobsandgeeks.saripaar.annotation.TextRule;
import com.tdil.peugeotservice.R;
import com.tdil.peugeotservice.android.rest.client.HttpMethod;
import com.tdil.peugeotservice.android.rest.client.IRestClientObserver;
import com.tdil.peugeotservice.android.rest.client.IRestClientTask;
import com.tdil.peugeotservice.android.rest.client.RESTClientTask;
import com.tdil.peugeotservice.android.rest.client.RESTConstants;
import com.tdil.peugeotservice.android.rest.client.RestParams;
import com.tdil.peugeotservice.android.rest.model.RESTResponse;
import com.tdil.peugeotservice.android.rest.prevent.model.VehicleValueObjectBean;
import com.tdil.peugeotservice.android.utils.DateUtils;
import com.tdil.peugeotservice.android.utils.Messages;

/**
 * Activity which displays a login screen to the user, offering registration as
 * well.
 */
public class AddServiceActivity extends ActionBarActivity implements ValidationListener {

	public static final String VEHICLE = "VEHICLE";
	private VehicleValueObjectBean vehicleValueObjectBean;

	private int mYear;
    private int mMonth;
    private int mDay;
    private Validator validator;
    static final int DATE_DIALOG_ID = 1;
    
	private TextView serviceDate;

	@TextRule(order = 7, maxLength = 11, message = "Ingrese hasta 11 digitos.")
	@Regex(order = 8, pattern = "[0-9]*", message = "Ingrese solo numeros")
	private EditText serviceKmEditText;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Thread.setDefaultUncaughtExceptionHandler(new UnCaughtException(this));
		validator = new Validator(this);
	    validator.setValidationListener(this);
	    
	    Bundle extras = getIntent().getExtras();
		vehicleValueObjectBean = (VehicleValueObjectBean)extras.getSerializable(VEHICLE);
		
		setContentView(R.layout.add_service_activity);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		
		serviceKmEditText = (EditText) findViewById(R.id.serviceKmEditText);
		
		serviceDate = (TextView) findViewById(R.id.serviceDate);
		Calendar cal = Calendar.getInstance();
		this.mYear = cal.get(Calendar.YEAR);
		this.mMonth = cal.get(Calendar.MONTH);
		this.mDay = cal.get(Calendar.DATE);
		serviceDate.setText(DateUtils.formatDateSp(cal.getTime()));
		serviceDate.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				 showDialog(DATE_DIALOG_ID);
			}
		});

		findViewById(R.id.addServiceButton).setOnClickListener(
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
    	addService();
    }
    @Override
    public void onValidationCancelled() {
    }

	protected void addService() {
		new RESTClientTask(this, HttpMethod.GET, new IRestClientObserver() {
			@Override
			public void sucess(IRestClientTask task) {
				Gson gson = new Gson();
				RESTResponse resp = gson.fromJson(task.getResult(), RESTResponse.class);
				if (resp.getOk()) {
					new AlertDialog.Builder(AddServiceActivity.this)
					.setIcon(R.drawable.ic_launcher)
					.setTitle("Service")
					.setMessage("Se ha agregado el service")
					.setPositiveButton("OK",
							new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,
								int whichButton) {
							AddServiceActivity.this.finish();
						}
					}).show();
				} else {
					new AlertDialog.Builder(AddServiceActivity.this)
					.setIcon(R.drawable.ic_launcher)
					.setTitle("Service")
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
				Messages.connectionErrorMessage(AddServiceActivity.this);
			}
		}, RESTConstants.ADD_SERVICE, new RestParams(RESTConstants.P_VEHICLE, vehicleValueObjectBean.getId())
			.put(RESTConstants.P_DATE, mDay + "-" + (mMonth + 1) + "-" + mYear)
			.put(RESTConstants.P_KM, serviceKmEditText.getText().toString()), null)
		.executeSerial((Void) null);
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
	            TextView birthDate = (TextView)findViewById(R.id.serviceDate);
	            Calendar cal = Calendar.getInstance();
	            cal.set(Calendar.YEAR, year);
	            cal.set(Calendar.MONTH, mMonth);
	            cal.set(Calendar.DATE, mDay);
	            birthDate.setText(DateUtils.formatDateSp(cal.getTime()));
	        }
	    };

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
		getMenuInflater().inflate(R.menu.activity_login, menu);
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
