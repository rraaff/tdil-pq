package com.tdil.tuafesta.struts.action;

import javax.servlet.http.HttpServletRequest;

import com.tdil.struts.actions.AjaxReportAction;
import com.tdil.struts.forms.TransactionalValidationForm;
import com.tdil.tuafesta.struts.forms.StatisticReportForm;

public class StatisticReportAction extends AjaxReportAction {

	@Override
	protected TransactionalValidationForm getAjaxForm(HttpServletRequest request) {
		StatisticReportForm statisticReportForm = new StatisticReportForm();
		statisticReportForm.setsStatType(Integer.parseInt(request.getParameter("sStatType")));
		statisticReportForm.setsGroup(Integer.parseInt(request.getParameter("sGroup")));
		statisticReportForm.setsFrom(request.getParameter("sFrom"));
		statisticReportForm.setsTo(request.getParameter("sTo"));
		statisticReportForm.setsSum(Integer.parseInt(request.getParameter("sSum")));
		return statisticReportForm;
	}
	
}
