package com.tdil.thalamus.android;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.protocol.HttpContext;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.gson.Gson;
import com.mobsandgeeks.saripaar.Rule;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.Validator.ValidationListener;
import com.mobsandgeeks.saripaar.annotation.Password;
import com.mobsandgeeks.saripaar.annotation.TextRule;
import com.tdil.lojack.rl.R;
import com.tdil.thalamus.android.gui.BeanMappingFunction;
import com.tdil.thalamus.android.gui.BeanMappingListAdapter;
import com.tdil.thalamus.android.rest.client.HttpMethod;
import com.tdil.thalamus.android.rest.client.IRestClientObserver;
import com.tdil.thalamus.android.rest.client.IRestClientTask;
import com.tdil.thalamus.android.rest.client.RESTClientTask;
import com.tdil.thalamus.android.rest.client.RESTClientTaskOpt;
import com.tdil.thalamus.android.rest.client.RESTConstants;
import com.tdil.thalamus.android.rest.client.RestParams;
import com.tdil.thalamus.android.rest.model.DocumentTypeBean;
import com.tdil.thalamus.android.rest.model.LoginResponse;
import com.tdil.thalamus.android.rest.model.RESTResponse;
import com.tdil.thalamus.android.rest.model.RegisterAndroidBean;
import com.tdil.thalamus.android.utils.Login;
import com.tdil.thalamus.android.utils.Messages;

/**
 * Activity which displays a login screen to the user, offering registration as
 * well.
 */
