package com.tdil.thalamus.android;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.View.OnTouchListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.tdil.lojack.rl.R;
import com.tdil.thalamus.android.car.ActivityCarsPathHistory;
import com.tdil.thalamus.android.car.CarsDialogs;
import com.tdil.thalamus.android.car.VLUMessagesActivity;
import com.tdil.thalamus.android.header.logic.ActionBarLogic;
import com.tdil.thalamus.android.header.logic.HeaderLogic;
import com.tdil.thalamus.android.utils.Login;

@SuppressLint("ResourceAsColor")
public class IndexActivity extends LoJackActivity {

	private static final String HOME = "HOME";
	private static final String PARKINGS = "PARKINGS";
	private static final String TV = "TV";
	private static final String PETS = "PETS";
	private static final String PREVENT = "CAR";
	
	private static final String CLUB_LJ = "CLUB_LJ";
	
	/**
	 * The default email to populate the email field with.
	 */
	// Values for email and password at the time of the login attempt.

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		//this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 0);
		Thread.setDefaultUncaughtExceptionHandler(new UnCaughtException(this));
		setContentView(R.layout.activity_index);
		customizeActionBar(Login.getLoggedUser(this).getName(), true);
		
//		customizeActionBar(Login.getLoggedUser(this).getName(), true);
		
		//ActionBarLogic.installActionBarLogic(this, false);

//		BitmapFactory.Options options = new BitmapFactory.Options();
//		options.inSampleSize = 4;
		
		
//		Pablo, este pedazo era el viejo on drag
//		findViewById(R.id.btnFooterPrevent).setOnTouchListener(new StartDragOnTouchListener(this, PREVENT, BitmapFactory.decodeResource(getResources(), R.drawable.rd_item_cars_on)));
//		findViewById(R.id.btnFooterPets).setOnTouchListener(new StartDragOnTouchListener(this, PETS, BitmapFactory.decodeResource(getResources(), R.drawable.rd_item_pets_on)));
//		findViewById(R.id.btnFooterParkings).setOnTouchListener(new StartDragOnTouchListener(this, PARKINGS, BitmapFactory.decodeResource(getResources(), R.drawable.rd_item_park_on)));
//		findViewById(R.id.btnFooterTV).setOnTouchListener(new StartDragOnTouchListener(this, TV, BitmapFactory.decodeResource(getResources(), R.drawable.rd_item_ljtv_on)));
//		findViewById(R.id.btnFooterHome).setOnTouchListener(new StartDragOnTouchListener(this, HOME, BitmapFactory.decodeResource(getResources(), R.drawable.rd_item_home_on)));
//		
//		findViewById(R.id.btnClubLJ).setOnTouchListener(new StartDragOnTouchListener(this, CLUB_LJ, BitmapFactory.decodeResource(getResources(), R.drawable.rd_item_ljtv_on)));
//		
//		
//		findViewById(R.id.btnFooterPrevent).setOnDragListener(new ButtonDragListener(PREVENT));
//		findViewById(R.id.btnFooterPets).setOnDragListener(new ButtonDragListener(PETS));
//		findViewById(R.id.btnFooterParkings).setOnDragListener(new ButtonDragListener(PARKINGS));
//		findViewById(R.id.btnFooterTV).setOnDragListener(new ButtonDragListener(TV));
//		findViewById(R.id.btnFooterHome).setOnDragListener(new ButtonDragListener(HOME));
//		
//		findViewById(R.id.btnClubLJ).setOnDragListener(new ButtonDragListener(CLUB_LJ));
		
//		Pablo, este es el nuevo onclick y on long clic
		/*
		findViewById(R.id.btnFooterPrevent).setOnLongClickListener(new StartDragOnLongClickListener(this, PREVENT, BitmapFactory.decodeResource(getResources(), R.drawable.rd_item_cars_on)));
		findViewById(R.id.btnFooterPrevent).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				FooterLogic.handlePreventAccess(IndexActivity.this);
			}
		});
		
		findViewById(R.id.btnFooterPets).setOnLongClickListener(new StartDragOnLongClickListener(this, PETS, BitmapFactory.decodeResource(getResources(), R.drawable.rd_item_pets_on)));
		findViewById(R.id.btnFooterPets).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				FooterLogic.handlePetsAccess(IndexActivity.this);
			}
		});
		
		findViewById(R.id.btnFooterParkings).setOnLongClickListener(new StartDragOnLongClickListener(this, PARKINGS, BitmapFactory.decodeResource(getResources(), R.drawable.rd_item_park_on)));
		findViewById(R.id.btnFooterParkings).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				FooterLogic.handleParkingsAccess(IndexActivity.this);
			}
		});
		
		findViewById(R.id.btnFooterTV).setOnLongClickListener(new StartDragOnLongClickListener(this, TV, BitmapFactory.decodeResource(getResources(), R.drawable.rd_item_ljtv_on)));
		findViewById(R.id.btnFooterTV).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				FooterLogic.handleTvAccess(IndexActivity.this);
			}
		});
		*/
//		findViewById(R.id.btnFooterHome).setOnLongClickListener(new StartDragOnLongClickListener(this, HOME, BitmapFactory.decodeResource(getResources(), R.drawable.rd_item_home_on)));
		findViewById(R.id.btnFooterPrevent).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				HeaderLogic.handlePreventAccess(IndexActivity.this);
			}
		});
		findViewById(R.id.btnFooterPets).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				HeaderLogic.handlePetsAccess(IndexActivity.this);
			}
		});
		findViewById(R.id.btnFooterParkings).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				HeaderLogic.handleParkingsAccess(IndexActivity.this);
			}
		});
