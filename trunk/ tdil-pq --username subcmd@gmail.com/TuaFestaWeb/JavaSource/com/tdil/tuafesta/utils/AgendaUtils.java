package com.tdil.tuafesta.utils;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.tdil.ibatis.TransactionProvider;
import com.tdil.struts.TransactionalActionWithResult;
import com.tdil.struts.resources.ApplicationResources;
import com.tdil.tuafesta.daomanager.DAOManager;
import com.tdil.tuafesta.model.ProfesionalAgenda;
import com.tdil.tuafesta.model.ProfesionalAgendaExample;
import com.tdil.tuafesta.model.valueobjects.AgendaDate;
import com.tdil.tuafesta.struts.forms.ProfesionalProfileForm;

public class AgendaUtils {

	public static String getCurrentMonth(ProfesionalProfileForm profesionalProfileForm) {
		return ApplicationResources.getMessage("MONTH_" + profesionalProfileForm.getCalendar().get(Calendar.MONTH));
	}
	
	public static String getCurrentYear(ProfesionalProfileForm profesionalProfileForm) {
		return String.valueOf(profesionalProfileForm.getCalendar().get(Calendar.YEAR));
	}
	
	public static List<List<AgendaDate>> getAgenda(final ProfesionalProfileForm profesionalProfileForm) {
		Calendar start = profesionalProfileForm.getCalendar();
		Calendar end = Calendar.getInstance();
		end.setTime(start.getTime());
		end.add(Calendar.MONTH, 1);
		end.add(Calendar.MILLISECOND, -1);
		final Date startDate = start.getTime();
		final Date endDate = end.getTime();
		Set<String> busyDates = new HashSet<String>();
		try {
			List<ProfesionalAgenda> busy = (List<ProfesionalAgenda>)TransactionProvider.executeInTransactionWithResult(new TransactionalActionWithResult() {
				public Object executeInTransaction() throws SQLException {
					ProfesionalAgendaExample example = new ProfesionalAgendaExample();
					example.createCriteria().andDateBetween(startDate, endDate).andIdProfesionalEqualTo(profesionalProfileForm.getProfesional().getId());
					return DAOManager.getProfesionalAgendaDAO().selectProfesionalAgendaByExample(example);
				}
			});
			for (ProfesionalAgenda busyDay : busy) {
				busyDates.add(String.valueOf(busyDay.getDate().getDate()));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List<List<AgendaDate>> result = new ArrayList<List<AgendaDate>>();
		int maxDay = profesionalProfileForm.getCalendar().getActualMaximum(Calendar.DAY_OF_MONTH);
		ArrayList<AgendaDate> tempResult = new ArrayList<AgendaDate>();
		for (int i = 2; i < profesionalProfileForm.getCalendar().get(Calendar.DAY_OF_WEEK); i++) {
			tempResult.add(new AgendaDate("-", false));
		}
		for (int i = 0; i < maxDay; i++) {
			AgendaDate ad = new AgendaDate(String.valueOf(i + 1), true);
			if (busyDates.contains(ad.getDate())) {
				ad.setBusy(true);
			}
			tempResult.add(ad);
		}
		for (int i = 0; i < tempResult.size(); ) {
			ArrayList<AgendaDate> row = new ArrayList<AgendaDate>();
			for (int j = 0; j < 7; j++) {
				if (i < tempResult.size()) {
					row.add(tempResult.get(i));
				} else {
					row.add(new AgendaDate("-", false));
				}
				i++;
			}
			result.add(row);
		}
		return result;
	}
	
	public static void main(String[] args) {
		System.out.println(Calendar.getInstance().get(Calendar.DAY_OF_WEEK));
	}
}
