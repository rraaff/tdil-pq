package com.tdil.thalamus.android;

import java.util.ArrayList;

import android.app.Activity;
import android.content.res.Resources;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.TextView;
import android.widget.Toast;

import com.tdil.lojack.rl.R;
import com.tdil.thalamus.android.gui.AbstractListAdapter;
import com.tdil.thalamus.android.rest.client.HttpMethod;
import com.tdil.thalamus.android.rest.client.IRestClientObserver;
import com.tdil.thalamus.android.rest.client.IRestClientTask;
import com.tdil.thalamus.android.rest.client.RESTClientTaskOpt;
import com.tdil.thalamus.android.rest.client.RESTConstants;
import com.tdil.thalamus.android.rest.client.RestParams;
import com.tdil.thalamus.android.rest.model.NotificationBean;
import com.tdil.thalamus.android.rest.model.RESTResponse;
import com.tdil.thalamus.android.utils.Login;

public class NotificationsListAdapter extends AbstractListAdapter<NotificationBean, NotificationsViewHolder> {

	private Activity activity;
	private MyTouchListener onSwipeTouchListener;

	public NotificationsListAdapter(Activity a, ArrayList<NotificationBean> d, Resources resLocal) {
		super(a, d, resLocal);
		this.activity = a;
		onSwipeTouchListener = new MyTouchListener();
	}

	class MyTouchListener implements OnTouchListener {

		private int action_down_x = 0;
		private int action_up_x = 0;
		private int difference = 0;

		@Override
		public boolean onTouch(View v, MotionEvent event) {

			NotificationsViewHolder holder = (NotificationsViewHolder) v.getTag();
			int action = event.getAction();
//			int position = (Integer) v.getTag();

			switch (action) {
			case MotionEvent.ACTION_DOWN:
				action_down_x = (int) event.getX();
//				Log.d("action", "ACTION_DOWN - ");
				break;
			case MotionEvent.ACTION_MOVE:
//				Log.d("action", "ACTION_MOVE - ");
				action_up_x = (int) event.getX();
				difference = action_down_x - action_up_x;
				break;
			case MotionEvent.ACTION_UP:
//				Log.d("action", "ACTION_UP - ");
				calcuateDifference(holder);
				action_down_x = 0;
				action_up_x = 0;
				difference = 0;
				break;
			}
			return true;
		}
		private void calcuateDifference(final NotificationsViewHolder holder) {

			if (difference == 0) {
//						Toast.makeText(mContext, items.get(position).getList_item(), Toast.LENGTH_LONG).show();
			}
			if (difference > 75) {
//				"Right to Left - "
				dismiss(holder.bean);
			}
			if (difference < -75) {
//				"Left to Right - "
				dismiss(holder.bean);
			}
		}
	}
	

	@Override
	protected void fillViewHolder(NotificationsViewHolder holder, NotificationBean iter) {
		holder.bean = iter;
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
//		vi.setOnTouchListener(onSwipeTouchListener);
		NotificationsViewHolder holder = new NotificationsViewHolder();
		holder.notificationTitleTextView = (TextView) vi.findViewById(R.id.notificationTitleTextView);
		holder.notificationDescriptionTextView = (TextView) vi.findViewById(R.id.notificationDescriptionTextView);
		holder.notificationDismissImageButton = (View) vi.findViewById(R.id.notificationDismissImageButton);
		return holder;
	}

	@Override
	protected int getItemLayout() {
		return R.layout.notification_list_item;
	}

	public void dismiss(NotificationBean notificationbean) {
		new RESTClientTaskOpt<RESTResponse>(activity, HttpMethod.POST, getPostDismissObserver((LoJackActivity) activity, this,
				notificationbean), RESTConstants.POST_DISMISS_NOTIFICATION, new RestParams(RESTConstants.P_NOTIFICATION_ID,
				String.valueOf(notificationbean.getId())), null, RESTResponse.class).executeSerial((Void) null);
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
			NotificationBean notificationbean = this.notificationBean;
			dismiss(notificationbean);
		}
	}

	public static IRestClientObserver getPostDismissObserver(final LoJackActivity activity,
			final NotificationsListAdapter notificationsListAdapter, final NotificationBean notificationBean) {
		return new LoJackRestClientObserver(activity) {
			@Override
			public void sucess(IRestClientTask restClientTask) {
				RESTResponse response = ((RESTClientTaskOpt<RESTResponse>) restClientTask).getCastedResult();
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
