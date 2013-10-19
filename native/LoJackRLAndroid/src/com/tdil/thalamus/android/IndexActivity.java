package com.tdil.thalamus.android;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ClipData;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnDragListener;
import android.view.View.OnLongClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.tdil.lojack.rl.R;

/**
 * Activity which displays a login screen to the user, offering registration as
 * well.
 */
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

		setContentView(R.layout.activity_index);
		// FooterLogic.installFooterLogic(this, false);

		findViewById(R.id.btnFooterPrevent)
				.setOnLongClickListener(new StartDragListener(this, PREVENT, BitmapFactory.decodeResource(getResources(), R.drawable.rd_item_cars_on)));
		findViewById(R.id.btnFooterPets).setOnLongClickListener(new StartDragListener(this, PETS, BitmapFactory.decodeResource(getResources(), R.drawable.rd_item_pets_on)));
		findViewById(R.id.btnFooterParkings).setOnLongClickListener(
				new StartDragListener(this, PARKINGS, BitmapFactory.decodeResource(getResources(), R.drawable.rd_item_park_on)));
		findViewById(R.id.btnFooterTV).setOnLongClickListener(new StartDragListener(this, TV, BitmapFactory.decodeResource(getResources(), R.drawable.rd_item_ljtv_on)));
		findViewById(R.id.btnFooterHome).setOnLongClickListener(new StartDragListener(this, HOME, BitmapFactory.decodeResource(getResources(), R.drawable.rd_item_home_on)));

		findViewById(R.id.dropTarget).setOnDragListener(dragListener);
		
		findViewById(R.id.droptarget2).setOnDragListener(dragListener1);
	}

	public class StartDragListener implements OnLongClickListener {

		private IndexActivity activity;
		private String localState;
		private Bitmap bitmap;

		public StartDragListener(IndexActivity activity, String localState, Bitmap bitmap) {
			super();
			this.activity = activity;
			this.localState = localState;
			this.bitmap = bitmap;
		}

		@Override
		public boolean onLongClick(View v) {
			Button fruit = (Button) v;
			
			View.DragShadowBuilder myShadowBuilder = new MyShadowBuilder(v, bitmap);

			ClipData data = ClipData.newPlainText("", "");
			v.startDrag(data, myShadowBuilder, localState, 0);

			return true;
		}

	};

	OnDragListener dragListener = new OnDragListener() {
		@Override
		public boolean onDrag(View v, DragEvent event) {
			int dragEvent = event.getAction();
			TextView dropButton = (TextView) v;

			switch (dragEvent) {
			case DragEvent.ACTION_DRAG_ENTERED:
				dropButton.setTextColor(R.color.orangeDark);
				dropButton.setAlpha(20);
				
				break;

			case DragEvent.ACTION_DRAG_EXITED:
//				dropButton.setTextColor(R.color.);
				dropButton.setAlpha(0);
				break;

			case DragEvent.ACTION_DROP:
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
				dropButton.setTextColor(R.color.orangeDark);
				
				break;
			}

			return true;
		}
	};
	
	OnDragListener dragListener1 = new OnDragListener() {
		@Override
		public boolean onDrag(View v, DragEvent event) {
			int dragEvent = event.getAction();
			TextView dropButton = (TextView) v;

			switch (dragEvent) {
			case DragEvent.ACTION_DRAG_ENTERED:
				dropButton.setBackgroundResource(R.drawable.dropon);
				break;

			case DragEvent.ACTION_DRAG_EXITED:
				dropButton.setBackgroundResource(R.drawable.dropoff);
				break;

			case DragEvent.ACTION_DROP:
				dropButton.setBackgroundResource(R.drawable.dropoff);
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
				dropButton.setTextColor(R.color.orangeDark);
				
				break;
			}

			return true;
		}

	};

	private class MyShadowBuilder extends View.DragShadowBuilder {
		private Drawable shadow;

		public MyShadowBuilder(View v, Bitmap bitmap) {
			super(v);
//			shadow = new ColorDrawable(Color.LTGRAY);
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