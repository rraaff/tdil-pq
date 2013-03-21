import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.tdil.lojack.gis.model.Alarm;
import com.tdil.lojack.gis.model.AlarmAgenda;
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
		
		ChangeLog changeLog = new ChangeLog();
		changeLog.setAction("ACTIVAR");
		changeLog.setDate("2012");
		changeLog.setHour("20:30");
		changeLog.setUser("MARCELA");
		
		List<ChangeLog> log = new ArrayList<ChangeLog>();
		log.add(changeLog);
		changeLog = new ChangeLog();
		changeLog.setAction("DESATIVAR");
		changeLog.setDate("2012");
		changeLog.setHour("22:30");
		changeLog.setUser("MARCOS");
		log.add(changeLog);
		
		jsonObject = JSONArray.fromObject( log ); 
		System.out.println(jsonObject.toString(2));
		
		System.out.println("*************************");
		
		AlarmAgenda alarmAgenda = new AlarmAgenda();
		alarmAgenda.setId("1");
		alarmAgenda.setDescription("Dias habiles");
		alarmAgenda.setType("BUSINESS_DAYS");
		alarmAgenda.setActivateTime("10:00:00");
		alarmAgenda.setDeactivateTime("17:30:00");
		
		List<AlarmAgenda> agendas = new ArrayList<AlarmAgenda>();
		agendas.add(alarmAgenda);
		
		alarmAgenda = new AlarmAgenda();
		alarmAgenda.setId("2");
		alarmAgenda.setDescription("Findes");
		alarmAgenda.setType("WEEKENDS");
		alarmAgenda.setActivateTime("20:00:00");
		alarmAgenda.setDeactivateTime("05:00:00");
		
		agendas.add(alarmAgenda);
		jsonObject = JSONArray.fromObject( agendas ); 
		System.out.println(jsonObject.toString(2));
		
		System.out.println("*************************");
		
		JSONObject jsonObject1 = JSONObject.fromObject(alarmAgenda);
		System.out.println(jsonObject1.toString(2));
	}

}
