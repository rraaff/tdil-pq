package com.tdil.thalamus.android.car;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.tdil.lojack.rl.R;
import com.tdil.thalamus.android.LoJackWithProductMenuActivity;
import com.tdil.thalamus.android.MenuLogic;
import com.tdil.thalamus.android.header.logic.HeaderLogic;
import com.tdil.thalamus.android.rest.client.HttpMethod;
import com.tdil.thalamus.android.rest.client.RESTClientTask;
import com.tdil.thalamus.android.rest.client.RESTConstants;
import com.tdil.thalamus.android.rest.model.prevent.PositionHistoryBean;
import com.tdil.thalamus.android.rest.model.prevent.PositionHistoryCollection;


/**
 * Esta pagina maneja el listado de alarmas
 * @author mgodoy
 *
 */
public class ActivityCarsPathHistoryDetail extends LoJackWithProductMenuActivity {

	public static final String PositionHistoryBean = "PositionHistoryBean";
	private PositionHistoryBean positionHistoryBean;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locar_path_detail);
        customizeActionBar(true);
        HeaderLogic.installTabLogic(this);
		Bundle extras = getIntent().getExtras();
		positionHistoryBean = (PositionHistoryBean)extras.getSerializable(PositionHistoryBean);
		
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddhhmmss");
		SimpleDateFormat dfView = new SimpleDateFormat("dd/MM/yyyy hh:mm");
		try {
			((TextView)findViewById(R.id.positionHistoryDate)).setText(dfView.format(df.parse(positionHistoryBean.getFecha())));
		} catch (ParseException e) {
			((TextView)findViewById(R.id.positionHistoryDate)).setText("-");
		}
		
		((TextView)findViewById(R.id.positionHistoryStreet)).setText(positionHistoryBean.getStreet());
		((TextView)findViewById(R.id.positionHistoryNumber)).setText(positionHistoryBean.getNumber());
		((TextView)findViewById(R.id.positionHistorySpeed)).setText(positionHistoryBean.getSpeed());
		((TextView)findViewById(R.id.positionHistoryDirection)).setText(positionHistoryBean.getDirection());
		((TextView)findViewById(R.id.positionHistoryLongitude)).setText(positionHistoryBean.getLongitude());
		((TextView)findViewById(R.id.positionHistoryLatitude)).setText(positionHistoryBean.getLatitude());
    }
    
}