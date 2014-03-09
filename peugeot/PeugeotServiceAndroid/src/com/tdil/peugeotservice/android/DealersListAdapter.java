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
import com.tdil.peugeotservice.android.rest.prevent.model.DealerBean;

/********* Adapter class extends with BaseAdapter and implements with OnClickListener ************/
public class DealersListAdapter extends BaseAdapter implements OnClickListener {

	/*********** Declare Used Variables *********/
	private Activity activity;
	private ArrayList<DealerBean> data;
	private static LayoutInflater inflater = null;
	public Resources res;
	DealerBean iterAlarm = null;
	int i = 0;

	/************* CustomAdapter Constructor *****************/
	public DealersListAdapter(Activity a, ArrayList<DealerBean> d, Resources resLocal) {

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
		public TextView name;
		public TextView address;
		public TextView phone;
		public TextView email;
	}

	/*********** Depends upon data size called for each row , Create each ListView row ***********/
	public View getView(int position, View convertView, ViewGroup parent) {

		View vi = convertView;
		AlarmViewHolder holder;

		if (convertView == null) {

			/********** Inflate tabitem.xml file for each row ( Defined below ) ************/
			vi = inflater.inflate(R.layout.dealer_item, null);

			/******** View Holder Object to contain tabitem.xml file elements ************/
			holder = new AlarmViewHolder();
			holder.name = (TextView) vi.findViewById(R.id.dealerName);
			holder.address = (TextView) vi.findViewById(R.id.dealerAddress);
			holder.phone = (TextView) vi.findViewById(R.id.dealerPhone);
			holder.email = (TextView) vi.findViewById(R.id.dealerEmail);
//			holder.lastChangeUserAvatar = (ImageView) vi.findViewById(R.id.logAlarmAvatar);
			//holder.activateDeactivate = (ToggleButton)vi.findViewById(R.id.toggleAlarmActivation);
			//holder.viewAlarmLog = (Button)vi.findViewById(R.id.viewAlarmLogButton);
			/************ Set holder with LayoutInflater ************/
			vi.setTag(holder);
		} else
			holder = (AlarmViewHolder) vi.getTag();

		if (data.size() <= 0) {
			holder.name.setText("No Data");

		} else {
			/***** Get each Model object from Arraylist ********/
			iterAlarm = null;
			iterAlarm = (DealerBean) data.get(position);

			/************ Set Model values in Holder elements ***********/
			holder.name.setText(iterAlarm.getName());
			holder.address.setText(iterAlarm.getAddress());
			holder.phone.setText(iterAlarm.getPhone());
			holder.email.setText(iterAlarm.getEmail());
//			holder.vehicleDescription.setOnClickListener(goAlarmDashBoard);
//			holder.actualKm.setOnClickListener(goAlarmDashBoard);
//			holder.alarmStatus.setOnClickListener(goAlarmDashBoard);
//			holder.lastChangeDateLabel.setOnClickListener(goAlarmDashBoard);
//			holder.lastChangeDate.setOnClickListener(goAlarmDashBoard);
//			holder.goDashBoard.setOnClickListener(goAlarmDashBoard);
			
			/*new DownloadImageTask(holder.lastChangeUserAvatar)
					.execute(ApplicationConfig.URL_WEBSITE
							+ iterAlarm.getLastChangeLojackUserID());*/
			// holder.image.setImageResource(res.getIdentifier("com.androidexample.customlistview:drawable/"+tempValues.getStatus(),null,null));

			/******** Set Item Click Listner for LayoutInflater for each row ***********/
//			vi.setOnClickListener(goAlarmDashBoard);
		}
		return vi;
	}

	@Override
	public void onClick(View v) {
		Log.v("CustomAdapter", "=====Row button clicked");
	}

//	private class GoAlarmDashBoard implements OnClickListener {
//		private Alarm alarm;
//		
//		GoAlarmDashBoard(Alarm alarm) {
//			this.alarm = alarm;
//		}
//
//		@Override
//		public void onClick(View arg0) {
//			Intent intent = new Intent(activity.getBaseContext(), HomeAlarmDashboard.class);
//			intent.putExtra(HomeAlarmDashboard.ALARM, alarm);
//			activity.startActivity(intent);
//			activity.finish();
//		}
//	}
}