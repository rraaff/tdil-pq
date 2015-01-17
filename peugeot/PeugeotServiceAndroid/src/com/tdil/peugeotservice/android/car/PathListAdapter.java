package com.tdil.peugeotservice.android.car;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import com.tdil.peugeotservice.R;
import com.tdil.peugeotservice.android.rest.model.prevent.PositionHistoryBean;

/********* Adapter class extends with BaseAdapter and implements with OnClickListener ************/
public class PathListAdapter extends BaseAdapter implements OnClickListener {

	/*********** Declare Used Variables *********/
	private Activity activity;
	private ArrayList<PositionHistoryBean> data;
	private static LayoutInflater inflater = null;
	public Resources res;
	PositionHistoryBean iterLight = null;
	int i = 0;

	/************* CustomAdapter Constructor *****************/
	public PathListAdapter(Activity a, Collection<PositionHistoryBean> d, Resources resLocal) {

		/********** Take passed values **********/
		activity = a;
		data = new ArrayList<PositionHistoryBean>();
		data.addAll(d);
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
	public static class PathItemHolder {

		public TextView pathItemDescription;
		public TextView pathItemSpeed;
		public TextView pathItemDate;
		public ImageButton goToPathDetail;

	}

	/*********** Depends upon data size called for each row , Create each ListView row ***********/
	public View getView(int position, View convertView, ViewGroup parent) {

		View vi = convertView;
		PathItemHolder holder;

		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddhhmmss");
		SimpleDateFormat dfView = new SimpleDateFormat("dd/MM/yyyy hh:mm");
		if (convertView == null) {

			/********** Inflate tabitem.xml file for each row ( Defined below ) ************/
			vi = inflater.inflate(R.layout.activity_locar_path_list_item, null);

			/******** View Holder Object to contain tabitem.xml file elements ************/
			holder = new PathItemHolder();
			holder.pathItemDescription = (TextView) vi.findViewById(R.id.pathItemDescription);
			holder.pathItemSpeed = (TextView) vi.findViewById(R.id.pathItemSpeed);
			holder.pathItemDate = (TextView) vi.findViewById(R.id.pathItemDate);
			holder.goToPathDetail = (ImageButton)vi.findViewById(R.id.goToPathDetail);

			/************ Set holder with LayoutInflater ************/
			vi.setTag(holder);
		} else
			holder = (PathItemHolder) vi.getTag();

		if (data.size() <= 0) {
			holder.pathItemDescription.setText("No Data");

		} else {
			/***** Get each Model object from Arraylist ********/
			iterLight = null;
			iterLight = (PositionHistoryBean) data.get(position);

			/************ Set Model values in Holder elements ***********/
			holder.pathItemDescription.setText(iterLight.getStreet() + " " + iterLight.getNumber());
			holder.pathItemSpeed.setText(iterLight.getSpeed());
			holder.pathItemDate.setText(iterLight.getFecha());

			GoPathDetail goLightDashBoard = new GoPathDetail(iterLight);
			holder.pathItemDescription.setOnClickListener(goLightDashBoard);
			holder.pathItemSpeed.setOnClickListener(goLightDashBoard);
			holder.pathItemDate.setOnClickListener(goLightDashBoard);

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
//			HomeLightsActivity sct = (HomeLightsActivity) activity;
//			sct.onItemClick(mPosition);
//			Light light = sct.getLight(mPosition);
//			Intent intent = new Intent(activity.getBaseContext(), ActivityHomeLightDashboard.class);
//			intent.putExtra(ActivityHomeLightDashboard.LIGHT, light);
//			activity.startActivity(intent);
		}
	}
	
	private class GoPathDetail implements OnClickListener {
		private PositionHistoryBean alarm;
		
		GoPathDetail(PositionHistoryBean alarm) {
			this.alarm = alarm;
		}

		@Override
		public void onClick(View arg0) {
			Intent intent = new Intent(activity.getBaseContext(), ActivityCarsPathHistoryDetail.class);
			intent.putExtra(ActivityCarsPathHistoryDetail.PositionHistoryBean, alarm);
			activity.startActivity(intent);
		}
	}
}