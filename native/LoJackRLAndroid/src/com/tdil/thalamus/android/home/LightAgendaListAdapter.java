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
import com.tdil.thalamus.android.rest.model.LightAgenda;

public class LightAgendaListAdapter extends AbstractListAdapter<LightAgenda, LightAgendaViewHolder> {

	private Activity activity;
	
	public LightAgendaListAdapter(Activity a,
			ArrayList<LightAgenda> d, Resources resLocal) {
		super(a, d, resLocal);
		this.activity = a;
	}

	@Override
	protected void fillViewHolder(LightAgendaViewHolder holder,
			LightAgenda iter) {
		holder.description.setText(iter.getDescription());
		holder.goDetail.setOnClickListener(new GoDashboard(activity, iter));
	}

	@Override
	protected LightAgendaViewHolder createViewHolder(View vi) {
		LightAgendaViewHolder holder = new LightAgendaViewHolder();
		holder.description = (TextView)vi.findViewById(R.id.lightAgendaDescriptionTextView);
		holder.goDetail = (View)vi.findViewById(R.id.lightAgendaGoToDashboard);
		return holder;
	}

	@Override
	protected int getItemLayout() {
		return R.layout.light_agenda_list_item;
	}
	

	private static class GoDashboard implements OnClickListener {
		
		private Activity activity;
		private LightAgenda alarmAgenda;
		
		GoDashboard(Activity activity, LightAgenda alarmAgenda) {
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
