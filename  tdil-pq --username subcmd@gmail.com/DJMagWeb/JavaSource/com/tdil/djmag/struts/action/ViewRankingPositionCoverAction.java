package com.tdil.djmag.struts.action;

import javax.activation.MimetypesFileTypeMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tdil.djmag.struts.forms.RankingPositionForm;
import com.tdil.struts.actions.AbstractAction;
import com.tdil.struts.forms.UploadData;

public class ViewRankingPositionCoverAction extends AbstractAction {

	@Override
	protected ActionForward basicExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		RankingPositionForm noteForm = (RankingPositionForm) form;
		UploadData uploadData = noteForm.getFrontCover();
		String contentType = new MimetypesFileTypeMap().getContentType(uploadData.getFileName());
		response.setContentType(contentType);
		response.getOutputStream().write(uploadData.getData());
		return null;
	}

}
