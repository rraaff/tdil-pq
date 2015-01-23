package com.tdil.thalamus.android.push;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.tdil.lojack.rl.R;
import com.tdil.thalamus.android.NotificationsActivity;
import com.tdil.thalamus.android.rest.client.HttpMethod;
import com.tdil.thalamus.android.rest.client.IRestClientObserver;
import com.tdil.thalamus.android.rest.client.RESTClientTaskOpt;
import com.tdil.thalamus.android.rest.client.RESTConstants;
import com.tdil.thalamus.android.rest.client.RestParams;
import com.tdil.thalamus.android.rest.model.RESTResponse;

public class GcmIntentService extends IntentService {
	public static final int NOTIFICATION_ID = 1;
	private NotificationManager mNotificationManager;
	private final static String TAG = "GcmIntentService";

	public GcmIntentService() {
		super("GcmIntentService");
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		Bundle extras = intent.getExtras();

		GoogleCloudMessaging gcm = GoogleCloudMessaging.getInstance(this);
		String messageType = gcm.getMessageType(intent);
		if (!extras.isEmpty()) {
			if (extras.containsKey("type") && extras.containsKey("level") && extras.containsKey("title") && extras.containsKey("message")) {
				int type = Integer.valueOf(extras.getString("type"));
				int level = Integer.valueOf(extras.getString("level"));
				String title = extras.getString("title");
				String message = extras.getString("message");
				if (extras.containsKey("nId")) {
					int nId = Integer.valueOf(extras.getString("nId"));
					new RESTClientTaskOpt<RESTResponse>(this, HttpMethod.POST, IRestClientObserver.dummy, RESTConstants.POST_NOTIFICATION_RECEIVED, 
							new RestParams(RESTConstants.P_NOTIFICATION_ID, nId),null,RESTResponse.class, false, false)
								.executeSerial((Void) null);
				}
				Log.d(TAG, "Notification Data Json :" + message);
				if (GoogleCloudMessaging.MESSAGE_TYPE_SEND_ERROR.equals(messageType)) {
					sendNotification(type, level, title, "Send error: " + extras.toString());
				} else if (GoogleCloudMessaging.MESSAGE_TYPE_DELETED.equals(messageType)) {
					sendNotification(type, level, title, "Deleted messages on server: " + extras.toString()); // If
				} else if (GoogleCloudMessaging.MESSAGE_TYPE_MESSAGE.equals(messageType)) {
					sendNotification(type, level, title, message);
				}
			} // Release the wake lock provided by the WakefulBroadcastReceiver.
			GcmBroadcastReceiver.completeWakefulIntent(intent);
		} // Put the message into a notification and post it.
	}

	// This is just one simple example of what you might choose to do with
	// a GCM message.
	private void sendNotification(int type, int level, String title, String msg) {
		mNotificationManager = (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);
		PendingIntent contentIntent = PendingIntent.getActivity(this, 0, new Intent(this, NotificationsActivity.class), 0);

		/* Icono para notificaciones genéricas */
		int icon = R.drawable.ic_stat_loapp;
		
		/* Icono para notificaciones de CAR */
		if (type == NotificationType.CAR) {
			icon = R.drawable.ic_stat_locar;
		}
		
		/* Icono para notificaciones de HOME */
		if (type == NotificationType.HOME) {
			icon = R.drawable.ic_stat_lohome;
		}
		
		/* Icono para notificaciones de PET */
		if (type == NotificationType.PET) {
			icon = R.drawable.ic_stat_lopet;
		}
		
		NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this).setSmallIcon(icon).setContentTitle(title)
				.setStyle(new NotificationCompat.BigTextStyle().bigText(msg)).setContentText(msg)
				.setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE);

		mBuilder.setContentIntent(contentIntent);
		mNotificationManager.notify(NOTIFICATION_ID, mBuilder.build());
	}
}