package com.tdil.thalamus.android;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

import com.tdil.lojack.rl.R;
import com.tdil.thalamus.android.car.CarsDialogs;
import com.tdil.thalamus.android.header.logic.HeaderLogic;
import com.tdil.thalamus.android.utils.Login;

@SuppressLint("ResourceAsColor")
public class IndexActivityPreAndroid3 extends LoJackLoggedActivity {

	public static boolean isUpdate;

	private static final String HOME = "HOME";
	private static final String PARKINGS = "PARKINGS";
	private static final String TV = "TV";
	private static final String PETS = "PETS";
	private static final String PREVENT = "CAR";
	private static final String PARKED_MODE = "PARKED_MODE";

	private static final String CLUB_LJ = "CLUB_LJ";

	/**
	 * The default email to populate the email field with.
	 */
	// Values for email and password at the time of the login attempt.

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		// this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
		// 0);
		setContentView(R.layout.activity_index);
		customizeActionBar(Login.getLoggedUser(this).getName(), true);

		// customizeActionBar(Login.getLoggedUser(this).getName(), true);

		// ActionBarLogic.installActionBarLogic(this, false);

		// BitmapFactory.Options options = new BitmapFactory.Options();
		// options.inSampleSize = 4;

//		btnFooterPrevent.setOnTouchListener(new StartDragOnTouchListener(this, PREVENT, drawableToBitmap(getResources().getDrawable(R.drawable.ic_cars_r2))));

//		findViewById(R.id.btnFooterPets).setOnTouchListener(new StartDragOnTouchListener(this, PETS, drawableToBitmap(getResources().getDrawable(R.drawable.ic_pets_r2))));
//		findViewById(R.id.btnFooterParkings).setOnTouchListener(new StartDragOnTouchListener(this, PARKINGS, drawableToBitmap(getResources().getDrawable(R.drawable.ic_places_r2))));
//		findViewById(R.id.btnFooterEstacioname).setOnTouchListener(new StartDragOnTouchListener(this, PARKED_MODE, drawableToBitmap(getResources().getDrawable(R.drawable.ic_modoe_r2))));
//		findViewById(R.id.btnFooterHome).setOnTouchListener(new StartDragOnTouchListener(this, HOME, drawableToBitmap(getResources().getDrawable(R.drawable.ic_home_r2))));
//		findViewById(R.id.btnClubLJ).setOnTouchListener(new StartDragOnTouchListener(this, CLUB_LJ, drawableToBitmap(getResources().getDrawable(R.drawable.ic_club_r2))));
//		
//		
//		btnFooterPrevent.setOnDragListener(new ButtonDragListener(PREVENT));
//		findViewById(R.id.btnFooterPets).setOnDragListener(new ButtonDragListener(PETS));
//		findViewById(R.id.btnFooterParkings).setOnDragListener(new ButtonDragListener(PARKINGS));
//		findViewById(R.id.btnFooterEstacioname).setOnDragListener(new ButtonDragListener(PARKED_MODE));
//		findViewById(R.id.btnFooterHome).setOnDragListener(new ButtonDragListener(HOME));
//		findViewById(R.id.btnClubLJ).setOnDragListener(new ButtonDragListener(CLUB_LJ));
//		
//		findViewById(R.id.dropTarget).setOnDragListener(dragListener1);
		
		findViewById(R.id.btnFooterPrevent).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				HeaderLogic.handlePreventAccess(IndexActivityPreAndroid3.this);
			}
		});
		findViewById(R.id.btnFooterPets).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				HeaderLogic.handlePetsAccess(IndexActivityPreAndroid3.this);
			}
		});
		findViewById(R.id.btnFooterParkings).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				HeaderLogic.handleParkingsAccess(IndexActivityPreAndroid3.this);
			}
		});
		// findViewById(R.id.btnFooterTV).setOnClickListener(new
		// OnClickListener() {
		// @Override
		// public void onClick(View v) {
		// FooterLogic.handleTvAccess(IndexActivity.this);
		// }
		// });
		findViewById(R.id.btnFooterHome).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				HeaderLogic.handleHomeAccess(IndexActivityPreAndroid3.this, false);
			}
		});
		findViewById(R.id.btnClubLJ).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				HeaderLogic.handleClubLoJackAccess(IndexActivityPreAndroid3.this);
			}
		});

		findViewById(R.id.btnFooterEstacioname).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				HeaderLogic.handleParkedModeAccess(IndexActivityPreAndroid3.this);
			}
		});

		/*
		 * Button button = (Button)findViewById(R.id.vluCount); int
		 * vluMessagesCount = Login.getLoggedUser(this).getVluMessages(); if
		 * (vluMessagesCount > 0) {
		 * button.setText(String.valueOf(vluMessagesCount));
		 * button.setOnClickListener(new ViewVLUMessagesListener(this,
		 * vluMessagesCount)); } else { if
		 * (Login.getLoggedUser(this).getVluClient()) {
		 * button.setBackgroundResource(R.drawable.badge_ok); //
		 * button.setOnClickListener(new OnClickListener() { // @Override //
		 * public void onClick(View v) { // Context context =
		 * getApplicationContext(); // CharSequence text =
		 * "TU EQUIPO LOJACK FUNCIONA CORRECTAMENTE"; // int duration =
		 * Toast.LENGTH_SHORT; // Toast toast = Toast.makeText(context, text,
		 * duration); // toast.show(); // } // }); button.setOnClickListener(new
		 * ViewVLUMessagesListener(this, vluMessagesCount)); } else {
		 * button.setVisibility(View.GONE); } }
		 */
	}
	
	
	public static class ViewVLUMessagesListener implements OnClickListener {
		private LoJackActivity activity;
		private int vluMessagesCount;

		ViewVLUMessagesListener(LoJackActivity activity, int vluMessagesCount) {
			this.activity = activity;
			this.vluMessagesCount = vluMessagesCount;
		}

		@Override
		public void onClick(View arg0) {
			CarsDialogs.goToVLUMessages(activity);
		}
	}

	// @Override
	// protected void onStart() {
	// // TODO Auto-generated method stub
	// super.onStart();
	// int []location = new int[2];
	// Button button = (Button)findViewById(R.id.btnFooterPrevent);
	// button.getLocationInWindow(location);
	// System.out.println(location);
	//
	// Button msg = (Button)findViewById(R.id.vluCount);
	// msg.setX(location[0]);
	// msg.setY(location[1]);
	//
	// AbsoluteLayout.LayoutParams OBJ = new
	// AbsoluteLayout.LayoutParams(35,35,location[0],location[1]);
	// msg.setLayoutParams(OBJ);
	// }



	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		// TODO Auto-generated method stub
		super.onConfigurationChanged(newConfig);
	}

	public Toast textView(View findViewById) {
		// TODO Auto-generated method stub
		return null;
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
