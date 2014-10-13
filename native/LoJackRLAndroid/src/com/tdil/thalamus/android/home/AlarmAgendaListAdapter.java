package com.tdil.thalamus.android.home;

import java.util.ArrayList;

import android.app.Activity;
import android.content.res.Resources;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.tdil.lojack.rl.R;
import com.tdil.thalamus.android.gui.AbstractListAdapter;
import com.tdil.thalamus.android.rest.model.AlarmAgenda;

public class AlarmAgendaListAdapter extends AbstractListAdapter<AlarmAgenda, AlarmAgendaViewHolder> {

	private Activity activity;
	
	public AlarmAgendaListAdapter(Activity a,
			ArrayList<AlarmAgenda> d, Resources resLocal) {
		super(a, d, resLocal);
		this.activity = a;
	}

	@Override
	protected void fillViewHolder(AlarmAgendaViewHolder holder,
			AlarmAgenda iter) {
		holder.description.setText(iter.getDescription());
		holder.goDetail.setOnClickListener(new GoDashboard(activity, iter));
	}

	@Override
	protected AlarmAgendaViewHolder createViewHolder(View vi) {
		AlarmAgendaViewHolder holder = new AlarmAgendaViewHolder();
		holder.description = (TextView)vi.findViewById(R.id.alarmAgendaDescriptionTextView);
		holder.goDetail = (View)vi.findViewById(R.id.alarmAgendaGoToDashboard);
		return holder;
	}

	@Override
	protected int getItemLayout() {
		return R.layout.alarm_agenda_list_item;
	}
	

	private static class GoDashboard implements OnClickListener {
		
		private Activity activity;
		private AlarmAgenda alarmAgenda;
		
		GoDashboard(Activity activity, AlarmAgenda alarmAgenda) {
			this.activity = activity;
			this.alarmAgenda = alarmAgenda;
		}

		@Override
		public void onClick(View arg0) {
//			Intent intent = new Intent(activity.getBaseContext(), ActivityParkedModeVehicleHome.class);
//			intent.putExtra(ActivityParkedModeVehicleHome.VEHICLE, parkedModeStatus);
//			activity.startActivity(intent);
//			activity.finish();
		}
	}
}
