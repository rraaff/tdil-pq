package com.tdil.thalamus.android.car.parkedmode;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.tdil.lojack.rl.R;
import com.tdil.thalamus.android.LoJackWithProductMenuActivity;
import com.tdil.thalamus.android.MenuLogic;
import com.tdil.thalamus.android.header.logic.HeaderLogic;
import com.tdil.thalamus.android.rest.model.parkedmode.ParkedModeStatus;


/**
 * Esta pagina maneja el listado de alarmas
 * @author mgodoy
 *
 */
public class ActivityParkedModeVehicleHome extends LoJackWithProductMenuActivity {

	public static final String VEHICLE = "VEHICLE";
	private ParkedModeStatus parkedModeStatus;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locar_pm_home);
        HeaderLogic.installTabLogic(this);
		Bundle extras = getIntent().getExtras();
		parkedModeStatus = (ParkedModeStatus)extras.getSerializable(VEHICLE);
		
		((TextView)findViewById(R.id.pmDetailDomainTextView)).setText(parkedModeStatus.getDomain());
		TextView status = (TextView)findViewById(R.id.pmDetailStatusTextView);
		ToggleButton statusSwitch = (ToggleButton)findViewById(R.id.pmStatusSwitch);
		if ("ON".equals(parkedModeStatus.getStatus())) {
			status.setText("ACTIVADO");
			status.setTextColor(getResources().getColor(R.color.lst_itm_on));
			statusSwitch.setChecked(true);
		} else {
			status.setText("DESACTIVADO");
			status.setTextColor(getResources().getColor(R.color.lst_itm_off));
			statusSwitch.setChecked(false);
		}
		
		((View)findViewById(R.id.pmVehicleGoConfig)).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				ParkedModeRestFacade.goParkedModeConfigActivity(ActivityParkedModeVehicleHome.this, parkedModeStatus);
			}
		});
		
		((View)findViewById(R.id.pmVehicleGoHistory)).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				ParkedModeRestFacade.goParkedModeHistoryActivity(ActivityParkedModeVehicleHome.this, parkedModeStatus);
			}
		});
		
		((View)findViewById(R.id.pmVehicleHomeBack)).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		
    }
    
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		getMenuInflater().inflate(R.menu.activity_login, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		return MenuLogic.handleOnOptionsItemSelected(this, item);
	}
	
}