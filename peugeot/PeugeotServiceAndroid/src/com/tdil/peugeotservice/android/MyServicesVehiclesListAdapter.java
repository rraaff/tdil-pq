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
import android.widget.ImageView;
import android.widget.TextView;

import com.tdil.peugeotservice.R;
import com.tdil.peugeotservice.android.rest.prevent.model.VehicleValueObjectBean;

/********* Adapter class extends with BaseAdapter and implements with OnClickListener ************/
public class MyServicesVehiclesListAdapter extends BaseAdapter implements OnClickListener {

	/*********** Declare Used Variables *********/
	private Activity activity;
	private ArrayList<VehicleValueObjectBean> data;
	private static LayoutInflater inflater = null;
	public Resources res;
	VehicleValueObjectBean iterAlarm = null;
	int i = 0;

	/************* CustomAdapter Constructor *****************/
	public MyServicesVehiclesListAdapter(Activity a, ArrayList<VehicleValueObjectBean> d, Resources resLocal) {

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
		public ImageView go;
	}

	/*********** Depends upon data size called for each row , Create each ListView row ***********/
	public View getView(int position, View convertView, ViewGroup parent) {

		View vi = convertView;
		AlarmViewHolder holder;

		if (convertView == null) {

			/********** Inflate tabitem.xml file for each row ( Defined below ) ************/
			vi = inflater.inflate(R.layout.my_services_vehicles_item, null);

			/******** View Holder Object to contain tabitem.xml file elements ************/
			holder = new AlarmViewHolder();
			holder.vehicleDescription = (TextView) vi.findViewById(R.id.vehicleDescription);
			holder.actualKm = (TextView) vi.findViewById(R.id.kmActual);
			holder.needsService = (TextView) vi.findViewById(R.id.needsService);
			holder.lastServiceDate = (TextView) vi.findViewById(R.id.lastServiceDate);
			holder.lastServiceKm = (TextView) vi.findViewById(R.id.lastServiceKm);
			holder.go = (ImageView)vi.findViewById(R.id.goToAddService);
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
			
			GoAddService goAddService = new GoAddService(iterAlarm);
			holder.vehicleDescription.setOnClickListener(goAddService);
			holder.actualKm.setOnClickListener(goAddService);
			holder.needsService.setOnClickListener(goAddService);
			holder.lastServiceDate.setOnClickListener(goAddService);
			holder.lastServiceKm.setOnClickListener(goAddService);
			holder.go.setOnClickListener(goAddService);
			/******** Set Item Click Listner for LayoutInflater for each row ***********/
			vi.setOnClickListener(goAddService);
		}
		return vi;
	}

	@Override
	public void onClick(View v) {
		Log.v("CustomAdapter", "=====Row button clicked");
	}

	private class GoAddService implements OnClickListener {
		private VehicleValueObjectBean vehicle;
		
		GoAddService(VehicleValueObjectBean vehicle) {
			this.vehicle = vehicle;
		}

		@Override
		public void onClick(View arg0) {
			Intent intent = new Intent(activity.getBaseContext(), AddServiceActivity.class);
			intent.putExtra(AddServiceActivity.VEHICLE, vehicle);
			activity.startActivity(intent);
			activity.finish();
		}
	}
}