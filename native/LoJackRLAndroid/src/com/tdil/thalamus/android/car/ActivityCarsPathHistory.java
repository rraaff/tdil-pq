package com.tdil.thalamus.android.car;

import java.util.ArrayList;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;

import com.tdil.lojack.rl.R;
import com.tdil.thalamus.android.LoJackWithProductMenuActivity;
import com.tdil.thalamus.android.header.logic.HeaderLogic;
import com.tdil.thalamus.android.rest.model.prevent.PositionHistoryBean;
import com.tdil.thalamus.android.rest.model.prevent.PositionHistoryCollection;


/**
 * Esta pagina maneja el listado de alarmas
 * @author mgodoy
 *
 */
public class ActivityCarsPathHistory extends LoJackWithProductMenuActivity {

	public static final String Position_History_Collection = "positionHistoryCollection";
	private PositionHistoryCollection positionHistoryCollection;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locar_path);
        HeaderLogic.installTabLogic(this);
		Bundle extras = getIntent().getExtras();
		positionHistoryCollection = (PositionHistoryCollection)extras.getSerializable(Position_History_Collection);
		final ListView list = (ListView) findViewById(R.id.pathList);
		
		ArrayList<PositionHistoryBean> CustomListViewValuesArr = new ArrayList<PositionHistoryBean>(positionHistoryCollection.getList());
		Resources res = getResources();
		PathListAdapter adapter = new PathListAdapter(this,
				CustomListViewValuesArr, res);
		list.setAdapter(adapter);
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