package com.tdil.thalamus.android.rest.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.protocol.HttpContext;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;

import com.tdil.thalamus.android.rest.model.LoginResponse;
import com.tdil.thalamus.android.utils.Login;



public class RESTClientTask extends AsyncTask<Void, Void, Boolean> implements IRestClientTask {

	protected HttpMethod method;
	protected WeakReference<Context> contextRef;
	protected WeakReference<IRestClientObserver> observerRef;
	protected Context contextStrong;
	protected IRestClientObserver observerStrong;
	
	protected WeakReference<ProgressDialog> progressDialogRef;
	protected InputStream inputStream = null;
	protected String result = "";
	protected int statusCode;
	protected String url;
	protected String urlToExecute;
	protected Map<String, String> urlParams;
	protected String body;
	
	protected boolean incomplete = false;
	protected boolean showProgress = true;
	protected boolean showError = true;
	
	private static DefaultHttpClient httpClient = null;
	
	private static ExecutorService SERIAL_EXECUTOR = Executors.newFixedThreadPool(1);
	
	public static void recreateClient() {
		httpClient = null;
	}
	
	public RESTClientTask(Context context, HttpMethod method, IRestClientObserver observer, String url, RestParams restParams,
			String body) {
		this.contextRef = new WeakReference<Context>(context);
		this.contextStrong = context;
		this.method = method;
		this.observerRef = new WeakReference<IRestClientObserver>(observer);
		this.observerStrong = observer;
		this.url = url;
		this.urlParams = restParams == null? null : restParams.getParams();
		this.body = body;
	}
	
	public RESTClientTask(Context context, HttpMethod method, IRestClientObserver observer, String url, RestParams restParams,
			String body, boolean showProgress, boolean showError) {
		this.contextRef = new WeakReference<Context>(context);
		this.contextStrong = context;
		this.method = method;
		this.observerRef = new WeakReference<IRestClientObserver>(observer);
		this.observerStrong = observer;
		this.url = url;
		this.urlParams = restParams == null? null : restParams.getParams();
		this.body = body;
		this.showProgress = showProgress;
		this.showError = showError;
	}
	
	public static DefaultHttpClient getHttpClient(final Context context) {
		if (httpClient != null) {
			return httpClient;
		}
		httpClient = new DefaultHttpClient();
		httpClient.getParams().setParameter(CoreProtocolPNames.USER_AGENT,System.getProperty("http.agent"));
		if (context != null) {
			httpClient.addRequestInterceptor(new HttpRequestInterceptor() {
				@Override
				public void process(HttpRequest arg0, HttpContext arg1)
						throws HttpException, IOException {
					LoginResponse loggedUser = Login.getLoggedUser(context);
					if (loggedUser != null && loggedUser.getLogged()) {
						arg0.addHeader("apkToken", loggedUser.getApkToken());
					}
				}
			});
		}
		return httpClient;
	}

	protected void onPreExecute() {
		urlToExecute = RESTConstants.REST_URL + this.url;
		if (this.urlParams != null) {
			for (Map.Entry<String, String> replacements : this.urlParams.entrySet()) {
				if (replacements.getValue() != null) {
					urlToExecute = urlToExecute.replace(replacements.getKey(), replacements.getValue());
				}
			}
		}
		if (urlToExecute.indexOf("{") != -1) {
			incomplete = true;
		}
		if (incomplete) {
			return;
		}
		Context context = contextRef.get();
		if (context != null && showProgress) {
			ProgressDialog progressDialog = new ProgressDialog(context);
			progressDialog.setMessage("Por favor, espere...");
			progressDialog.show();
			progressDialogRef = new WeakReference<ProgressDialog>(progressDialog);
			/*progressDialog.setOnCancelListener(new OnCancelListener() {
				public void onCancel(DialogInterface arg0) {
					RESTClientTask.this.cancel(true);
				}
			});*/
		}
	}

	@Override
	protected Boolean doInBackground(Void... params) {
		if (incomplete) {
			return Boolean.FALSE;
		}
		Context context = contextRef.get();
		try {
			// Set up HTTP post
			HttpRequestBase httpPost = this.method.create(urlToExecute);
			if (this.body != null) {
				((HttpEntityEnclosingRequestBase)httpPost).setEntity(new ByteArrayEntity(
					    this.body.getBytes("UTF8")));
			}
			HttpResponse httpResponse = getHttpClient(context).execute(httpPost);
			HttpEntity httpEntity = httpResponse.getEntity();
			// Read content & Log
			inputStream = httpEntity.getContent();
			statusCode = httpResponse.getStatusLine().getStatusCode();
			// Convert response to string using String Builder
			try {
				BufferedReader bReader = new BufferedReader(new InputStreamReader(
						inputStream), 8);
				StringBuilder sBuilder = new StringBuilder();

				String line = null;
				while ((line = bReader.readLine()) != null) {
					sBuilder.append(line + "\n");
				}
				inputStream.close();
				result = sBuilder.toString();
				JSONObject jObject;
				try {
					jObject = new JSONObject(result);
					// if por ...
					return true;
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return false;
				}
			} catch (Exception e) {
				Log.e("StringBuilding & BufferedReader", "Error converting result "
						+ e.toString());
				return false;
			}
			
		} catch (UnsupportedEncodingException e1) {
			Log.e("UnsupportedEncodingException", e1.toString());
			e1.printStackTrace();
			return false;
		} catch (ClientProtocolException e2) {
			Log.e("ClientProtocolException", e2.toString());
			e2.printStackTrace();
			return false;
		} catch (IllegalStateException e3) {
			Log.e("IllegalStateException", e3.toString());
			e3.printStackTrace();
			return false;
		} catch (IOException e4) {
			Log.e("IOException", e4.toString());
			e4.printStackTrace();
			return false;
		}
		
	}
	

	@Override
	protected void onPostExecute(final Boolean success) {
		if (incomplete) {
			return;
		}
		try {
			if (this.progressDialogRef != null && showProgress) {
				ProgressDialog progressDialog = progressDialogRef.get();
				if (progressDialog != null) {
					progressDialog.dismiss();
				}
			}
			IRestClientObserver observer = this.observerRef.get();
			if (observer != null) {
				if(success) {
					try {
						observer.sucess(this);
					} catch (Exception e) {
						e.printStackTrace();
						if (showError) {
							observer.error(this);
						}
					}
				} else {
					if (showError) {
						observer.error(this);
					}
				}
			}
		} catch (Exception e) {
			
		}
	}
	
	@Override
	protected void onCancelled() {
		// TODO ver context.showProgress(false);
	}

	/* (non-Javadoc)
	 * @see com.tdil.peugeotservice.android.rest.client.IRestClientTask#getResult()
	 */
	@Override
	public String getResult() {
		return result;
	}

	/* (non-Javadoc)
	 * @see com.tdil.peugeotservice.android.rest.client.IRestClientTask#getStatusCode()
	 */
	@Override
	public int getStatusCode() {
		return statusCode;
	}

	/* (non-Javadoc)
	 * @see com.tdil.peugeotservice.android.rest.client.IRestClientTask#setStatusCode(int)
	 */
	@Override
	public void setStatusCode(int status) {
		this.statusCode = status;
	}

	public void executeSerial(Void params) {
		this.executeOnExecutor(SERIAL_EXECUTOR, params);
	}

}
