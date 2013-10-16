package com.tdil.thalamus.android;

import com.tdil.lojack.rl.R;
import com.tdil.thalamus.android.utils.Login;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.content.ClipData;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.DragEvent;
//import android.view.Menu;
import android.view.View;
import android.view.View.OnDragListener;
import android.view.View.OnLongClickListener;
import android.widget.Button;
//import android.widget.TextView;
import android.widget.Toast;

public class FooterLogic extends Activity {

	
	public static void installFooterLogic(final Activity activity) {
		installFooterLogic(activity, true);
	}
	
	public static void installFooterLogic(final Activity activity, final boolean finishOnExit) {
		
		View sendPanic = activity.findViewById(R.id.goToSendPanicActivity);
		if (sendPanic != null) {
			sendPanic.setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						Intent intent = new Intent(activity, HomeAlarmsSendPanicActivity.class);
						activity.startActivity(intent);
			        	//finish();
					}
				});
			}
		}
/*		
		View home = activity.findViewById(R.id.btnFooterHome);
		if (home!= null) {
			home.setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						if (Login.loggedUser.getHomeUser()) {
							Intent intent = new Intent(activity, HomeActivity.class); 
							activity.startActivity(intent); 
							if (finishOnExit) {
								activity.finish();
							}
						} else {
							String videoId = Login.loggedUser.getHomeVideo();
							playVideo(activity, videoId);
						}
					}
				});
		}
		View pets = activity.findViewById(R.id.btnFooterPets);
		if (pets != null) {
			pets.setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						handlePetsAccess(activity);
					}
	
				});
		}
		
		View prevent = activity.findViewById(R.id.btnFooterPrevent);
		if(prevent != null) {
			prevent.setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						handlePreventAccess(activity);
					}
				});
		}
		
		View parkings = activity.findViewById(R.id.btnFooterParkings);
		if (parkings != null) {
			parkings.setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						handleParkingsAccess(activity); 
					}
				});
		}
	}*/
	
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_index);
            findViewById(R.id.btnFooterHome).setOnLongClickListener(longListener);
            findViewById(R.id.btnFooterPrevent).setOnLongClickListener(longListener);
            findViewById(R.id.btnFooterPets).setOnLongClickListener(longListener);
            findViewById(R.id.btnFooterParkings).setOnLongClickListener(longListener);
            // findViewById(R.id.btnFooterTV).setOnLongClickListener(longListener);
            
            findViewById(R.id.dropTarget).setOnDragListener(dragListener);
    }
    
   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) 
    {
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.main, menu);
            return true;
    }
    */
    OnLongClickListener longListener = new OnLongClickListener()
    {
            @Override
            public boolean onLongClick(View v) 
            {
                //TextView fruit = (TextView) v;
                Button fruit = (Button) v;    
            	Toast.makeText(FooterLogic.this, "Text long clicked - " +fruit.getText() , Toast.LENGTH_SHORT).show();
                    
                    View.DragShadowBuilder myShadowBuilder = new MyShadowBuilder(v);
                    
                    ClipData data = ClipData.newPlainText("", "");
                    v.startDrag(data, myShadowBuilder, fruit, 0);
                    
                    return true;
            }
            
    };
    
    OnDragListener dragListener = new OnDragListener()
    {
            @Override
            public boolean onDrag(View v, DragEvent event) 
            {
                    int dragEvent = event.getAction();
                    //TextView dropText = (TextView) v;
                    Button dropButton = (Button) v;
                    
                    switch(dragEvent)
                    {
                            case DragEvent.ACTION_DRAG_ENTERED:
                            		dropButton.setTextColor(Color.GREEN);
                                    break;
                                    
                            case DragEvent.ACTION_DRAG_EXITED:
                            		dropButton.setTextColor(Color.RED);
                                    break;
                                    
                            case DragEvent.ACTION_DROP:
                                    //TextView draggedText = (TextView)event.getLocalState();
                                    dropButton.setTextColor(Color.BLUE);
                                    break;
                    }
                    
                    return true;
            }
            
    };
    
    private class MyShadowBuilder extends View.DragShadowBuilder
    {
            private Drawable shadow;
            
            public MyShadowBuilder(View v)
            {
                    super(v);
                    shadow = new ColorDrawable(Color.LTGRAY);
            }

            @Override
            public void onDrawShadow(Canvas canvas) 
            {
                    shadow.draw(canvas);
            }

            @Override
            public void onProvideShadowMetrics(Point shadowSize, Point shadowTouchPoint) 
            {
                    int height, width;
                    height = (int) getView().getHeight()/2;
                    width = (int) getView().getHeight()/2;
                    
                    shadow.setBounds(0, 0, width, height);
                    
                    shadowSize.set(width, height);
                    shadowTouchPoint.set(width/2, height/2);
            }
            
    }		

	public static void playVideo(final Activity activity, String videoId) {
		try{
			 Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + videoId));
			 activity.startActivity(intent);      
		}catch (ActivityNotFoundException ex){
			Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://m.youtube.com/watch?v="+videoId)); 
			//intent.putExtra("VIDEO_ID", videoId); 
			activity.startActivity(intent); 
		}
	}

	public static void handlePreventAccess(final Activity activity) {
		if (Login.loggedUser.getPreventUser()) {
			Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(Login.loggedUser.getPreventUrl())); 
			activity.startActivity(intent); 
		} else {
			String videoId = Login.loggedUser.getPreventVideo();
			playVideo(activity, videoId);
		}
	}

	public static void handleParkingsAccess(final Activity activity) {
		Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.lojack-app.com.ar/")); 
		activity.startActivity(intent);
	}

	public static void handlePetsAccess(final Activity activity) {
		if (Login.loggedUser.getPetUser()) {
			Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(Login.loggedUser.getPetUrl())); 
			activity.startActivity(intent); 
		} else {
			String videoId = Login.loggedUser.getPetVideo();
			playVideo(activity, videoId);
		}
	}
}