//		findViewById(R.id.btnFooterTV).setOnClickListener(new OnClickListener() {
//			@Override
//			public void onClick(View v) {
//				FooterLogic.handleTvAccess(IndexActivity.this);
//			}
//		});
		findViewById(R.id.btnFooterHome).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				HeaderLogic.handleHomeAccess(IndexActivity.this, false);
			}
		});
		findViewById(R.id.btnClubLJ).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				HeaderLogic.handleClubLoJackAccess(IndexActivity.this);
			}
		});
		
		findViewById(R.id.btnFooterEstacioname).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				HeaderLogic.handleParkedModeAccess(IndexActivity.this);
			}
		});
/*
		Button button = (Button)findViewById(R.id.vluCount);
		int vluMessagesCount = Login.getLoggedUser(this).getVluMessages();
		if (vluMessagesCount > 0) {
			button.setText(String.valueOf(vluMessagesCount));
			button.setOnClickListener(new ViewVLUMessagesListener(this, vluMessagesCount));
		} else {
			if (Login.getLoggedUser(this).getVluClient()) {
				button.setBackgroundResource(R.drawable.badge_ok);
//				button.setOnClickListener(new OnClickListener() {
//					@Override
//					public void onClick(View v) {
//						Context context = getApplicationContext();
//						CharSequence text = "TU EQUIPO LOJACK FUNCIONA CORRECTAMENTE";
//						int duration = Toast.LENGTH_SHORT;
//						Toast toast = Toast.makeText(context, text, duration);
//						toast.show();
//					}
//				});
				button.setOnClickListener(new ViewVLUMessagesListener(this, vluMessagesCount));
			} else {
				button.setVisibility(View.GONE);
			}
		}*/
	}

//		findViewById(R.id.dropTarget).setOnDragListener(dragListener1);
	
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
	
	
//	public class StartDragOnLongClickListener implements OnLongClickListener {
//
//		private IndexActivity activity;
//		private String localState;
//		private Bitmap bitmap;
//
//		public StartDragOnLongClickListener(IndexActivity activity, String localState, Bitmap bitmap) {
//			super();
//			this.activity = activity;
//			this.localState = localState;
//			this.bitmap = bitmap;
//		}
//		
//		@Override
//		public boolean onLongClick(View v) {
//			Button fruit = (Button) v;
//			v.setAlpha(0.3f);
//			// No responde
//			View.DragShadowBuilder myShadowBuilder = new MyShadowBuilder(v, bitmap);
//
//			ClipData data = ClipData.newPlainText("", "");
//			v.startDrag(data, myShadowBuilder, localState, 0);
//
//			return true;
//		}
//	};

//	public class StartDragOnTouchListener implements OnTouchListener {
//
//		private IndexActivity activity;
//		private String localState;
//		private Bitmap bitmap;
//
//		public StartDragOnTouchListener(IndexActivity activity, String localState, Bitmap bitmap) {
//			super();
//			this.activity = activity;
//			this.localState = localState;
//			this.bitmap = bitmap;
//		}
//
//		@Override
//		public boolean onTouch(View v, MotionEvent motionEvent) {
//			if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
//				Button fruit = (Button) v;
//				v.setAlpha(0.3f);
//				// No responde
//				View.DragShadowBuilder myShadowBuilder = new MyShadowBuilder(v, bitmap);
//	
//				ClipData data = ClipData.newPlainText("", "");
//				v.startDrag(data, myShadowBuilder, localState, 0);
//	
//				return true;
//			} else {
//			    return false;
//			}
//		}
//	};
	
