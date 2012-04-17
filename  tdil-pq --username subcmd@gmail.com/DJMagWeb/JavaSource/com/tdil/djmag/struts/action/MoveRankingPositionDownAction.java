package com.tdil.djmag.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tdil.djmag.struts.forms.NoteForm;
import com.tdil.djmag.struts.forms.RankingNoteForm;
import com.tdil.struts.actions.AbstractAction;

public class MoveRankingPositionDownAction extends AbstractAction {

	@Override
	protected ActionForward basicExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		int index = Integer.valueOf((String)request.getParameter("index"));
		RankingNoteForm noteForm = (RankingNoteForm) form;
		noteForm.movePositionDown(index);
		return mapping.findForward("continue");
	}

}
