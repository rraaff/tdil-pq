package com.tdil.peugeotservice.android.rest.client;

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
import org.apache.http.protocol.HttpContext;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Build;
import android.util.Log;

import com.tdil.peugeotservice.android.utils.Login;


public class RESTClientTask extends AsyncTask<Void, Void, Boolean> implements IRestClientTask {

	private HttpMethod method;
	private WeakReference<Context> contextRef;
	private WeakReference<IRestClientObserver> observerRef;
	private WeakReference<ProgressDialog> progressDialogRef;
	private InputStream inputStream = null;
	private String result = "";
	private int statusCode;
	private String url;
	private String urlToExecute;
	private Map<String, String> urlParams;
	private String body;
	
	private boolean incomplete = false;
	
	public static DefaultHttpClient httpClient = new DefaultHttpClient();
	
	private static ExecutorService SERIAL_EXECUTOR = Executors.newFixedThreadPool(1);
	
	
	public RESTClientTask(Context context, HttpMethod method, IRestClientObserver observer, String url, RestParams restParams,
			String body) {
		this.contextRef = new WeakReference<Context>(context);
		this.method = method;
		this.observerRef = new WeakReference<IRestClientObserver>(observer);
		this.url = url;
		this.urlParams = restParams == null? null : restParams.getParams();
		this.body = body;
	}
	
	public static DefaultHttpClient get1HttpClient(final Context context) {
		DefaultHttpClient httpClient = new DefaultHttpClient();
		if (context != null) {
			httpClient.addRequestInterceptor(new HttpRequestInterceptor() {
				@Override
				public void process(HttpRequest arg0, HttpContext arg1)
						throws HttpException, IOException {
					arg0.addHeader("apkToken", Login.getLoggedUser(context).getApkToken());
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
		if (context != null) {
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
			HttpResponse httpResponse = httpClient.execute(httpPost);
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
			if (this.progressDialogRef != null) {
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
						observer.error(this);
					}
				} else {
					observer.error(this);
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
