package com.tdil.thalamus.android.car.parkedmode;

import java.util.ArrayList;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.tdil.lojack.rl.R;
import com.tdil.thalamus.android.LoJackWithProductMenuActivity;
import com.tdil.thalamus.android.header.logic.HeaderLogic;
import com.tdil.thalamus.android.rest.model.parkedmode.ParkedModeHistoryLogBean;
import com.tdil.thalamus.android.rest.model.parkedmode.ParkedModeHistoryLogBeanCollection;


/**
 * Esta pagina maneja el listado de alarmas
 * @author mgodoy
 *
 */
public class ActivityParkedModeVehicleHistory extends LoJackWithProductMenuActivity {

	public static final String HISTORY_LIST = "HISTORY_LIST";
	private ParkedModeHistoryLogBeanCollection parkedModeHistoryLogBeanCollection;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locar_pm_history_list);
        HeaderLogic.installTabLogic(this);
		Bundle extras = getIntent().getExtras();
		parkedModeHistoryLogBeanCollection = (ParkedModeHistoryLogBeanCollection)extras.getSerializable(HISTORY_LIST);
		final ListView list = (ListView) findViewById(R.id.pmHistoryList);
		
		ArrayList<ParkedModeHistoryLogBean> CustomListViewValuesArr = new ArrayList<ParkedModeHistoryLogBean>(parkedModeHistoryLogBeanCollection.getList());
		Resources res = getResources();
		ParkedModeHistoryListAdapter adapter = new ParkedModeHistoryListAdapter(this,
				CustomListViewValuesArr, res);
		list.setAdapter(adapter);
		
		((View)findViewById(R.id.pmHistoryBack)).setOnClickListener(new View.OnClickListener() {
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