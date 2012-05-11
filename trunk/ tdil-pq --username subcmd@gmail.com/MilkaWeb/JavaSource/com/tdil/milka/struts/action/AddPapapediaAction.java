package com.tdil.milka.struts.action;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tdil.log4j.LoggerProvider;
import com.tdil.milka.model.WallType;
import com.tdil.milka.struts.forms.WallWrittingForm;
import com.tdil.struts.actions.SaveAction;
import com.tdil.struts.forms.AbstractForm;

public class AddPapapediaAction extends SaveAction {

	@Override
	public ActionForward validateAndSave(AbstractForm form, HttpServletRequest request, ActionMapping mapping) {
		final WallWrittingForm wallWrittingForm = (WallWrittingForm)form;
		wallWrittingForm.setWallType(WallType.PAPAPEDIA);
		return super.validateAndSave(wallWrittingForm, request, mapping);
	}
	
	private static Logger getLog() {
		return LoggerProvider.getLogger(AddPapapediaAction.class);
	}
}