//	public class ButtonDragListener implements OnDragListener {
//		
//		private String buttonTarget;
//		
//		public ButtonDragListener(String buttonTarget) {
//			super();
//			this.buttonTarget = buttonTarget;
//		}
//
//		@Override
//		public boolean onDrag(View v, DragEvent event) {
//			int dragEvent = event.getAction();
//			TextView dropButton = (TextView) v;
//
//			switch (dragEvent) {
//			case DragEvent.ACTION_DRAG_ENDED:
//				findViewById(R.id.btnFooterHome).setAlpha(1);
//				findViewById(R.id.btnFooterParkings).setAlpha(1);
//				findViewById(R.id.btnFooterPets).setAlpha(1);
//				findViewById(R.id.btnFooterPrevent).setAlpha(1);
//				findViewById(R.id.btnFooterTV).setAlpha(1);
//				findViewById(R.id.btnClubLJ).setAlpha(1);
//				break;
//			case DragEvent.ACTION_DRAG_ENTERED:
//				break;
//
//			case DragEvent.ACTION_DRAG_EXITED:
//				break;
//
//			case DragEvent.ACTION_DROP:
//				findViewById(R.id.btnFooterHome).setAlpha(1);
//				findViewById(R.id.btnFooterParkings).setAlpha(1);
//				findViewById(R.id.btnFooterPets).setAlpha(1);
//				findViewById(R.id.btnFooterPrevent).setAlpha(1);
//				findViewById(R.id.btnFooterTV).setAlpha(1);
//				findViewById(R.id.btnClubLJ).setAlpha(1);
//				if (HOME.equals(event.getLocalState()) && HOME.equals(this.buttonTarget)) {
//					FooterLogic.handleHomeAccess(IndexActivity.this, false);
//				}
//				if (PETS.equals(event.getLocalState()) && PETS.equals(this.buttonTarget)) {
//					FooterLogic.handlePetsAccess(IndexActivity.this);
//				}
//				if (PREVENT.equals(event.getLocalState()) && PREVENT.equals(this.buttonTarget)) {
//					FooterLogic.handlePreventAccess(IndexActivity.this);
//				}
//				if (PARKINGS.equals(event.getLocalState()) && PARKINGS.equals(this.buttonTarget)) {
//					FooterLogic.handleParkingsAccess(IndexActivity.this);
//				}
//				if (TV.equals(event.getLocalState()) && TV.equals(this.buttonTarget)) {
//					FooterLogic.handleTvAccess(IndexActivity.this);
//				}
//				if (CLUB_LJ.equals(event.getLocalState()) && CLUB_LJ.equals(this.buttonTarget)) {
//					FooterLogic.handleClubLoJackAccess(IndexActivity.this);
//				}
//				
//				break;
//			}
//
//			return true;
//		}
//
//	};
	
//	OnDragListener dragListener1 = new OnDragListener() {
//		@Override
//		public boolean onDrag(View v, DragEvent event) {
//			int dragEvent = event.getAction();
//			TextView dropButton = (TextView) v;
//
//			switch (dragEvent) {
//			case DragEvent.ACTION_DRAG_ENDED:
//				findViewById(R.id.btnFooterHome).setAlpha(1);
//				findViewById(R.id.btnFooterParkings).setAlpha(1);
//				findViewById(R.id.btnFooterPets).setAlpha(1);
//				findViewById(R.id.btnFooterPrevent).setAlpha(1);
//				findViewById(R.id.btnFooterTV).setAlpha(1);
//				break;
//			case DragEvent.ACTION_DRAG_ENTERED:
//				dropButton.setBackgroundResource(R.drawable.rd_droppon);
//				
//				// Get instance of Vibrator from current Context
//				Vibrator vv = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
//
//				// Vibrate for 100 milliseconds
//				vv.vibrate(100);
//				break;
//
//			case DragEvent.ACTION_DRAG_EXITED:
//				dropButton.setBackgroundResource(R.drawable.transparente);
//				break;
//
//			case DragEvent.ACTION_DROP:
//				dropButton.setBackgroundResource(R.drawable.transparente);
//				if (HOME.equals(event.getLocalState())) {
//					FooterLogic.handleHomeAccess(IndexActivity.this, false);
//				}
//				if (PETS.equals(event.getLocalState())) {
//					FooterLogic.handlePetsAccess(IndexActivity.this);
//				}
//				if (PREVENT.equals(event.getLocalState())) {
//					FooterLogic.handlePreventAccess(IndexActivity.this);
//				}
//				if (PARKINGS.equals(event.getLocalState())) {
//					FooterLogic.handleParkingsAccess(IndexActivity.this);
//				}
//				if (TV.equals(event.getLocalState())) {
//					FooterLogic.handleTvAccess(IndexActivity.this);
//				}
//				if (CLUB_LJ.equals(event.getLocalState())) {
//					FooterLogic.handleClubLoJackAccess(IndexActivity.this);
//				}
//				
//				break;
//			}
//
//			return true;
//		}
//
//	};

//	private class MyShadowBuilder extends View.DragShadowBuilder {
//		private Drawable shadow;
//
//		public MyShadowBuilder(View v, Bitmap bitmap) {
//			super(v);
//			shadow = new BitmapDrawable(IndexActivity.this.getResources(), bitmap);
//		}
//
//		@Override
//		public void onDrawShadow(Canvas canvas) {
//			shadow.draw(canvas);
//		}
//
//		@Override
//		public void onProvideShadowMetrics(Point shadowSize,
//				Point shadowTouchPoint) {
//			int height, width;
//			height = (int) getView().getHeight();
//			width = (int) getView().getHeight();
//
//			shadow.setBounds(0, 0, width, height);
//
//			shadowSize.set(width, height);
//			shadowTouchPoint.set(width / 2, height / 2);
//		}
//
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
		getMenuInflater().inflate(R.menu.activity_login, menu);
		return true;
	}

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
