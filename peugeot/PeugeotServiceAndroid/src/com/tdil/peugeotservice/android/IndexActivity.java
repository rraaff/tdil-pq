package com.tdil.peugeotservice.android;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.Toast;

import com.tdil.peugeotservice.R;
import com.tdil.peugeotservice.android.utils.Login;

@SuppressLint("ResourceAsColor")
public class IndexActivity extends PeugeotActivity {

	
	/**
	 * The default email to populate the email field with.
	 */
	// Values for email and password at the time of the login attempt.

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Thread.setDefaultUncaughtExceptionHandler(new UnCaughtException(this));
		setContentView(R.layout.activity_index);
		setTypeface(this, R.id.sendAlertButton);

		customizeActionBar();
		
		findViewById(R.id.btnFooterPrevent).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				FooterLogic.handlePreventAccess(IndexActivity.this);
//				IndexActivity.this.startActivity(new Intent(IndexActivity.this, com.tdil.peugeotservice.android.car.ActivityCars.class));
			}
		});
		
		findViewById(R.id.btnFooterParkings).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				FooterLogic.handleParkingsAccess(IndexActivity.this);
			}
		});
		
		findViewById(R.id.btnFooterServices).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				IndexActivity.this.startActivity(new Intent(IndexActivity.this, ServicesDashboardActivity.class));
			}
		});
		
		findViewById(R.id.btnFooterSecureZone).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				FooterLogic.handleSecureZoneAccess(IndexActivity.this);
			}
		});
		
		findViewById(R.id.btnFooterSpeed).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				FooterLogic.handleSpeedAccess(IndexActivity.this);
			}
		});
		
		findViewById(R.id.btnFooterHistoric).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				FooterLogic.handleHistoricAccess(IndexActivity.this);
			}
		});
		
		new com.tdil.peugeotservice.android.utils.DownloadImageTask((ImageView)findViewById(R.id.gr_userIcon))
		.execute(ApplicationConfig.URL_WEBSITE
				+ Login.getLoggedUser(IndexActivity.this).getAvatar());
		
		if (Login.getLoggedUser(this).getMustChangePassword()) {
			this.startActivity(new Intent(this, ChangePasswordActivity.class));
			Login.getLoggedUser(this).setMustChangePassword(false);
		} else {
			if (Login.getLoggedUser(this).getMustCompleteEmergencyData()) {
				if (!Login.getRedirectedToEmergency()) {
					Login.setRedirectedToEmergency(true);
					this.startActivity(new Intent(this, UpdateEmergencyConfigActivity.class));
				}
			}
		}
		/* EJEMPLO DE ANIMACION findViewById(R.id.btnFooterServices).setOnTouchListener(new View.OnTouchListener() {
			
			@Override
			public boolean onTouch(View arg0, MotionEvent arg1) {
				Animation rotate = AnimationUtils.loadAnimation(IndexActivity.this, R.anim.rotate_picture);
				IndexActivity.this.findViewById(R.id.gr_iso).startAnimation(rotate);
				return true;
			}
		});*/
		
		
//		findViewById(R.id.btnFooterPets).setOnClickListener(new OnClickListener() {
//			@Override
//			public void onClick(View arg0) {
//				FooterLogic.handlePeugeotAccess(IndexActivity.this);
//			}
//		});
//		findViewById(R.id.btnFooterHome).setOnClickListener(new OnClickListener() {
//			@Override
//			public void onClick(View arg0) {
//				FooterLogic.handleHomeAccess(IndexActivity.this, false);
//			}
//		});

	}


	
	
	
//	@Override
//	protected void onStart() {
//		// TODO Auto-generated method stub
//		super.onStart();
//		int []location = new int[2];
//		Button button = (Button)findViewById(R.id.btnFooterPrevent);
//		button.getLocationInWindow(location);
//		System.out.println(location);
//		
//		Button msg = (Button)findViewById(R.id.vluCount);
//		msg.setX(location[0]);
//		msg.setY(location[1]);
//		
//		AbsoluteLayout.LayoutParams OBJ = new AbsoluteLayout.LayoutParams(35,35,location[0],location[1]);
//		msg.setLayoutParams(OBJ);
//	}



	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		// TODO Auto-generated method stub
		super.onConfigurationChanged(newConfig);
	}

	public Toast textView(View findViewById) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		getMenuInflater().inflate(R.menu.menu_full, menu);
		return true;
	}
	/*
	public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//
//            case R.id.action_settings:
            Dialog dialog = new Dialog(this);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.setContentView(R.layout.options_menu);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

            dialog.show();


            Button buttonMyAccount = (Button) dialog.findViewById(R.id.buttonMyAccount);
            Typeface font = getNormalFont(this);
            buttonMyAccount.setTypeface(font);
            buttonMyAccount.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Intent itMyAccount = new Intent(getBaseContext(), AccountActivity.class);
//                    startActivity(itMyAccount);
                }
            });


            Button buttonHelp = (Button) dialog.findViewById(R.id.buttonHelp);
            buttonHelp.setTypeface(font);
            buttonHelp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Intent itAssistant = new Intent(getBaseContext(), AssistantPagerActivity.class);
//                    startActivity(itAssistant);
                }
            });


            Button buttonContact = (Button) dialog.findViewById(R.id.buttonContact);
            buttonContact.setTypeface(font);
            buttonContact.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Intent itContact = new Intent(getBaseContext(), ContactActivity.class);
//                    startActivity(itContact);
                }
            });

            Button buttonAbout = (Button) dialog.findViewById(R.id.buttonAbout);
            buttonAbout.setTypeface(font);
            buttonAbout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Intent itAbout = new Intent(getBaseContext(), AboutActivity.class);
//                    startActivity(itAbout);
                }
            });


            Window window = dialog.getWindow();
            WindowManager.LayoutParams wlp = window.getAttributes();
            wlp.gravity = Gravity.RIGHT | Gravity.TOP;
            wlp.y = getSupportActionBar().getHeight();
            wlp.width = 300;
            wlp.flags &= ~WindowManager.LayoutParams.FLAG_DIM_BEHIND;
            window.setAttributes(wlp);


            return true;

//        default:
//            return super.onOptionsItemSelected(item);
//        }

    }
    */

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		return MenuLogic.handleOnOptionsItemSelected(this, item);
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
