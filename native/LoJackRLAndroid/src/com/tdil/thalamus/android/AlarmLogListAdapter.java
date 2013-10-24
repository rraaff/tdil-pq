package com.tdil.thalamus.android;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
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
import com.tdil.lojack.rl.R.color;
import com.tdil.thalamus.android.rest.model.Alarm;
import com.tdil.thalamus.android.rest.model.ChangeLog;
import com.tdil.thalamus.android.utils.DownloadImageTask;

/********* Adapter class extends with BaseAdapter and implements with OnClickListener ************/
public class AlarmLogListAdapter extends BaseAdapter implements OnClickListener {

	/*********** Declare Used Variables *********/
	private Activity activity;
	private ArrayList<ChangeLog> data;
	private static LayoutInflater inflater = null;
	public Resources res;
	ChangeLog iterLog = null;
	int i = 0;

	/************* CustomAdapter Constructor *****************/
	public AlarmLogListAdapter(Activity a, ArrayList<ChangeLog> d, Resources resLocal) {

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
	public static class AlarmLogViewHolder {

		public ImageView changeUserAvatar;
		public TextView logDate;
		public TextView logStatus;
		public TextView logUser;

	}

	/*********** Depends upon data size called for each row , Create each ListView row ***********/
	public View getView(int position, View convertView, ViewGroup parent) {

		View vi = convertView;
		AlarmLogViewHolder holder;

		if (convertView == null) {

			/********** Inflate tabitem.xml file for each row ( Defined below ) ************/
			vi = inflater.inflate(R.layout.alarmlogitem, null);

			/******** View Holder Object to contain tabitem.xml file elements ************/
			holder = new AlarmLogViewHolder();
			holder.logDate = (TextView) vi.findViewById(R.id.logAlarmUser);
			holder.logStatus = (TextView) vi.findViewById(R.id.logAlarmStatus);
			holder.logUser = (TextView) vi.findViewById(R.id.logAlarmUser);
			holder.changeUserAvatar = (ImageView) vi.findViewById(R.id.logAlarmAvatar);

			/************ Set holder with LayoutInflater ************/
			vi.setTag(holder);
		} else
			holder = (AlarmLogViewHolder) vi.getTag();

		if (data.size() <= 0) {
			holder.logDate.setText("No Data");

		} else {
			/***** Get each Model object from Arraylist ********/
			iterLog = null;
			iterLog = data.get(position);

			/************ Set Model values in Holder elements ***********/
			holder.logDate.setText(iterLog.getDate() + " " + iterLog.getHour());
			holder.logStatus.setText(iterLog.getAction());
			if (iterLog.getAction().toUpperCase().contains("DES")) {
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