package com.tdil.thalamus.android.home;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.tdil.lojack.rl.R;
import com.tdil.thalamus.android.ActivityRestClientObserver;
import com.tdil.thalamus.android.gui.AbstractListAdapter;
import com.tdil.thalamus.android.rest.client.HttpMethod;
import com.tdil.thalamus.android.rest.client.IRestClientObserver;
import com.tdil.thalamus.android.rest.client.IRestClientTask;
import com.tdil.thalamus.android.rest.client.RESTClientTaskOpt;
import com.tdil.thalamus.android.rest.client.RESTConstants;
import com.tdil.thalamus.android.rest.client.RestParams;
import com.tdil.thalamus.android.rest.model.LightAgenda;
import com.tdil.thalamus.android.rest.model.RESTResponse;

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
		if (iter.monday()) {
			holder.lun.setTextColor(activity.getResources().getColor(R.color.actionBarRed));
		} else {
			holder.lun.setTextColor(activity.getResources().getColor(R.color.gray));
		}
		if (iter.tuesday()) {
			holder.mar.setTextColor(activity.getResources().getColor(R.color.actionBarRed));
		} else {
			holder.mar.setTextColor(activity.getResources().getColor(R.color.gray));
		}
		if (iter.wednesday()) {
			holder.mie.setTextColor(activity.getResources().getColor(R.color.actionBarRed));
		} else {
			holder.mie.setTextColor(activity.getResources().getColor(R.color.gray));
		}
		if (iter.thursday()) {
			holder.jue.setTextColor(activity.getResources().getColor(R.color.actionBarRed));
		} else {
			holder.jue.setTextColor(activity.getResources().getColor(R.color.gray));
		}
		if (iter.friday()) {
			holder.vie.setTextColor(activity.getResources().getColor(R.color.actionBarRed));
		} else {
			holder.vie.setTextColor(activity.getResources().getColor(R.color.gray));
		}
		if (iter.saturday()) {
			holder.sab.setTextColor(activity.getResources().getColor(R.color.actionBarRed));
		} else {
			holder.sab.setTextColor(activity.getResources().getColor(R.color.gray));
		}
		if (iter.sunday()) {
			holder.dom.setTextColor(activity.getResources().getColor(R.color.actionBarRed));
		} else {
			holder.dom.setTextColor(activity.getResources().getColor(R.color.gray));
		}
		holder.statusSwitch.setChecked(iter.isActive());
		holder.statusSwitch.setOnCheckedChangeListener(new ToggleAgendaActivationListener(activity, holder.statusSwitch, iter));
		holder.goDetail.setOnClickListener(new GoDashboard(activity, iter));
	}

	@Override
	protected LightAgendaViewHolder createViewHolder(View vi) {
		LightAgendaViewHolder holder = new LightAgendaViewHolder();
		holder.description = (TextView)vi.findViewById(R.id.lightAgendaDescriptionTextView);
		holder.lun = (TextView)vi.findViewById(R.id.luTextView);
		holder.mar = (TextView)vi.findViewById(R.id.maTextView);
		holder.mie = (TextView)vi.findViewById(R.id.miTextView);
		holder.jue = (TextView)vi.findViewById(R.id.juTextView);
		holder.vie = (TextView)vi.findViewById(R.id.viTextView);
		holder.sab = (TextView)vi.findViewById(R.id.saTextView);
		holder.dom = (TextView)vi.findViewById(R.id.doTextView);
		holder.statusSwitch = (ToggleButton)vi.findViewById(R.id.alarmStatusSwitch);
		holder.goDetail = (View)vi.findViewById(R.id.lightAgendaGoToDashboard);
		return holder;
	}

	@Override
	protected int getItemLayout() {
		return R.layout.light_agenda_list_item;
	}
	

	private static class ToggleAgendaActivationListener implements OnCheckedChangeListener {
		private Activity activity;
		private ToggleButton toggleButton;
		private LightAgenda agenda;
		private boolean ignore = false;
		
		public ToggleAgendaActivationListener(Activity activity, ToggleButton toggleButton, LightAgenda agenda) {
			this.activity = activity;
			this.toggleButton = toggleButton;
			this.agenda = agenda;
		}

		@Override
		public void onCheckedChanged(CompoundButton buttonView,
			boolean isChecked) {
			if (!ignore) {
				ignore = true;
				if (agenda.isActive()) {
					new RESTClientTaskOpt<RESTResponse>(activity, HttpMethod.POST, getPostDeactivate(activity, toggleButton, this), 
							RESTConstants.POST_DELETE_LIGHT_AGENDA, 
			    			new RestParams(RESTConstants.ID_ENTIDAD, this.agenda.getIdEntidad())
								.put(RESTConstants.ID_LUZ, this.agenda.getIdLuz())
								.put(RESTConstants.ID_AGENDA, this.agenda.getIdAgenda())
			    			,null,RESTResponse.class)
			    				.execute((Void) null);
				} else {
					new RESTClientTaskOpt<RESTResponse>(activity, HttpMethod.POST, getPostActivate(activity, toggleButton, this), 
							RESTConstants.POST_ACTIVATE_LIGHT_AGENDA, 
			    			new RestParams(RESTConstants.ID_ENTIDAD, this.agenda.getIdEntidad())
								.put(RESTConstants.ID_LUZ, this.agenda.getIdLuz())
								.put(RESTConstants.ID_AGENDA, this.agenda.getIdAgenda())
			    				,null,RESTResponse.class)
			    				.execute((Void) null); 
				}
			}
		}
	}
	
	public static IRestClientObserver getPostDeactivate(final Activity activity, final ToggleButton toggleButton, final ToggleAgendaActivationListener listener) {
    	return new ActivityRestClientObserver(activity) { 
			@Override
			public void sucess(IRestClientTask restClientTask) {
				RESTResponse response = ((RESTClientTaskOpt<RESTResponse>)restClientTask).getCastedResult();
				if (response.getOk()) {
					toggleButton.setChecked(false);
					listener.agenda.setActive(false);
				} else {
					toggleButton.setChecked(true);
					listener.agenda.setActive(true);
					error(restClientTask);
				}
				listener.ignore = false;
			}
			@Override
			public void error(IRestClientTask task) {
				super.error(task);
				toggleButton.setChecked(true);
				listener.agenda.setActive(true);
				listener.ignore = false;
			}
		};
    }
	
	public static IRestClientObserver getPostActivate(final Activity activity, final ToggleButton toggleButton, final ToggleAgendaActivationListener listener) {
    	return new ActivityRestClientObserver(activity) { 
			@Override
			public void sucess(IRestClientTask restClientTask) {
				RESTResponse response = ((RESTClientTaskOpt<RESTResponse>)restClientTask).getCastedResult();
				if (response.getOk()) {
					toggleButton.setChecked(true);
					listener.agenda.setActive(true);
				} else {
					toggleButton.setChecked(false);
					listener.agenda.setActive(false);
					error(restClientTask);
				}
				listener.ignore = false;
			}
			@Override
			public void error(IRestClientTask task) {
				super.error(task);
				toggleButton.setChecked(false);
				listener.agenda.setActive(false);
				listener.ignore = false;
			}
		};
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
			Intent intent = new Intent(activity.getBaseContext(), ActivityHomeLightAgendaEdit.class);
			intent.putExtra(ActivityHomeLightAgendaEdit.ID_ENTIDAD, alarmAgenda.getIdEntidad());
			intent.putExtra(ActivityHomeLightAgendaEdit.ID_LUZ, alarmAgenda.getIdLuz());
			intent.putExtra(ActivityHomeLightAgendaEdit.AGENDA, alarmAgenda);
			activity.startActivity(intent);
			activity.finish();
		}
	}
}
