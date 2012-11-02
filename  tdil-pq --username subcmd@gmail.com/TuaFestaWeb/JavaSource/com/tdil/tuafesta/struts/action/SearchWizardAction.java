package com.tdil.tuafesta.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tdil.ibatis.TransactionProvider;
import com.tdil.struts.actions.SearchTransactionalAction;
import com.tdil.tuafesta.struts.forms.OrganizeWizardForm;
import com.tdil.tuafesta.struts.forms.SellSearchResultForm;

public class SearchWizardAction extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		final OrganizeWizardForm organizeWizardForm = (OrganizeWizardForm)request.getSession().getAttribute("OrganizeWizardForm");
		TransactionProvider.executeInTransaction(new SearchTransactionalAction(organizeWizardForm));
		SellSearchResultForm form2 = (SellSearchResultForm)form;
		form2.setSearchResult(organizeWizardForm.getSearchResult());
		return mapping.findForward("continue");
	}
}
