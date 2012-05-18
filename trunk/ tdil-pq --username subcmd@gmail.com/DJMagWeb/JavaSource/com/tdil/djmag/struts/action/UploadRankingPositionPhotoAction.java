package com.tdil.djmag.struts.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;

import com.tdil.djmag.struts.forms.RankingPositionForm;
import com.tdil.struts.actions.AjaxFileUploadAction;
import com.tdil.struts.forms.AjaxUploadHandlerForm;

public class UploadRankingPositionPhotoAction extends AjaxFileUploadAction {

	@Override
	protected AjaxUploadHandlerForm getUploadForm(HttpServletRequest request, ActionForm form) {
		RankingPositionForm rankingPositionForm =  (RankingPositionForm)request.getSession().getAttribute("RankingPositionForm");
		rankingPositionForm.setOperation("gal");
		return rankingPositionForm;
	}

}
