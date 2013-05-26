package com.tdil.lojack.struts.forms;

import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import com.tdil.log4j.LoggerProvider;
import com.tdil.lojack.daomanager.DAOManager;
import com.tdil.lojack.gis.LoJackServicesConnector;
import com.tdil.lojack.gis.model.Alarm;
import com.tdil.lojack.model.AlarmConf;
import com.tdil.lojack.model.AlarmConfExample;
import com.tdil.lojack.utils.WebsiteUser;
import com.tdil.struts.TransactionalActionWithResult;

public class AlarmsForm extends ActionForm {

	private static final long serialVersionUID = 7670249948557986182L;
	
	private static final org.apache.log4j.Logger LOG = LoggerProvider.getLogger(AlarmsForm.class);

	private WebsiteUser user;
	private Collection<Alarm> alarms;
	
	private static final class GetAlarmConf implements TransactionalActionWithResult {
		private int userId;
		public GetAlarmConf(int userId) {
			super();
			this.userId = userId;
		}
		public Object executeInTransaction() throws SQLException {
			AlarmConfExample example = new AlarmConfExample();
			example.createCriteria().andIdwebsiteuserEqualTo(userId);
			return DAOManager.getAlarmConfDAO().selectAlarmConfByExample(example);
		}
	}
	
		
	@Override
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		super.reset(mapping, request);
	}
	
	public void reset() {
	}

	public void initWith(WebsiteUser user) {
		setUser(user);
		setAlarms(LoJackServicesConnector.getAlarms(user));
		try {
			List<AlarmConf> alarmConf = (List<AlarmConf>)new GetAlarmConf(user.getModelUser().getId()).executeInTransaction();
			for (Alarm alarm : getAlarms()) {
				enhance(alarm, alarmConf);
			}
		} catch (SQLException e) {
			LOG.error(e.getMessage(), e);
		}
		
	}

	private void enhance(Alarm alarm, List<AlarmConf> alarmConf) {
		for (AlarmConf ac : alarmConf) {
			if (ac.getIdentidad().equals(alarm.getIdEntidad())) {
				alarm.setEmailnotification(ac.getEmailnotification() == null ? false : (ac.getEmailnotification().equals(1)));
				alarm.setDescription(ac.getDescription());
				return;
			}
		}
	}

	public Collection<Alarm> getAlarms() {
		return alarms;
	}


	public void setAlarms(Collection<Alarm> alarms) {
		this.alarms = alarms;
	}

	public WebsiteUser getUser() {
		return user;
	}

	public void setUser(WebsiteUser user) {
		this.user = user;
	}
	
}
