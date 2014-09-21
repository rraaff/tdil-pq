package com.tdil.thalamus.android.car.parkedmode;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.tdil.lojack.rl.R;
import com.tdil.thalamus.android.LoJackWithProductMenuActivity;
import com.tdil.thalamus.android.header.logic.HeaderLogic;
import com.tdil.thalamus.android.rest.model.parkedmode.ParkedModeConfiguration;
import com.tdil.thalamus.android.rest.model.parkedmode.ParkedModeStatus;


/**
 * Esta pagina maneja el listado de alarmas
 * @author mgodoy
 *
 */
public class ActivityParkedModeVehicleDetail extends LoJackWithProductMenuActivity {

	public static final String CONFIG = "CONFIG";
	private ParkedModeConfiguration parkedModeConfiguration;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locar_pm_detail);
        HeaderLogic.installTabLogic(this);
		Bundle extras = getIntent().getExtras();
		parkedModeConfiguration = (ParkedModeConfiguration)extras.getSerializable(CONFIG);
		
		((TextView)findViewById(R.id.pmDetailDomainTextView)).setText(parkedModeConfiguration.getDomain());
		TextView status = (TextView)findViewById(R.id.pmDetailStatusTextView);
		ToggleButton statusSwitch = (ToggleButton)findViewById(R.id.pmStatusSwitch);
		if ("ON".equals(parkedModeConfiguration.getStatus())) {
			status.setText("ACTIVADO");
			status.setTextColor(getResources().getColor(R.color.lst_itm_on));
			statusSwitch.setChecked(true);
		} else {
			status.setText("DESACTIVADO");
			status.setTextColor(getResources().getColor(R.color.lst_itm_off));
			statusSwitch.setChecked(false);
		}
		
		((TextView)findViewById(R.id.pmStatusPhoneTextView)).setText(parkedModeConfiguration.getPhone());
		RadioButton always = ((RadioButton)findViewById(R.id.pmStatusAlwaysRadioButton));
		RadioButton withoutContact = ((RadioButton)findViewById(R.id.pmStatusWithoutContactRadioButton));
		if (parkedModeConfiguration.isAlways()) {
			always.setChecked(true);
			withoutContact.setChecked(false);
		} else {
			always.setChecked(false);
			withoutContact.setChecked(true);
		}
		
		((View)findViewById(R.id.pmVehicleDetailBack)).setOnClickListener(new View.OnClickListener() {
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