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
import com.tdil.thalamus.android.IndexActivity;

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
				int type = extras.getInt("type");
				int level = extras.getInt("level");
				String title = extras.getString("title");
				String message = extras.getString("message");
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
		PendingIntent contentIntent = PendingIntent.getActivity(this, 0, new Intent(this, IndexActivity.class), 0);

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
		NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this).setSmallIcon(icon).setContentTitle(title)
				.setStyle(new NotificationCompat.BigTextStyle().bigText(msg)).setContentText(msg)
				.setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE);

		mBuilder.setContentIntent(contentIntent);
		mNotificationManager.notify(NOTIFICATION_ID, mBuilder.build());
	}
}