package com.tdil.thalamus.android.home;

import java.util.ArrayList;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;

import com.google.gson.Gson;
import com.tdil.lojack.rl.R;
import com.tdil.thalamus.android.LoJackWithProductMenuActivity;
import com.tdil.thalamus.android.header.logic.HeaderLogic;
import com.tdil.thalamus.android.rest.client.HttpMethod;
import com.tdil.thalamus.android.rest.client.IRestClientObserver;
import com.tdil.thalamus.android.rest.client.IRestClientTask;
import com.tdil.thalamus.android.rest.client.RESTClientTask;
import com.tdil.thalamus.android.rest.client.RESTConstants;
import com.tdil.thalamus.android.rest.client.RestParams;
import com.tdil.thalamus.android.rest.model.Alarm;
import com.tdil.thalamus.android.rest.model.ChangeLog;
import com.tdil.thalamus.android.rest.model.LogCollection;
import com.tdil.thalamus.android.utils.Messages;


/**
 * Esta pagina maneja el listado de alarmas
 * @author mgodoy
 *
 */
public class ActivityHomeLightLog extends LoJackWithProductMenuActivity {

	private int identidad;
	private int idluz;
	public static final String IDENTIDAD = "IDENTIDAD";
	public static final String IDLUZ = "IDLUZ";
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lohome_light_log);
        HeaderLogic.installTabLogic(this);
		Bundle extras = getIntent().getExtras();
		identidad = extras.getInt(IDENTIDAD);
		idluz = extras.getInt(IDLUZ);
		final ListView list = (ListView) findViewById(R.id.lightLogList);
		new RESTClientTask(this, HttpMethod.GET, new IRestClientObserver() {
			@Override
			public void sucess(IRestClientTask task) {
				Gson gson = new Gson();

				LogCollection col = gson.fromJson(task.getResult(),
						LogCollection.class);
				ArrayList<ChangeLog> CustomListViewValuesArr = new ArrayList<ChangeLog>(col.getLogs());
				Resources res = getResources();
				LightLogListAdapter adapter = new LightLogListAdapter(ActivityHomeLightLog.this,
						CustomListViewValuesArr, res);
				list.setAdapter(adapter);
			}

			@Override
			public void error(IRestClientTask task) {
				Messages.connectionErrorMessage(ActivityHomeLightLog.this);
			}
		}, RESTConstants.LOG_LIGHT, new RestParams(RESTConstants.ID_ENTIDAD, String.valueOf(identidad)).put(RESTConstants.ID_LUZ, String.valueOf(idluz)), null).execute((Void) null);
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