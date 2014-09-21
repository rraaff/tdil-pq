package com.tdil.thalamus.android.car.parkedmode;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.tdil.lojack.rl.R;
import com.tdil.thalamus.android.car.ActivityCarsPathHistoryDetail;
import com.tdil.thalamus.android.gui.AbstractListAdapter;
import com.tdil.thalamus.android.rest.model.parkedmode.ParkedModeStatus;

public class ParkedModeStatusListAdapter extends AbstractListAdapter<ParkedModeStatus, ParkedModeStatusViewHolder> {

	private Activity activity;
	
	public ParkedModeStatusListAdapter(Activity a,
			ArrayList<ParkedModeStatus> d, Resources resLocal) {
		super(a, d, resLocal);
		this.activity = a;
	}

	@Override
	protected void fillViewHolder(ParkedModeStatusViewHolder holder,
			ParkedModeStatus iter) {
		holder.domain.setText(iter.getDomain());
		if ("ON".equals(iter.getStatus())) {
			holder.status.setText("ACTIVADO");
			holder.status.setTextColor(activity.getResources().getColor(R.color.lst_itm_on));
		} else {
			holder.status.setText("DESACTIVADO");
			holder.status.setTextColor(activity.getResources().getColor(R.color.lst_itm_off));
		}
		holder.goDetail.setOnClickListener(new GoDashboard(activity, iter));
	}

	@Override
	protected ParkedModeStatusViewHolder createViewHolder(View vi) {
		ParkedModeStatusViewHolder holder = new ParkedModeStatusViewHolder();
		holder.domain = (TextView)vi.findViewById(R.id.pmDomainTextView);
		holder.status = (TextView)vi.findViewById(R.id.pmStatus);
		holder.goDetail = (View)vi.findViewById(R.id.pmGoToDashboard);
		return holder;
	}

	@Override
	protected int getItemLayout() {
		return R.layout.parked_mode_list_item;
	}

	private class GoDashboard implements OnClickListener {
		
		private Activity activity;
		private ParkedModeStatus parkedModeStatus;
		
		GoDashboard(Activity activity, ParkedModeStatus parkedModeStatus) {
			this.activity = activity;
			this.parkedModeStatus = parkedModeStatus;
		}

		@Override
		public void onClick(View arg0) {
			Intent intent = new Intent(activity.getBaseContext(), ActivityParkedModeVehicleHome.class);
			intent.putExtra(ActivityParkedModeVehicleHome.VEHICLE, parkedModeStatus);
			activity.startActivity(intent);
		}
	}
}
