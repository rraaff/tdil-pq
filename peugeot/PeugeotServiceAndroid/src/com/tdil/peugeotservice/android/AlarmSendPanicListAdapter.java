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
import android.widget.Button;
import android.widget.TextView;

import com.tdil.peugeotservice.R;
import com.tdil.peugeotservice.android.rest.model.Alarm;

/********* Adapter class extends with BaseAdapter and implements with OnClickListener ************/
public class AlarmSendPanicListAdapter extends BaseAdapter implements OnClickListener {

	/*********** Declare Used Variables *********/
	private Activity activity;
	private ArrayList<Alarm> data;
	private static LayoutInflater inflater = null;
	public Resources res;
	Alarm iterAlarm = null;
	int i = 0;

	/************* CustomAdapter Constructor *****************/
	public AlarmSendPanicListAdapter(Activity a, ArrayList<Alarm> d, Resources resLocal) {

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

		public TextView alarmDescription;
		public Button activateDeactivate;

	}

	/*********** Depends upon data size called for each row , Create each ListView row ***********/
	public View getView(int position, View convertView, ViewGroup parent) {

		View vi = convertView;
		AlarmViewHolder holder;

		if (convertView == null) {

			/********** Inflate tabitem.xml file for each row ( Defined below ) ************/
			vi = inflater.inflate(R.layout.alarmitem_send_panic, null);

			/******** View Holder Object to contain tabitem.xml file elements ************/
			holder = new AlarmViewHolder();
			holder.alarmDescription = (TextView) vi.findViewById(R.id.alarmDescription);
			holder.activateDeactivate = (Button)vi.findViewById(R.id.sendPanicSignalButton);

			/************ Set holder with LayoutInflater ************/
			vi.setTag(holder);
		} else
			holder = (AlarmViewHolder) vi.getTag();

		if (data.size() <= 0) {
			holder.alarmDescription.setText("No Data");

		} else {
			/***** Get each Model object from Arraylist ********/
			iterAlarm = null;
			iterAlarm = (Alarm) data.get(position);

			/************ Set Model values in Holder elements ***********/
			holder.alarmDescription.setText(iterAlarm.getDescription());
			holder.activateDeactivate.setOnClickListener(new SendPanicSignalListener(position));
			
			/******** Set Item Click Listner for LayoutInflater for each row ***********/
			vi.setOnClickListener(new SendPanicSignalListener(position));
		}
		return vi;
	}

	@Override
	public void onClick(View v) {
		Log.v("CustomAdapter", "=====Row button clicked");
	}

	/********* Called when Item click in ListView ************/
	private class SendPanicSignalListener implements OnClickListener {
		private int mPosition;

		SendPanicSignalListener(int position) {
			mPosition = position;
		}

		@Override
		public void onClick(View arg0) {
			HomeAlarmsSendPanicActivity sct = (HomeAlarmsSendPanicActivity) activity;
			sct.sendPanic(mPosition);
		}
	}
	
}