public class LoginActivity extends LoJackNotLoggedActivity implements IRestClientObserver,
		ValidationListener {

	private final static int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
	public static final String EXTRA_MESSAGE = "message";
	public static final String PROPERTY_REG_ID = "registration_id";
	private static final String PROPERTY_APP_VERSION = "appVersion";
	private final static String TAG = "LaunchActivity";
	protected String SENDER_ID = "453786616923";
	private GoogleCloudMessaging gcm = null;
	private String regid = null;
	private Context context = null;
	
	/*
	 * public static final String URL_WEBSITE = "http://www.lojack-app.com.ar/";
	 * public static final String URL_ANDROID_VERSION = URL_WEBSITE +
	 * "android_version.txt";
	 */

	/**
	 * The default email to populate the email field with.
	 */
	public static final String EXTRA_EMAIL = "com.example.android.authenticatordemo.extra.EMAIL";
	
	public static final String FROM_LAUNCH = "FROM_LAUNCH";

	private RESTClientTask mAuthTask = null;
	// Values for email and password at the time of the login attempt.

	@TextRule(order = 1, minLength = 1, maxLength = 11, message = "Selecione el tipo de documento.")
	private Spinner docTypeSpinner;

	private String mDocType;

	@TextRule(order = 2, minLength = 1, maxLength = 11, message = "Ingrese hasta 11 caracteres.")
	private EditText docNumberEdittext;

	private String mDocNumber;

	@TextRule(order = 3, minLength = 4, message = "Ingrese la contraseña.")
	@Password(order = 4, message = "Contraseña")
	private EditText passwordEditText;

	private String mPassword;
	private CheckBox remCheckBox;

//	private DocumentTypeCollection col;

	private Validator validator;
	
	private boolean fromLaunch = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		customizeActionBar();
		
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			fromLaunch = FROM_LAUNCH.equals(extras.getString(FROM_LAUNCH));
			extras.putString(FROM_LAUNCH, "");
		}

		// Editor e = this.getPreferences(Context.MODE_PRIVATE).edit();
		// e.clear();
		// e.commit();

		validator = new Validator(this);
		validator.setValidationListener(this);

		/* TODO borrar */
		String mDocNumber = this.getPreferences(Context.MODE_PRIVATE)
				.getString("mDocNumber", "");
		String mPassword = this.getPreferences(Context.MODE_PRIVATE).getString(
				"mPassword", "");

		docTypeSpinner = (Spinner) findViewById(R.id.documentType);
		docTypeSpinner
				.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
					@Override
					public void onItemSelected(AdapterView<?> arg0, View arg1,
							int arg2, long arg3) {
						DocumentTypeBean item = (DocumentTypeBean) arg0
								.getItemAtPosition(arg2);
						LoginActivity.this.mDocType = String.valueOf(item
								.getId());

					}

					@Override
					public void onNothingSelected(AdapterView<?> arg0) {
					}
				});

		List<DocumentTypeBean> documentTypeBeans = new ArrayList<DocumentTypeBean>();
		documentTypeBeans.add(new DocumentTypeBean(1, "DNI",1));
		documentTypeBeans.add(new DocumentTypeBean(2, "Pasaporte",1));
		documentTypeBeans.add(new DocumentTypeBean(3, "Libreta Civica",1));
		documentTypeBeans.add(new DocumentTypeBean(4, "Libreta Enrolamiento",1));
		documentTypeBeans.add(new DocumentTypeBean(5, "Cedula Identidad",1));
		
		BeanMappingListAdapter<DocumentTypeBean> adapter = new BeanMappingListAdapter<DocumentTypeBean>(
				LoginActivity.this,
				android.R.layout.simple_spinner_item, documentTypeBeans,
				new BeanMappingFunction<DocumentTypeBean>() {
					public String key(DocumentTypeBean t) {
						return String.valueOf(t.getId());
					};

					@Override
					public String value(DocumentTypeBean t) {
						return t.getName();
					}
				});
		LoginActivity.this.docTypeSpinner.setAdapter(adapter);
		String mDocTypeSt = LoginActivity.this.getPreferences(
				Context.MODE_PRIVATE).getString("mDocType", "-1");
		if (mDocTypeSt.length() == 0) {
			mDocTypeSt = "-1";
		}
		int mDocType = Integer.valueOf(mDocTypeSt);
		boolean found = false;
		if (mDocType != -1) {
			int index = 0;
			for (DocumentTypeBean bean : documentTypeBeans) {
				if (bean.getId() == mDocType) {
					LoginActivity.this.mDocType = mDocTypeSt;
					LoginActivity.this.docTypeSpinner
							.setSelection(index);
					found = true;
				}
				index = index + 1;
			}
		}
		/*
		new RESTClientTask(this, HttpMethod.GET, new IRestClientObserver() {
			@Override
			public void sucess(IRestClientTask task) {
				Gson gson = new Gson();
				col = gson.fromJson(task.getResult(),
						DocumentTypeCollection.class);
				BeanMappingListAdapter<DocumentTypeBean> adapter = new BeanMappingListAdapter<DocumentTypeBean>(
						LoginActivity.this,
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
				LoginActivity.this.docTypeSpinner.setAdapter(adapter);

				String mDocTypeSt = LoginActivity.this.getPreferences(
						Context.MODE_PRIVATE).getString("mDocType", "-1");
				if (mDocTypeSt.length() == 0) {
					mDocTypeSt = "-1";
				}
				int mDocType = Integer.valueOf(mDocTypeSt);
				boolean found = false;
				if (mDocType != -1) {
					int index = 0;
					for (DocumentTypeBean bean : col.getList()) {
						if (bean.getId() == mDocType) {
							LoginActivity.this.mDocType = mDocTypeSt;
							LoginActivity.this.docTypeSpinner
									.setSelection(index);
							found = true;
						}
						index = index + 1;
					}
				}
				if (found && remCheckBox.isChecked() && fromLaunch) {
					attemptLogin();
				}
			}

			@Override
			public void error(IRestClientTask task) {
				Messages.connectionErrorMessage(LoginActivity.this);
			}
		}, RESTConstants.DOCUMENT_TYPES, null, null).executeSerial((Void) null);*/

		/*
		 * List<String> list = new ArrayList<String>(); list.add("DNI");
		 * list.add("LE"); SpinAdapter adapter = new SpinAdapter(this,
		 * android.R.layout.simple_spinner_item, list);
		 * spinner.setAdapter(adapter);
		 */

		findViewById(R.id.loginButtons).setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						validator.validate();
					}
				});

		findViewById(R.id.requestResetPassword).setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						Intent intent = new Intent(LoginActivity.this,
								RequestResetPasswordActivity.class);
						startActivity(intent);
					}
				});
		remCheckBox = (CheckBox) findViewById(R.id.rememberPasswordCheckbox);

		/*
		findViewById(R.id.requestRegistration).setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						Intent intent = new Intent(LoginActivity.this,
								RegisterActivity.class);
						startActivity(intent);
					}
				});
		 
		findViewById(R.id.gotoMoreInfo).setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						Intent intent = new Intent(LoginActivity.this,
								RegisterActivity.class);
						startActivity(intent);
					}
				});*/
		docNumberEdittext = (EditText) findViewById(R.id.documentNumber);
		docNumberEdittext.setText(mDocNumber);
		passwordEditText = (EditText) findViewById(R.id.password);
		passwordEditText.setText(mPassword);
		if (!isEmpty(mDocNumber) && !isEmpty(mPassword)) {
			remCheckBox.setChecked(true);
		}

		try {
			int curVersion = getPackageManager().getPackageInfo(
					"com.tdil.lojack.rl", 0).versionCode;
			String curVersionName = getPackageManager().getPackageInfo(
					"com.tdil.lojack.rl", 0).versionName;
			((TextView) findViewById(R.id.appVersion)).setText(curVersion + "-"
					+ curVersionName);
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (found && remCheckBox.isChecked() && fromLaunch) {
			attemptLogin();
		}

	}

	private boolean isEmpty(String mDocNumber2) {
		if (mDocNumber2 == null) {
			return true;
		}
		return mDocNumber2.trim().length() == 0;
	}
	
	@Override
	protected boolean mustUpdateMessages() {
		return false;
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
		attemptLogin();
	}

	@Override
	public void onValidationCancelled() {
	}

	public void attemptLogin() {
		fromLaunch = false;
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
		mAuthTask.executeSerial((Void) null);
	}

	@Override
	public void error(IRestClientTask task) {
		Messages.connectionErrorMessage(LoginActivity.this);
		this.mAuthTask = null;
	}

	@Override
	public void sucess(IRestClientTask task) {
		Gson gson = new Gson();
		final LoginResponse resp = gson.fromJson(task.getResult(),
				LoginResponse.class);
		if (resp.getLogged()) {
			Login.setLoggedUser(this, resp);

			if (LoginActivity.this.remCheckBox.isChecked()) {
				Editor e = this.getPreferences(Context.MODE_PRIVATE).edit();
				e.putString("mDocType", mDocType);
				e.putString("mDocNumber", mDocNumber);
				e.putString("mPassword", mPassword);
				e.commit();
			} else {
				Editor e = this.getPreferences(Context.MODE_PRIVATE).edit();
				e.putString("mDocType", "");
				e.putString("mDocNumber", "");
				e.putString("mPassword", "");
				e.commit();
			}
			RESTClientTask.httpClient
					.addRequestInterceptor(new HttpRequestInterceptor() {
						@Override
						public void process(HttpRequest arg0, HttpContext arg1)
								throws HttpException, IOException {
							arg0.addHeader("apkToken", resp.getApkToken());
						}
					});
			
			if (checkPlayServices()) {
				gcm = GoogleCloudMessaging.getInstance(this);
				registerInBackground();
			}
			
			Intent intent = new Intent(this, IndexActivity.class);
			// Intent intent = new Intent(this, HomeAlarmsActivity.class);
			startActivity(intent);
			finish();
		} else {
			Editor e = this.getPreferences(Context.MODE_PRIVATE).edit();
			e.clear();
			e.commit();

			new AlertDialog.Builder(LoginActivity.this)
					.setIcon(R.drawable.ic_launcher)
					.setTitle("Atención")
					.setMessage("El usuario o contraseña son incorrectos")
					.setPositiveButton("Ok",
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int whichButton) {
								}
							}).show();
		}
		this.mAuthTask = null;
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		checkPlayServices();
	}
	
	private boolean checkPlayServices() {
		int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
		if (resultCode != ConnectionResult.SUCCESS) {
			if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
				GooglePlayServicesUtil.getErrorDialog(resultCode, this, PLAY_SERVICES_RESOLUTION_REQUEST).show();
			} else {
				Log.d(TAG, "This device is not supported - Google Play Services.");
				finish();
			}
			return false;
		}
		return true;
	}
	

	private SharedPreferences getGCMPreferences(Context context) {
		return getSharedPreferences(IndexActivity.class.getSimpleName(), Context.MODE_PRIVATE);
	}

	private void registerInBackground() {
		new AsyncTask() {
			@Override
			protected Object doInBackground(Object... params) {
				String msg = "";
				try {
					if (gcm == null) {
						gcm = GoogleCloudMessaging.getInstance(context);
					}
					regid = gcm.register(SENDER_ID);
					Log.d(TAG, "########################################");
					Log.d(TAG, "Current Device's Registration ID is: " + regid);
					
				} catch (IOException ex) {
					msg = "Error :" + ex.getMessage();
					ex.printStackTrace();
				}
				return null;
			}

			protected void onPostExecute(Object result) { // to do here
				Gson gson = new Gson();
				String json = gson.toJson(new RegisterAndroidBean(regid));
				new RESTClientTaskOpt<RESTResponse>(LoginActivity.this, HttpMethod.POST, getPostSaveObserver((LoJackActivity)LoginActivity.this), 
						RESTConstants.POST_REG_ID,null,json, RESTResponse.class, false, false).executeSerial((Void) null);
			};

		}.execute(null, null, null);
	}

	// findViewById(R.id.dropTarget).setOnDragListener(dragListener1);

	protected IRestClientObserver getPostSaveObserver(final LoJackActivity loJackActivity) {
		return new LoJackRestClientObserver(loJackActivity) {
			@Override
			public void sucess(IRestClientTask restClientTask) {
				RESTResponse response = ((RESTClientTaskOpt<RESTResponse>)restClientTask).getCastedResult();
			}
			@Override
			public void error(IRestClientTask task) {
			}
		};
		
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
