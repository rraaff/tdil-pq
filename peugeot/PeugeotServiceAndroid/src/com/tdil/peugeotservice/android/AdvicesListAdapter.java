package com.tdil.peugeotservice.android;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.tdil.peugeotservice.R;
import com.tdil.peugeotservice.android.rest.model.AdviceBean;
import com.tdil.peugeotservice.android.rest.prevent.model.VehicleValueObjectBean;

/********* Adapter class extends with BaseAdapter and implements with OnClickListener ************/
public class AdvicesListAdapter extends BaseAdapter implements OnClickListener {

	/*********** Declare Used Variables *********/
	private PeugeotActivity activity;
	private ArrayList<AdviceBean> data;
	private static LayoutInflater inflater = null;
	public Resources res;
	AdviceBean iterAlarm = null;
	int i = 0;

	/************* CustomAdapter Constructor *****************/
	public AdvicesListAdapter(PeugeotActivity a, ArrayList<AdviceBean> d, Resources resLocal) {

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

		
		public TextView vehicleDescription;
		public TextView serviceBeforeKm;
		public TextView serviceBeforeDate;
		public View beforeDateSection;
	}

	/*********** Depends upon data size called for each row , Create each ListView row ***********/
	public View getView(int position, View convertView, ViewGroup parent) {

		View vi = convertView;
		AlarmViewHolder holder;

		if (convertView == null) {

			/********** Inflate tabitem.xml file for each row ( Defined below ) ************/
			vi = inflater.inflate(R.layout.advices_item, null);

			/******** View Holder Object to contain tabitem.xml file elements ************/
			holder = new AlarmViewHolder();
			holder.vehicleDescription = (TextView) vi.findViewById(R.id.vehicleDescription);
			PeugeotActivity.setTypeface(activity, holder.vehicleDescription);
			holder.serviceBeforeKm = (TextView) vi.findViewById(R.id.serviceBeforeKm);
			PeugeotActivity.setTypeface(activity, holder.serviceBeforeKm);
			holder.serviceBeforeDate = (TextView) vi.findViewById(R.id.serviceBeforeDate);
			PeugeotActivity.setTypeface(activity, holder.serviceBeforeDate);
			holder.beforeDateSection = (View) vi.findViewById(R.id.beforeDateSection);
			PeugeotActivity.setTypeface(activity, holder.serviceBeforeDate);
//			holder.lastChangeUserAvatar = (ImageView) vi.findViewById(R.id.logAlarmAvatar);
			//holder.activateDeactivate = (ToggleButton)vi.findViewById(R.id.toggleAlarmActivation);
			//holder.viewAlarmLog = (Button)vi.findViewById(R.id.viewAlarmLogButton);
			/************ Set holder with LayoutInflater ************/
			vi.setTag(holder);
		} else
			holder = (AlarmViewHolder) vi.getTag();

		if (data.size() <= 0) {
			holder.vehicleDescription.setText("No Data");

		} else {
			/***** Get each Model object from Arraylist ********/
			iterAlarm = null;
			iterAlarm = (AdviceBean) data.get(position);

			/************ Set Model values in Holder elements ***********/
			holder.vehicleDescription.setText(iterAlarm.getDomain());
			holder.serviceBeforeKm.setText(String.valueOf(iterAlarm.getKm()));
			if (iterAlarm.getServicedate() != null && iterAlarm.getServicedate().length() > 0) {
				holder.serviceBeforeDate.setText(iterAlarm.getServicedate());
			} else {
				holder.beforeDateSection.setVisibility(View.GONE);
			}
		}
		return vi;
	}

	@Override
	public void onClick(View v) {
		Log.v("CustomAdapter", "=====Row button clicked");
	}

}