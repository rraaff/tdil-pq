package com.tdil.thalamus.android;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.tdil.lojack.rl.R;
import com.tdil.lojack.rl.R.color;
import com.tdil.thalamus.android.rest.model.ChangeLog;
import com.tdil.thalamus.android.utils.DownloadImageTask;

/********* Adapter class extends with BaseAdapter and implements with OnClickListener ************/
public class LightLogListAdapter extends BaseAdapter implements OnClickListener {

	/*********** Declare Used Variables *********/
	private Activity activity;
	private ArrayList<ChangeLog> data;
	private static LayoutInflater inflater = null;
	public Resources res;
	ChangeLog iterLog = null;
	int i = 0;

	/************* CustomAdapter Constructor *****************/
	public LightLogListAdapter(Activity a, ArrayList<ChangeLog> d, Resources resLocal) {

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
	public static class LightLogViewHolder {

		public ImageView changeUserAvatar;
		public TextView logDate;
		public TextView logStatus;
		public TextView logUser;

	}

	/*********** Depends upon data size called for each row , Create each ListView row ***********/
	public View getView(int position, View convertView, ViewGroup parent) {

		View vi = convertView;
		LightLogViewHolder holder;

		if (convertView == null) {

			/********** Inflate tabitem.xml file for each row ( Defined below ) ************/
			vi = inflater.inflate(R.layout.lightlogitem, null);

			/******** View Holder Object to contain tabitem.xml file elements ************/
			holder = new LightLogViewHolder();
			holder.logDate = (TextView) vi.findViewById(R.id.logLightUser);
			holder.logStatus = (TextView) vi.findViewById(R.id.logLightStatus);
			holder.logUser = (TextView) vi.findViewById(R.id.logLightUser);
			holder.changeUserAvatar = (ImageView) vi.findViewById(R.id.logLightAvatar);

			/************ Set holder with LayoutInflater ************/
			vi.setTag(holder);
		} else
			holder = (LightLogViewHolder) vi.getTag();

		if (data.size() <= 0) {
			holder.logDate.setText("No Data");

		} else {
			/***** Get each Model object from Arraylist ********/
			iterLog = null;
			iterLog = data.get(position);

			/************ Set Model values in Holder elements ***********/
			holder.logDate.setText(iterLog.getDate() + " " + iterLog.getHour());
			holder.logStatus.setText(iterLog.getAction());
			if (iterLog.getAction().toUpperCase().contains("APAGA")) {
				//holder.logStatus.setTextColor(getResources().getColor(R.color.lst_itm_off));
				holder.logStatus.setTextColor(color.lst_itm_off);
			} else {
				//holder.logStatus.setTextColor(getResources().getColor(R.color.lst_itm_on));
				holder.logStatus.setTextColor(color.lst_itm_on);
			}
			holder.logUser.setText(iterLog.getUser());
			new DownloadImageTask(holder.changeUserAvatar)
					.execute(ApplicationConfig.URL_WEBSITE
							+ iterLog.getLojackUserId());
			// holder.image.setImageResource(res.getIdentifier("com.androidexample.customlistview:drawable/"+tempValues.getStatus(),null,null));

			/******** Set Item Click Listner for LayoutInflater for each row ***********/
		}
		return vi;
	}

	@Override
	public void onClick(View v) {
		Log.v("CustomAdapter", "=====Row button clicked");
	}

	
}