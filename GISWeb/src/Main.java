import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;

import com.tdil.lojack.gis.model.Alarm;
import com.tdil.lojack.gis.model.ChangeLog;


public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Alarm alarm = new Alarm();
		alarm.setId("1");
		alarm.setDescription("Casa");
		alarm.setOn(true);
		alarm.setLastChangeAction("ACTIVAR");
		alarm.setLastChangeDate("2012");
		alarm.setLastChangeHour("20:30");
		alarm.setLastChangeUser("MARCELA");
		Alarm alarm1 = new Alarm();
		alarm1.setId("2");
		alarm1.setDescription("Estudio");
		alarm1.setOn(false);
		alarm1.setLastChangeAction("ACTIVAR");
		alarm1.setLastChangeDate("2012");
		alarm1.setLastChangeHour("20:30");
		alarm1.setLastChangeUser("MARCELA");
		
		List<Alarm> alarms = new ArrayList<Alarm>();
		alarms.add(alarm);
		alarms.add(alarm1);
		
		JSONArray jsonObject = JSONArray.fromObject( alarms ); 
		System.out.println(jsonObject.toString(2));
	}

}
