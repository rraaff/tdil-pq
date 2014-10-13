package com.tdil.thalamus.android;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.AttributeSet;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.LayoutInflater.Factory;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.tdil.lojack.rl.R;
import com.tdil.thalamus.android.rest.client.HttpMethod;
import com.tdil.thalamus.android.rest.client.IRestClientObserver;
import com.tdil.thalamus.android.rest.client.IRestClientTask;
import com.tdil.thalamus.android.rest.client.RESTClientTaskOpt;
import com.tdil.thalamus.android.rest.client.RESTConstants;
import com.tdil.thalamus.android.rest.model.LoginResponse;
import com.tdil.thalamus.android.rest.model.NotificationBeanCollection;
import com.tdil.thalamus.android.utils.Login;

public abstract class LoJackActivity extends ActionBarActivity {

	private static Typeface normalFont;
	private static Typeface boldFont;
	private View actionBarLayout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Thread.setDefaultUncaughtExceptionHandler(new UnCaughtException(this));
	}

	protected void customizeActionBar() {
		customizeActionBar(true);
	}

	protected void customizeActionBar(boolean displayHomeAsUpEnabled) {
		ActionBar actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(displayHomeAsUpEnabled);
		actionBar.setDisplayShowCustomEnabled(true);
		actionBar.setDisplayShowTitleEnabled(false);
		// actionBar.setIcon(R.drawable.ic_ac_back_white);

		// this.getSupportActionBar().setTitle(ApplicationConfig.APP_NAME);
		// this.getSupportActionBar().setDisplayShowHomeEnabled(true);
		LayoutInflater inflator = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		actionBarLayout = inflator.inflate(R.layout.actionbar, null);
		if (mustUpdateMessages()) {
			updateMessages(actionBarLayout, this);
		} else {
			if (actionBarLayout != null) {
				View findViewById = (View) actionBarLayout.findViewById(R.id.messagesCountTextView);
				if (findViewById != null) {
					findViewById.setVisibility(View.GONE);
				}
			}
		}
		// setTypeface(this, actionBarLayout.findViewById(R.id.actionBarTitle));
		this.getSupportActionBar().setCustomView(actionBarLayout);
		/** START ALERTA */
		// AlertLogic.installLogic(this);
		/** END ALERTA */
		// getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
		// getSupportActionBar().setCustomView(R.layout.actionbar);
	}

	protected void customizeActionBar(String title, boolean displayHomeAsUpEnabled) {
		ActionBar actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(displayHomeAsUpEnabled);
		actionBar.setDisplayShowCustomEnabled(true);
		actionBar.setDisplayShowTitleEnabled(false);
		// actionBar.setIcon(R.drawable.ic_ac_back_white);

		// this.getSupportActionBar().setTitle(ApplicationConfig.APP_NAME);
		// this.getSupportActionBar().setDisplayShowHomeEnabled(true);
		LayoutInflater inflator = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		actionBarLayout = inflator.inflate(R.layout.actionbar, null);
		if (mustUpdateMessages()) {
			updateMessages(actionBarLayout, this);
		} else {
			if (actionBarLayout != null) {
				View findViewById = (View) actionBarLayout.findViewById(R.id.messagesCountTextView);
				if (findViewById != null) {
					findViewById.setVisibility(View.GONE);
				}
			}
		}

		TextView titleTextView = (TextView) actionBarLayout.findViewById(R.id.actionBarTitle);
		titleTextView.setText(title);
		// setTypeface(this, titleTextView);
		this.getSupportActionBar().setCustomView(actionBarLayout);
		/** START ALERTA */
		// AlertLogic.installLogic(this);
		/** END ALERTA */
		// getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
		// getSupportActionBar().setCustomView(R.layout.actionbar);
	}

	protected void updateMessages(final View v, final LoJackActivity activity) {
		TextView messagesCountTextView = (TextView) v.findViewById(R.id.messagesCountTextView);
		if (!Login.getLoggedUser(this).getLogged()) {
			messagesCountTextView.setVisibility(View.GONE);
			return;
		}
		int messageCount = Login.getLoggedUser(this).getMessagesCount();
		int messagePriority = Login.getLoggedUser(this).getMessagesPriority();
		if (messageCount == 0) {
			messagesCountTextView.setVisibility(View.GONE);
		} else {
			if (messageCount == 1) {
				messagesCountTextView.setText("1");
			} else {
				messagesCountTextView.setText(messageCount + "");
			}
			if (messagePriority == 0) {
				messagesCountTextView.setTextColor(getResources().getColor(R.color.actionBarGreen));
			} else {
				if (messagePriority == 1) {
					messagesCountTextView.setTextColor(getResources().getColor(R.color.actionBarYellow));
				} else {
					messagesCountTextView.setTextColor(getResources().getColor(R.color.actionBarRed));
				}
			}
			messagesCountTextView.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					new RESTClientTaskOpt<NotificationBeanCollection>(activity, HttpMethod.GET, getNotificationsObserver(activity),
							RESTConstants.GET_NOTIFICATIONS, null, null, NotificationBeanCollection.class).execute((Void) null);
				}
			});
		}
	}

	protected void updateMessagesOnBack(final View v) {
		if (v == null) {
			return;
		}
		TextView messagesCountTextView = (TextView) v.findViewById(R.id.messagesCountTextView);
		if (!Login.getLoggedUser(this).getLogged()) {
			messagesCountTextView.setVisibility(View.GONE);
			return;
		}
		int messageCount = Login.getLoggedUser(this).getMessagesCount();
		int messagePriority = Login.getLoggedUser(this).getMessagesPriority();
		if (messageCount == 0) {
			messagesCountTextView.setVisibility(View.GONE);
		} else {
			if (messageCount == 1) {
				messagesCountTextView.setText("1");
			} else {
				messagesCountTextView.setText(messageCount + "");
			}
			if (messagePriority == 0) {
				messagesCountTextView.setTextColor(getResources().getColor(R.color.actionBarGreen));
			} else {
				if (messagePriority == 1) {
					messagesCountTextView.setTextColor(getResources().getColor(R.color.actionBarYellow));
				} else {
					messagesCountTextView.setTextColor(getResources().getColor(R.color.actionBarRed));
				}
			}
		}
	}

	public static IRestClientObserver getNotificationsObserver(final LoJackActivity activity) {
		return new LoJackRestClientObserver(activity) {
			@Override
			public void sucess(IRestClientTask restClientTask) {
				NotificationBeanCollection response = ((RESTClientTaskOpt<NotificationBeanCollection>) restClientTask).getCastedResult();
				Intent intent = new Intent(activity.getBaseContext(), NotificationsActivity.class);
				intent.putExtra(NotificationsActivity.NOTIFICATIONS, response);
				activity.startActivity(intent);
			}
		};
	}

	public static void setTypeface(LoJackActivity context, int id) {
		View view = context.findViewById(id);
		if (view instanceof TextView) {
			((TextView) view).setTypeface(getNormalFont(context));
		}
		if (view instanceof EditText) {
			((EditText) view).setTypeface(getNormalFont(context));
		}
		if (view instanceof Button) {
			((Button) view).setTypeface(getNormalFont(context));
		}
		if (view instanceof CheckBox) {
			((CheckBox) view).setTypeface(getNormalFont(context));
		}

	}

	@Override
	public final boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		getMenuInflater().inflate(menuResourceId(), menu);
