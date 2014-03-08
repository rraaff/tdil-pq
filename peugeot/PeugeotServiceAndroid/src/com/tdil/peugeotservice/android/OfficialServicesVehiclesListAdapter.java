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
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.tdil.peugeotservice.R;
import com.tdil.peugeotservice.android.rest.prevent.model.VehicleValueObjectBean;

/********* Adapter class extends with BaseAdapter and implements with OnClickListener ************/
public class OfficialServicesVehiclesListAdapter extends BaseAdapter implements OnClickListener {

	/*********** Declare Used Variables *********/
	private Activity activity;
	private ArrayList<VehicleValueObjectBean> data;
	private static LayoutInflater inflater = null;
	public Resources res;
	VehicleValueObjectBean iterAlarm = null;
	int i = 0;

	/************* CustomAdapter Constructor *****************/
	public OfficialServicesVehiclesListAdapter(Activity a, ArrayList<VehicleValueObjectBean> d, Resources resLocal) {

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
		public TextView actualKm;
		public TextView needsService;
		public TextView lastServiceDate;
		public TextView lastServiceKm;
		
		public TextView inWarranty;
		public TextView warrantyDescription;
	}

	/*********** Depends upon data size called for each row , Create each ListView row ***********/
	public View getView(int position, View convertView, ViewGroup parent) {

		View vi = convertView;
		AlarmViewHolder holder;

		if (convertView == null) {

			/********** Inflate tabitem.xml file for each row ( Defined below ) ************/
			vi = inflater.inflate(R.layout.official_services_vehicles_item, null);

			/******** View Holder Object to contain tabitem.xml file elements ************/
			holder = new AlarmViewHolder();
			holder.vehicleDescription = (TextView) vi.findViewById(R.id.vehicleDescription);
			holder.actualKm = (TextView) vi.findViewById(R.id.kmActual);
			holder.needsService = (TextView) vi.findViewById(R.id.needsService);
			holder.lastServiceDate = (TextView) vi.findViewById(R.id.lastServiceDate);
			holder.lastServiceKm = (TextView) vi.findViewById(R.id.lastServiceKm);
			holder.inWarranty = (TextView) vi.findViewById(R.id.is_in_warranty);
			holder.warrantyDescription = (TextView) vi.findViewById(R.id.model_warranty_text);
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
			iterAlarm = (VehicleValueObjectBean) data.get(position);

			/************ Set Model values in Holder elements ***********/
			holder.vehicleDescription.setText(iterAlarm.getDescription());
			holder.actualKm.setText(iterAlarm.getKm());
			holder.needsService.setText(iterAlarm.getNeedsService() ? "SI" : "NO");
			if (iterAlarm.getNeedsService()) {
				holder.needsService.setTextColor(Color.rgb(227,27,35));
			} else {
				holder.needsService.setTextColor(Color.rgb(35,102,0));
			}
			holder.lastServiceDate.setText(iterAlarm.getLastservicedate());
			holder.lastServiceKm.setText(iterAlarm.getLastservicekm());
			
			holder.inWarranty.setText(iterAlarm.isWarrantyexpired() ? "SI" : "NO");
			if (iterAlarm.isWarrantyexpired()) {
				holder.inWarranty.setTextColor(Color.rgb(227,27,35));
			} else {
				holder.inWarranty.setTextColor(Color.rgb(35,102,0));
			}
			holder.warrantyDescription.setText(iterAlarm.getVehicleModel() != null ? iterAlarm.getVehicleModel().getWarrantyDescription() : "-");
			
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