package com.tdil.thalamus.android;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.tdil.lojack.rl.R;
import com.tdil.thalamus.android.gui.AbstractListAdapter;
import com.tdil.thalamus.android.rest.client.HttpMethod;
import com.tdil.thalamus.android.rest.client.IRestClientObserver;
import com.tdil.thalamus.android.rest.client.IRestClientTask;
import com.tdil.thalamus.android.rest.client.RESTClientTaskOpt;
import com.tdil.thalamus.android.rest.client.RESTConstants;
import com.tdil.thalamus.android.rest.client.RestParams;
import com.tdil.thalamus.android.rest.model.NotificationBean;
import com.tdil.thalamus.android.rest.model.NotificationBeanCollection;
import com.tdil.thalamus.android.rest.model.RESTResponse;
import com.tdil.thalamus.android.utils.Login;

public class NotificationsListAdapter extends AbstractListAdapter<NotificationBean, NotificationsViewHolder> {

	private Activity activity;
	
	public NotificationsListAdapter(Activity a,
			ArrayList<NotificationBean> d, Resources resLocal) {
		super(a, d, resLocal);
		this.activity = a;
	}

	@Override
	protected void fillViewHolder(NotificationsViewHolder holder,
			NotificationBean iter) {
		holder.notificationTitleTextView.setText(iter.getTitle());
		holder.notificationDescriptionTextView.setText(iter.getMessage());
		if (0 == iter.getNotificationlevel()) {
			holder.notificationTitleTextView.setTextColor(activity.getResources().getColor(R.color.actionBarGreen));
		} else {
			if (1 == iter.getNotificationlevel()) {
				holder.notificationTitleTextView.setTextColor(activity.getResources().getColor(R.color.actionBarYellow));
			} else {
				holder.notificationTitleTextView.setTextColor(activity.getResources().getColor(R.color.actionBarRed));
			}
		}
		holder.notificationDismissImageButton.setOnClickListener(new DimisssNotification(activity, iter, this));
	}

	@Override
	protected NotificationsViewHolder createViewHolder(View vi) {
		NotificationsViewHolder holder = new NotificationsViewHolder();
		holder.notificationTitleTextView = (TextView)vi.findViewById(R.id.notificationTitleTextView);
		holder.notificationDescriptionTextView = (TextView)vi.findViewById(R.id.notificationDescriptionTextView);
		holder.notificationDismissImageButton = (View)vi.findViewById(R.id.notificationDismissImageButton);
		return holder;
	}

	@Override
	protected int getItemLayout() {
		return R.layout.notification_list_item;
	}

	private class DimisssNotification implements OnClickListener {
		
		private Activity activity;
		private NotificationBean notificationBean;
		private NotificationsListAdapter adapter;
		
		DimisssNotification(Activity activity, NotificationBean notificationBean, NotificationsListAdapter adapter) {
			this.activity = activity;
			this.notificationBean = notificationBean;
			this.adapter = adapter;
		}

		@Override
		public void onClick(View arg0) {
			new RESTClientTaskOpt<RESTResponse>(activity, HttpMethod.POST, getPostDismissObserver((LoJackActivity)activity, this.adapter, this.notificationBean), RESTConstants.POST_DISMISS_NOTIFICATION, new RestParams(
					RESTConstants.P_NOTIFICATION_ID, String.valueOf(notificationBean.getId())),null, RESTResponse.class).execute((Void) null);
		}
	}

	public static IRestClientObserver getPostDismissObserver(final LoJackActivity activity, final NotificationsListAdapter notificationsListAdapter, final NotificationBean notificationBean) {
		return new LoJackRestClientObserver(activity) {
			@Override
			public void sucess(IRestClientTask restClientTask) {
				RESTResponse response = ((RESTClientTaskOpt<RESTResponse>)restClientTask).getCastedResult();
				if (response.getOk()) {
					Login.getLoggedUser(activity).setMessagesCount(Login.getLoggedUser(activity).getMessagesCount() - 1);
					activity.updateMessagesOnBack(activity.getActionBarLayout());
					activity.runOnUiThread(new Runnable() {
				        @Override
				        public void run() {
				             notificationsListAdapter.remove(notificationBean);
				             notificationsListAdapter.notifyDataSetChanged();
				             if (notificationsListAdapter.getData().size() == 0) {
				            	 activity.finish();
				             }
				        }
				    });
				} else {
					error(restClientTask);
				}
			}
		};
	}
}
