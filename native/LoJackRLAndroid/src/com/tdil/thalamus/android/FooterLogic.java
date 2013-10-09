package com.tdil.thalamus.android;

import com.tdil.lojack.rl.R;
import com.tdil.thalamus.android.utils.Login;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.view.View;

public class FooterLogic {

	public static void installFooterLogic(final Activity activity) {
		
		activity.findViewById(R.id.goToSendPanicActivity).setOnClickListener(
			new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					Intent intent = new Intent(activity, HomeAlarmsSendPanicActivity.class);
					activity.startActivity(intent);
		        	//finish();
				}
			});
		
		activity.findViewById(R.id.btnFooterHome).setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View view) {
						Intent intent = new Intent(activity, HomeActivity.class); 
						activity.startActivity(intent); 
						activity.finish();
					}
				});
		
		activity.findViewById(R.id.btnFooterPets).setOnClickListener(
			new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					if (Login.loggedUser.getPetUser()) {
						Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.lojack-app.com.ar/")); 
						//intent.putExtra("VIDEO_ID", videoId); 
						activity.startActivity(intent); 
					} else {
						String videoId = "742NZ2Fa4I4";
						try{
							 Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + videoId));
							 activity.startActivity(intent);      
						}catch (ActivityNotFoundException ex){
							Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://m.youtube.com/watch?v="+videoId)); 
							//intent.putExtra("VIDEO_ID", videoId); 
							activity.startActivity(intent); 
						}
					}
				}
			});
	}
}
