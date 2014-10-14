package com.tdil.thalamus.android.car.parkedmode;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.tdil.lojack.rl.R;
import com.tdil.thalamus.android.gui.AbstractListAdapter;
import com.tdil.thalamus.android.rest.model.parkedmode.ParkedModeHistoryLogBean;

public class ParkedModeHistoryListAdapter extends AbstractListAdapter<ParkedModeHistoryLogBean, ParkedModeHistoryViewHolder> {

	private Activity activity;
	
	public ParkedModeHistoryListAdapter(Activity a,
			ArrayList<ParkedModeHistoryLogBean> d, Resources resLocal) {
		super(a, d, resLocal);
		this.activity = a;
	}

	@Override
	protected void fillViewHolder(ParkedModeHistoryViewHolder holder,
			ParkedModeHistoryLogBean iter) {
		if (1 == iter.getIdaccion()) {
			holder.pmHistoryActionTextView.setText("ACTIVACION");
			holder.pmHistoryViewInMap.setVisibility(View.GONE);
		}
		if (2 == iter.getIdaccion()) {
			holder.pmHistoryActionTextView.setText("DESACTIVACION");
			holder.pmHistoryViewInMap.setVisibility(View.GONE);
		}
		if (3 == iter.getIdaccion()) {
			holder.pmHistoryActionTextView.setText("MOVIMIENTO INDEBIDO");
			holder.pmHistoryActionTextView.setTextColor(activity.getResources().getColor(R.color.lst_itm_off));
			holder.pmHistoryViewInMap.setVisibility(View.VISIBLE);
			holder.pmHistoryViewInMap.setOnClickListener(new GoToMap(activity, iter));
		}
		holder.pmHistoryDateTextView.setText(iter.getFecha());
	}

	@Override
	protected ParkedModeHistoryViewHolder createViewHolder(View vi) {
		ParkedModeHistoryViewHolder holder = new ParkedModeHistoryViewHolder();
		holder.pmHistoryActionTextView = (TextView)vi.findViewById(R.id.pmHistoryActionTextView);
		holder.pmHistoryDateTextView = (TextView)vi.findViewById(R.id.pmHistoryDateTextView);
		holder.pmHistoryViewInMap = (View)vi.findViewById(R.id.pmHistoryViewInMap);
		return holder;
	}

	@Override
	protected int getItemLayout() {
		return R.layout.parked_mode_history_list_item;
	}

	private class GoToMap implements OnClickListener {
		
		private Activity activity;
		private ParkedModeHistoryLogBean parkedModeStatus;
		
		GoToMap(Activity activity, ParkedModeHistoryLogBean parkedModeStatus) {
			this.activity = activity;
			this.parkedModeStatus = parkedModeStatus;
		}

		@Override
		public void onClick(View arg0) {
			int googlePlayServicesAvailable = GooglePlayServicesUtil.isGooglePlayServicesAvailable(activity);
			if (googlePlayServicesAvailable != ConnectionResult.SUCCESS) {
	            // Handle the case here
	        	new AlertDialog.Builder(activity)
	            .setIcon(R.drawable.ic_launcher)
	            .setTitle("Lojack")
	            .setMessage("Para acceder a esta funcionalidad debe tener instalado Google Play (code: " + String.valueOf(googlePlayServicesAvailable) + ")")
	            .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
	                    public void onClick(DialogInterface dialog, int whichButton) {
	                        /* User clicked OK so do some stuff */
	                        //Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://search?q=pname:lojack.real.life"));
	                    }
	            })
	            .show();
	        } else {
	        	Intent intent = new Intent(activity.getBaseContext(), ActivityParkedModeMapView.class);
	        	intent.putExtra(ActivityParkedModeMapView.POSITION, parkedModeStatus);
	        	activity.startActivity(intent);
	        }
		}
	}
}
