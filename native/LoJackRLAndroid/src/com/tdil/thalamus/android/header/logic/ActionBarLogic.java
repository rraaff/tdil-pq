package com.tdil.thalamus.android.header.logic;

import android.app.Activity;
import android.support.v7.internal.view.menu.MenuBuilder;
import android.support.v7.widget.PopupMenu;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.tdil.lojack.rl.R;
import com.tdil.thalamus.android.LoJackActivity;
import com.tdil.thalamus.android.utils.Login;

public class ActionBarLogic {

	public static void installActionBarLogic(final LoJackActivity activity, boolean backEnabled) {
		
		ImageView actionBarBackImage = (ImageView)activity.findViewById(R.id.actionBarBackImage);
		if (backEnabled) {
			actionBarBackImage.setOnClickListener(new BackListener(activity));
		}
		
		ImageView actionBarIconImage = (ImageView)activity.findViewById(R.id.actionBarIconImage);
		actionBarIconImage.setImageResource(activity.getActionBarIconImageResource());
		
		ImageView actionBarMessagesImage = (ImageView)activity.findViewById(R.id.actionBarMessagesImage);
		
		TextView actionBarMessagesCountTextView = (TextView)activity.findViewById(R.id.actionBarMessagesCountTextView);
		actionBarMessagesCountTextView.setText(String.valueOf(Login.getLoggedUser(activity).getMessagesCount()));
		
		TextView actionBarTitle = (TextView)activity.findViewById(R.id.actionBarTitle);
		
		final ImageView actionBarMenuImage = (ImageView)activity.findViewById(R.id.actionBarMenuImage);
		actionBarMenuImage.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				PopupMenu menu = new PopupMenu(activity, actionBarMenuImage);
			    menu.inflate(R.menu.menu_without_apps);
			    menu.show();
//				android.support.v7.widget.PopupMenu p  = new android.support.v7.widget.PopupMenu(activity, null);
//				Menu menu = p.getMenu();
//				activity.getMenuInflater().inflate(R.menu.menu_without_apps, menu);
				
			}
		});
		
		
	}

	static class BackListener implements View.OnClickListener {
		
		private Activity activity;
		
		public BackListener(Activity activity) {
			super();
			this.activity = activity;
		}

		@Override
		public void onClick(View v) {
			activity.finish();
		}
	}
	
}
