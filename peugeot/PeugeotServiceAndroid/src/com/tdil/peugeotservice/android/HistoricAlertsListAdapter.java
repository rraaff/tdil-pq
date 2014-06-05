package com.tdil.peugeotservice.android;

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
import android.widget.TextView;

import com.tdil.peugeotservice.R;
import com.tdil.peugeotservice.android.rest.model.AlertBean;

/********* Adapter class extends with BaseAdapter and implements with OnClickListener ************/
public class HistoricAlertsListAdapter extends BaseAdapter implements OnClickListener {

	/*********** Declare Used Variables *********/
	private PeugeotActivity activity;
	private ArrayList<AlertBean> data;
	private static LayoutInflater inflater = null;
	public Resources res;
	AlertBean iterAlarm = null;
	int i = 0;

	/************* CustomAdapter Constructor *****************/
	public HistoricAlertsListAdapter(PeugeotActivity a, ArrayList<AlertBean> d, Resources resLocal) {

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
	public static class AlarmViewHolder {

		
		public TextView alertDescription;
	}

	/*********** Depends upon data size called for each row , Create each ListView row ***********/
	public View getView(int position, View convertView, ViewGroup parent) {

		View vi = convertView;
		AlarmViewHolder holder;

		if (convertView == null) {

			/********** Inflate tabitem.xml file for each row ( Defined below ) ************/
			vi = inflater.inflate(R.layout.historic_alert_item, null);

			/******** View Holder Object to contain tabitem.xml file elements ************/
			holder = new AlarmViewHolder();
			holder.alertDescription = (TextView) vi.findViewById(R.id.alertText);
			PeugeotActivity.setTypeface(activity, holder.alertDescription);
//			holder.lastChangeUserAvatar = (ImageView) vi.findViewById(R.id.logAlarmAvatar);
			//holder.activateDeactivate = (ToggleButton)vi.findViewById(R.id.toggleAlarmActivation);
			//holder.viewAlarmLog = (Button)vi.findViewById(R.id.viewAlarmLogButton);
			/************ Set holder with LayoutInflater ************/
			vi.setTag(holder);
		} else
			holder = (AlarmViewHolder) vi.getTag();

		if (data.size() <= 0) {
			holder.alertDescription.setText("No Data");

		} else {
			/***** Get each Model object from Arraylist ********/
			iterAlarm = null;
			iterAlarm = (AlertBean) data.get(position);

			/************ Set Model values in Holder elements ***********/
			holder.alertDescription.setText(iterAlarm.getIndex() + " - " + iterAlarm.getAlertDate());
		}
		return vi;
	}

	@Override
	public void onClick(View v) {
		Log.v("CustomAdapter", "=====Row button clicked");
	}

}