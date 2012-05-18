package com.tdil.djmag.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tdil.djmag.struts.forms.RankingPositionForm;
import com.tdil.struts.actions.AbstractAction;

public class MoveImageInRankingPositionUpAction extends AbstractAction {

	@Override
	protected ActionForward basicExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		int index = Integer.valueOf((String)request.getParameter("index"));
		RankingPositionForm noteForm = (RankingPositionForm) form;
		noteForm.moveImageUp(index);
		return mapping.findForward("continue");
	}

}
