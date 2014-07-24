package com.tdil.thalamus.android;


import com.tdil.lojack.rl.R;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class LoJackActivity extends ActionBarActivity {

	private static Typeface normalFont;
	private static Typeface boldFont;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}
	
	protected void customizeActionBar() {
		customizeActionBar(true);
	}
	
	protected void customizeActionBar(boolean displayHomeAsUpEnabled) {
			ActionBar actionBar = getSupportActionBar();
			actionBar.setDisplayHomeAsUpEnabled(displayHomeAsUpEnabled);
			actionBar.setDisplayShowCustomEnabled(true);
			actionBar.setDisplayShowTitleEnabled(false);
	//		actionBar.setIcon(R.drawable.ic_ac_back_white);
			
	//		this.getSupportActionBar().setTitle(ApplicationConfig.APP_NAME);
	//		this.getSupportActionBar().setDisplayShowHomeEnabled(true);
			LayoutInflater inflator = (LayoutInflater) this .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View v = inflator.inflate(R.layout.actionbar, null);
			setTypeface(this, v.findViewById(R.id.actionBarTitle)); 
			this.getSupportActionBar().setCustomView(v);
			/** START ALERTA */
//			AlertLogic.installLogic(this);
			/** END ALERTA */
	//		getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM); 
	//		getSupportActionBar().setCustomView(R.layout.actionbar);
		}
	
	protected void customizeActionBar(String title, boolean displayHomeAsUpEnabled) {
		ActionBar actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(displayHomeAsUpEnabled);
		actionBar.setDisplayShowCustomEnabled(true);
		actionBar.setDisplayShowTitleEnabled(false);
//		actionBar.setIcon(R.drawable.ic_ac_back_white);
		
//		this.getSupportActionBar().setTitle(ApplicationConfig.APP_NAME);
//		this.getSupportActionBar().setDisplayShowHomeEnabled(true);
		LayoutInflater inflator = (LayoutInflater) this .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View v = inflator.inflate(R.layout.actionbar, null);
		TextView titleTextView = (TextView)v.findViewById(R.id.actionBarTitle);
		titleTextView.setText(title);
		setTypeface(this, titleTextView); 
		this.getSupportActionBar().setCustomView(v);
		/** START ALERTA */
//		AlertLogic.installLogic(this);
		/** END ALERTA */
//		getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM); 
//		getSupportActionBar().setCustomView(R.layout.actionbar);
	}
	
	

	public static void setTypeface(LoJackActivity context, int id) {
		View view = context.findViewById(id);
		if (view instanceof TextView) {
			((TextView)view).setTypeface(getNormalFont(context));
		}
		if (view instanceof EditText) {
			((EditText)view).setTypeface(getNormalFont(context));
		}
		if (view instanceof Button) {
			((Button)view).setTypeface(getNormalFont(context));
		}
		if (view instanceof CheckBox) {
			((CheckBox)view).setTypeface(getNormalFont(context));
		}
		
    }
	
	@Override
	public void onBackPressed() {
		super.onBackPressed();
	}
	
	public static void setTypeface(LoJackActivity context, View view) {
		if (view instanceof TextView) {
			((TextView)view).setTypeface(getNormalFont(context));
		}
		if (view instanceof EditText) {
			((EditText)view).setTypeface(getNormalFont(context));
		}
		if (view instanceof Button) {
			((Button)view).setTypeface(getNormalFont(context));
		}
		if (view instanceof CheckBox) {
			((CheckBox)view).setTypeface(getNormalFont(context));
		}
	}
	
	public static Typeface getNormalFont(Context context) {
        if(normalFont == null) {
            normalFont = Typeface.createFromAsset(context.getAssets(),"Peugeot_Normal_v2.ttf");
        }
        return normalFont;
    }
	
	public static Typeface getLightFont(Context context) {
        if(boldFont == null) {
            boldFont = Typeface.createFromAsset(context.getAssets(),"Peugeot_Light_v2.ttf");
        }
        return boldFont;
    }

}
