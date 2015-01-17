package com.tdil.peugeotservice.android.car;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import android.os.Bundle;
import android.widget.TextView;

import com.tdil.peugeotservice.R;
import com.tdil.peugeotservice.android.PeugeotActivity;
import com.tdil.peugeotservice.android.rest.model.prevent.PositionHistoryBean;


/**
 * Esta pagina maneja el listado de alarmas
 * @author mgodoy
 *
 */
public class ActivityCarsPathHistoryDetail extends PeugeotActivity {

	public static final String PositionHistoryBean = "PositionHistoryBean";
	private PositionHistoryBean positionHistoryBean;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_locar_path_detail);
        customizeActionBar(true);
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