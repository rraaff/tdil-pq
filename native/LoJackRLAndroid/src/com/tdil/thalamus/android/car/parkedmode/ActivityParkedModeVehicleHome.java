package com.tdil.thalamus.android.car.parkedmode;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.tdil.lojack.rl.R;
import com.tdil.thalamus.android.LoJackWithProductMenuActivity;
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
		
		((View)findViewById(R.id.pmVehicleHomeBack)).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
	
}