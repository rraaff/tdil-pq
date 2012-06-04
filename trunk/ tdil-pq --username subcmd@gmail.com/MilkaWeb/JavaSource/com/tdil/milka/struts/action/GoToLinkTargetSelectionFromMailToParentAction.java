package com.tdil.milka.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tdil.milka.struts.forms.LinkAnchorForm;
import com.tdil.milka.struts.forms.LinkTargetSelectionForm;
import com.tdil.struts.actions.AbstractAction;

public class GoToLinkTargetSelectionFromMailToParentAction extends AbstractAction {

	@Override
	protected ActionForward basicExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		LinkAnchorForm linkSource = (LinkAnchorForm)request.getSession().getAttribute("MailToParentAdministrationForm");
		LinkTargetSelectionForm linkTargetSelectionForm = (LinkTargetSelectionForm)form;
		linkTargetSelectionForm.clearList();
		linkTargetSelectionForm.setOriginForm(linkSource);
 		return mapping.findForward("continue");
	}

}
