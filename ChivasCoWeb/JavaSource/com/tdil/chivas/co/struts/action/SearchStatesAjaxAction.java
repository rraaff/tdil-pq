package com.tdil.chivas.co.struts.action;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tdil.struts.actions.AjaxAction;
import com.tdil.thalamus.client.facade.ThalamusClientBeanFacade;
import com.tdil.thalamus.client.facade.json.beans.StateBean;

public class SearchStatesAjaxAction extends AjaxAction {

	@Override
	protected ActionForward basicExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		final String country = request.getParameter("countryId");
		int countryId = Integer.parseInt(country);

		Collection<StateBean> states = ThalamusClientBeanFacade.getStates(countryId);
		List<Map<String, String>> result = new ArrayList<Map<String,String>>();
		for (StateBean bean : states) {
			Map<String, String> row = new HashMap<String, String>();
			row.put("id", String.valueOf(bean.getId()));
			row.put("name", bean.getName());
			result.add(row);
		}
		JSONArray json = JSONArray.fromObject(result);
		response.getOutputStream().write(json.toString().getBytes());
		return null;
	}

}
