package com.tdil.peugeotservice.android.car;

import java.util.ArrayList;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import com.tdil.peugeotservice.R;
import com.tdil.peugeotservice.android.PeugeotActivity;
import com.tdil.peugeotservice.android.rest.model.prevent.PositionHistoryBean;
import com.tdil.peugeotservice.android.rest.model.prevent.PositionHistoryCollection;


/**
 * Esta pagina maneja el listado de alarmas
 * @author mgodoy
 *
 */
public class ActivityCarsPathHistory extends PeugeotActivity {

	public static final String Position_History_Collection = "positionHistoryCollection";
	private PositionHistoryCollection positionHistoryCollection;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locar_path);
        customizeActionBar(true);
		Bundle extras = getIntent().getExtras();
		positionHistoryCollection = (PositionHistoryCollection)extras.getSerializable(Position_History_Collection);
		final ListView list = (ListView) findViewById(R.id.pathList);
		
		ArrayList<PositionHistoryBean> CustomListViewValuesArr = new ArrayList<PositionHistoryBean>(positionHistoryCollection.getList());
		Resources res = getResources();
		PathListAdapter adapter = new PathListAdapter(this,
				CustomListViewValuesArr, res);
		list.setAdapter(adapter);
		
		((View)findViewById(R.id.viewPathInMapButton)).setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent output = new Intent();
//				output.putExtra(ActivityCars.REQUEST_PATH, 0);
				output.putExtra(ActivityCars.REQUEST_PATH_PARAM, positionHistoryCollection);
				setResult(RESULT_OK, output);
				finish();
			}
		});
		
    }
    
}