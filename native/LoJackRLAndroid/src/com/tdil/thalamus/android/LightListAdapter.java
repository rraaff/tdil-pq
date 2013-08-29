package com.tdil.thalamus.android;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.tdil.lojack.rl.R;
import com.tdil.thalamus.android.rest.model.Light;
import com.tdil.thalamus.android.rest.model.Light;
import com.tdil.thalamus.android.utils.DownloadImageTask;

/********* Adapter class extends with BaseAdapter and implements with OnClickListener ************/
public class LightListAdapter extends BaseAdapter implements OnClickListener {

	/*********** Declare Used Variables *********/
	private Activity activity;
	private ArrayList<Light> data;
	private static LayoutInflater inflater = null;
	public Resources res;
	Light iterLight = null;
	int i = 0;

	/************* CustomAdapter Constructor *****************/
	public LightListAdapter(Activity a, ArrayList<Light> d, Resources resLocal) {

		/********** Take passed values **********/
		activity = a;
		data = d;
		res = resLocal;

		/*********** Layout inflator to call external xml layout () **********************/
		inflater = (LayoutInflater) activity
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

	}

	/******** What is the size of Passed Arraylist Size ************/
	public int getCount() {

		if (data.size() <= 0)
			return 1;
		return data.size();
	}

	public Object getItem(int position) {
		return position;
	}

	public long getItemId(int position) {
		return position;
	}

	/********* Create a holder to contain inflated xml file elements ***********/
	public static class LightViewHolder {

		public TextView alarmDescription;
		public TextView alarmStatus;
		public TextView textWide;
		public ToggleButton activateDeactivate;
		public Button viewLightLog;
		public ImageView lastChangeUserAvatar;

	}

	/*********** Depends upon data size called for each row , Create each ListView row ***********/
	public View getView(int position, View convertView, ViewGroup parent) {

		View vi = convertView;
		LightViewHolder holder;

		if (convertView == null) {

			/********** Inflate tabitem.xml file for each row ( Defined below ) ************/
			vi = inflater.inflate(R.layout.lightitem, null);

			/******** View Holder Object to contain tabitem.xml file elements ************/
			holder = new LightViewHolder();
			holder.alarmDescription = (TextView) vi.findViewById(R.id.logLightUser);
			holder.alarmStatus = (TextView) vi.findViewById(R.id.logLightStatus);
			holder.activateDeactivate = (ToggleButton)vi.findViewById(R.id.toggleLight);
			holder.lastChangeUserAvatar = (ImageView) vi.findViewById(R.id.logLightAvatar);
			holder.viewLightLog = (Button)vi.findViewById(R.id.viewLightLogButton);

			/************ Set holder with LayoutInflater ************/
			vi.setTag(holder);
		} else
			holder = (LightViewHolder) vi.getTag();

		if (data.size() <= 0) {
			holder.alarmDescription.setText("No Data");

		} else {
			/***** Get each Model object from Arraylist ********/
			iterLight = null;
			iterLight = (Light) data.get(position);

			/************ Set Model values in Holder elements ***********/
			holder.alarmDescription.setText(iterLight.getDescription());
			holder.alarmStatus.setText(iterLight.getStatusDescription());
			holder.activateDeactivate.setChecked(iterLight.isOn());
			holder.activateDeactivate.setOnClickListener(new ToggleActivateListener(position));
			holder.viewLightLog.setOnClickListener(new ViewLightLogListener(position));
			
			new DownloadImageTask(holder.lastChangeUserAvatar)
					.execute(ApplicationConfig.URL_WEBSITE
							+ iterLight.getLastChangeLojackUserID());
			// holder.image.setImageResource(res.getIdentifier("com.androidexample.customlistview:drawable/"+tempValues.getStatus(),null,null));

			/******** Set Item Click Listner for LayoutInflater for each row ***********/
			vi.setOnClickListener(new OnItemClickListener(position));
		}
		return vi;
	}

	@Override
	public void onClick(View v) {
		Log.v("CustomAdapter", "=====Row button clicked");
	}

	/********* Called when Item click in ListView ************/
	private class OnItemClickListener implements OnClickListener {
		private int mPosition;

		OnItemClickListener(int position) {
			mPosition = position;
		}

		@Override
		public void onClick(View arg0) {
			HomeLightsActivity sct = (HomeLightsActivity) activity;
			sct.onItemClick(mPosition);
		}
	}
	
	/********* Called when Item click in ListView ************/
	private class ToggleActivateListener implements OnClickListener {
		private int mPosition;
		
		ToggleActivateListener(int position) {
			mPosition = position;
		}

		@Override
		public void onClick(View arg0) {
			HomeLightsActivity sct = (HomeLightsActivity) activity;
			sct.toggleActivation(mPosition);
		}
	}
	/********* Called when Item click in ListView ************/
	private class ViewLightLogListener implements OnClickListener {
		private int mPosition;
		
		ViewLightLogListener(int position) {
			mPosition = position;
		}

		@Override
		public void onClick(View arg0) {
			HomeLightsActivity sct = (HomeLightsActivity) activity;
			sct.viewLightLog(mPosition);
		}
	}
}