//		getLayoutInflater().setFactory(new Factory() {
//			@Override
//			public View onCreateView(String name, Context context, AttributeSet attrs) {
//				// if(name.equalsIgnoreCase("com.android.internal.view.menu.MenuItem"))
//				// {}
//				try {
//					LayoutInflater li = LayoutInflater.from(context);
//					final View view = li.createView(name, null, attrs);
//					new Handler().post(new Runnable() {
//						public void run() {
//							if (view instanceof TextView) {
//								TextView tView = (TextView) view;
//								LoJackLoggedActivity.setCustomTypeface(LoJackActivity.this, tView);
//							}
//						}
//					});
//					return view;
//				} catch (InflateException e) {
//					e.printStackTrace();
//				} catch (ClassNotFoundException e) {
//					e.printStackTrace();
//				}
//				return null;
//			}
//		});
		MenuItem menuItem = menu.findItem(R.id.menu_action_user);
		if (menuItem != null) {
			LoginResponse loggedUser = Login.getLoggedUser(this);
			if (loggedUser != null && loggedUser.getLogged()) {
				menuItem.setTitle(loggedUser.getName());
			} else {
				menuItem.setTitle("-");
			}
		}
		return true;
	}

	public abstract int menuResourceId();

	@Override
	public final boolean onOptionsItemSelected(MenuItem item) {
		return MenuLogic.handleOnOptionsItemSelected(this, item);
	}

	@Override
	protected void onResume() {
		super.onResume();
		if (mustUpdateMessages()) {
			updateMessagesOnBack(actionBarLayout);
		} else {
			if (actionBarLayout != null) {
				View findViewById = (View) actionBarLayout.findViewById(R.id.messagesCountTextView);
				if (findViewById != null) {
					findViewById.setVisibility(View.GONE);
				}
			}
		}
	}

	protected boolean mustUpdateMessages() {
		return true;
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
	}

	public static void setTypeface(LoJackActivity context, View view) {
		if (view instanceof TextView) {
			((TextView) view).setTypeface(getNormalFont(context));
		}
		if (view instanceof EditText) {
			((EditText) view).setTypeface(getNormalFont(context));
		}
		if (view instanceof Button) {
			((Button) view).setTypeface(getNormalFont(context));
		}
		if (view instanceof CheckBox) {
			((CheckBox) view).setTypeface(getNormalFont(context));
		}
	}

	public static void setCustomTypeface(Context context, TextView view) {
		view.setTypeface(getNormalFont(context));
	}

	public static void setCustomTypeface(Context context, EditText view) {
		view.setTypeface(getNormalFont(context));
	}

	public static void setCustomTypeface(Context context, Button view) {
		view.setTypeface(getNormalFont(context));
	}

	public static void setCustomTypeface(Context context, CheckBox view) {
		view.setTypeface(getNormalFont(context));
	}

	public static Typeface getNormalFont(Context context) {
		if (normalFont == null) {
			normalFont = Typeface.createFromAsset(context.getAssets(), "blackbooktwo.ttf");
		}
		return normalFont;
	}

	public static Typeface getLightFont(Context context) {
		if (boldFont == null) {
			boldFont = Typeface.createFromAsset(context.getAssets(), "blackbooktwo.ttf");
		}
		return boldFont;
	}

	public int getActionBarIconImageResource() {
		return R.drawable.ic_launcher;
	}

	public View getActionBarLayout() {
		return actionBarLayout;
	}

	public void setActionBarLayout(View actionBarLayout) {
		this.actionBarLayout = actionBarLayout;
	}

}
