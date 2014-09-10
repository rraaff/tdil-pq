package com.tdil.thalamus.android.home;

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

import com.tdil.lojack.rl.R;
import com.tdil.thalamus.android.deprecated.HomeAlarmDashboard;
import com.tdil.thalamus.android.rest.model.Alarm;

/********* Adapter class extends with BaseAdapter and implements with OnClickListener ************/
public class AlarmListAdapter extends BaseAdapter implements OnClickListener {

	/*********** Declare Used Variables *********/
	private Activity activity;
	private ArrayList<Alarm> data;
	private static LayoutInflater inflater = null;
	public Resources res;
	Alarm iterAlarm = null;
	int i = 0;

	/************* CustomAdapter Constructor *****************/
	public AlarmListAdapter(Activity a, ArrayList<Alarm> d, Resources resLocal) {

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
		public TextView alarmStatusLabel;
		public TextView alarmStatus;
		public TextView lastChangeDateLabel;
		public TextView lastChangeDate;
		public TextView textWide;
		public ToggleButton activateDeactivate;
		public Button viewAlarmLog;
		public ImageView lastChangeUserAvatar;
		public ImageButton goDashBoard;
	}

	/*********** Depends upon data size called for each row , Create each ListView row ***********/
	public View getView(int position, View convertView, ViewGroup parent) {

		View vi = convertView;
		AlarmViewHolder holder;

		if (convertView == null) {

			/********** Inflate tabitem.xml file for each row ( Defined below ) ************/
			vi = inflater.inflate(R.layout.alarm_list_item, null);

			/******** View Holder Object to contain tabitem.xml file elements ************/
			holder = new AlarmViewHolder();
			holder.alarmDescription = (TextView) vi.findViewById(R.id.alarmDescription);
			holder.alarmStatusLabel = (TextView) vi.findViewById(R.id.alarmStatusLabel);
			holder.alarmStatus = (TextView) vi.findViewById(R.id.alarmStatus);
			holder.lastChangeDateLabel = (TextView) vi.findViewById(R.id.alarmLastChangeDateLabel);
			holder.lastChangeDate = (TextView) vi.findViewById(R.id.alarmLastChangeDate);
//			holder.lastChangeUserAvatar = (ImageView) vi.findViewById(R.id.logAlarmAvatar);
			holder.goDashBoard = (ImageButton)vi.findViewById(R.id.goToAlarmView);
			//holder.activateDeactivate = (ToggleButton)vi.findViewById(R.id.toggleAlarmActivation);
			//holder.viewAlarmLog = (Button)vi.findViewById(R.id.viewAlarmLogButton);
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
			holder.alarmStatus.setText(iterAlarm.getStatus());
			holder.lastChangeDate.setText(iterAlarm.getLastChangeDate());
			if (iterAlarm.isActive()) {
				holder.alarmStatus.setTextColor(Color.rgb(35,102,0));
			} else {
				holder.alarmStatus.setTextColor(Color.rgb(227,27,35));
			}
//			holder.activateDeactivate.setChecked(iterAlarm.isActive());
//			holder.activateDeactivate.setOnClickListener(new ToggleActivateListener(position));
//			holder.viewAlarmLog.setOnClickListener(new ViewAlarmLogListener(position));
			GoAlarmDashBoard goAlarmDashBoard = new GoAlarmDashBoard(iterAlarm);
			
			holder.alarmDescription.setOnClickListener(goAlarmDashBoard);
			holder.alarmStatusLabel.setOnClickListener(goAlarmDashBoard);
			holder.alarmStatus.setOnClickListener(goAlarmDashBoard);
			holder.lastChangeDateLabel.setOnClickListener(goAlarmDashBoard);
			holder.lastChangeDate.setOnClickListener(goAlarmDashBoard);
			holder.goDashBoard.setOnClickListener(goAlarmDashBoard);
			
			/*new DownloadImageTask(holder.lastChangeUserAvatar)
					.execute(ApplicationConfig.URL_WEBSITE
							+ iterAlarm.getLastChangeLojackUserID());*/
			// holder.image.setImageResource(res.getIdentifier("com.androidexample.customlistview:drawable/"+tempValues.getStatus(),null,null));

			/******** Set Item Click Listner for LayoutInflater for each row ***********/
			vi.setOnClickListener(goAlarmDashBoard);
		}
		return vi;
	}

	@Override
	public void onClick(View v) {
		Log.v("CustomAdapter", "=====Row button clicked");
	}

	private class GoAlarmDashBoard implements OnClickListener {
		private Alarm alarm;
		
		GoAlarmDashBoard(Alarm alarm) {
			this.alarm = alarm;
		}

		@Override
		public void onClick(View arg0) {
			Intent intent = new Intent(activity.getBaseContext(), ActivityHomeAlarmDashboard.class);
			intent.putExtra(ActivityHomeAlarmDashboard.ALARM, alarm);
			activity.startActivity(intent);
			activity.finish();
		}
	}
}