package com.tdil.thalamus.android;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ClipData;
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
import android.view.DragEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnDragListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.tdil.lojack.rl.R;
import com.tdil.thalamus.android.utils.Login;

@SuppressLint("ResourceAsColor")
public class IndexActivity extends Activity {

	private static final String HOME = "HOME";
	private static final String PARKINGS = "PARKINGS";
	private static final String TV = "TV";
	private static final String PETS = "PETS";
	private static final String PREVENT = "CAR";
	
	/**
	 * The default email to populate the email field with.
	 */
	// Values for email and password at the time of the login attempt.

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Thread.setDefaultUncaughtExceptionHandler(new UnCaughtException(this));
		setContentView(R.layout.activity_index);

//		BitmapFactory.Options options = new BitmapFactory.Options();
//		options.inSampleSize = 4;
		
		this.getActionBar().setTitle(Login.getLoggedUser(this).getName());
		findViewById(R.id.btnFooterPrevent).setOnTouchListener(new StartDragOnTouchListener(this, PREVENT, BitmapFactory.decodeResource(getResources(), R.drawable.rd_item_cars_on)));
		findViewById(R.id.btnFooterPets).setOnTouchListener(new StartDragOnTouchListener(this, PETS, BitmapFactory.decodeResource(getResources(), R.drawable.rd_item_pets_on)));
		findViewById(R.id.btnFooterParkings).setOnTouchListener(new StartDragOnTouchListener(this, PARKINGS, BitmapFactory.decodeResource(getResources(), R.drawable.rd_item_park_on)));
		findViewById(R.id.btnFooterTV).setOnTouchListener(new StartDragOnTouchListener(this, TV, BitmapFactory.decodeResource(getResources(), R.drawable.rd_item_ljtv_on)));
		findViewById(R.id.btnFooterHome).setOnTouchListener(new StartDragOnTouchListener(this, HOME, BitmapFactory.decodeResource(getResources(), R.drawable.rd_item_home_on)));

		findViewById(R.id.dropTarget).setOnDragListener(dragListener1);
		Button button = (Button)findViewById(R.id.vluCount);
		if (Login.getLoggedUser(this).getVluMessages() > 0) {
			button.setText(String.valueOf(Login.getLoggedUser(this).getVluMessages()));
			button.setOnClickListener(new ViewVLUMessagesListener(this));
		} else {
			button.setVisibility(View.GONE);
		}
	}
	
	private class ViewVLUMessagesListener implements OnClickListener {
		private IndexActivity activity;
		
		ViewVLUMessagesListener(IndexActivity activity) {
			this.activity = activity;
		}

		@Override
		public void onClick(View arg0) {
			Intent intent = new Intent(getBaseContext(), VLUMessagesActivity.class);
			startActivity(intent);
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

	public class StartDragOnTouchListener implements OnTouchListener {

		private IndexActivity activity;
		private String localState;
		private Bitmap bitmap;

		public StartDragOnTouchListener(IndexActivity activity, String localState, Bitmap bitmap) {
			super();
			this.activity = activity;
			this.localState = localState;
			this.bitmap = bitmap;
		}

		@Override
		public boolean onTouch(View v, MotionEvent motionEvent) {
			if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
				Button fruit = (Button) v;
				v.setAlpha(0.3f);
				// No responde
				View.DragShadowBuilder myShadowBuilder = new MyShadowBuilder(v, bitmap);
	
				ClipData data = ClipData.newPlainText("", "");
				v.startDrag(data, myShadowBuilder, localState, 0);
	
				return true;
			} else {
			    return false;
			}
		}
	};
	
	OnDragListener dragListener1 = new OnDragListener() {
		@Override
		public boolean onDrag(View v, DragEvent event) {
			int dragEvent = event.getAction();
			TextView dropButton = (TextView) v;

			switch (dragEvent) {
			case DragEvent.ACTION_DRAG_ENDED:
				findViewById(R.id.btnFooterHome).setAlpha(1);
				findViewById(R.id.btnFooterParkings).setAlpha(1);
				findViewById(R.id.btnFooterPets).setAlpha(1);
				findViewById(R.id.btnFooterPrevent).setAlpha(1);
				findViewById(R.id.btnFooterTV).setAlpha(1);
				break;
			case DragEvent.ACTION_DRAG_ENTERED:
				dropButton.setBackgroundResource(R.drawable.rd_droppon);
				
				// Get instance of Vibrator from current Context
				Vibrator vv = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

				// Vibrate for 100 milliseconds
				vv.vibrate(100);
				break;

			case DragEvent.ACTION_DRAG_EXITED:
				dropButton.setBackgroundResource(R.drawable.transparente);
				break;

			case DragEvent.ACTION_DROP:
				dropButton.setBackgroundResource(R.drawable.transparente);
				if (HOME.equals(event.getLocalState())) {
					FooterLogic.handleHomeAccess(IndexActivity.this, false);
				}
				if (PETS.equals(event.getLocalState())) {
					FooterLogic.handlePetsAccess(IndexActivity.this);
				}
				if (PREVENT.equals(event.getLocalState())) {
					FooterLogic.handlePreventAccess(IndexActivity.this);
				}
				if (PARKINGS.equals(event.getLocalState())) {
					FooterLogic.handleParkingsAccess(IndexActivity.this);
				}
				if (TV.equals(event.getLocalState())) {
					FooterLogic.handleTvAccess(IndexActivity.this);
				}
				
				break;
			}

			return true;
		}

	};

	private class MyShadowBuilder extends View.DragShadowBuilder {
		private Drawable shadow;

		public MyShadowBuilder(View v, Bitmap bitmap) {
			super(v);
			shadow = new BitmapDrawable(IndexActivity.this.getResources(), bitmap);
		}

		@Override
		public void onDrawShadow(Canvas canvas) {
			shadow.draw(canvas);
		}

		@Override
		public void onProvideShadowMetrics(Point shadowSize,
				Point shadowTouchPoint) {
			int height, width;
			height = (int) getView().getHeight();
			width = (int) getView().getHeight();

			shadow.setBounds(0, 0, width, height);

			shadowSize.set(width, height);
			shadowTouchPoint.set(width / 2, height / 2);
		}

	}

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
		getMenuInflater().inflate(R.menu.menu_without_apps, menu);
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
