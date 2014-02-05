package com.tdil.ljpeugeot.struts.action;

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

import com.tdil.ljpeugeot.model.City;
import com.tdil.ljpeugeot.services.PeugeotService;
import com.tdil.struts.actions.AjaxAction;

public class SearchCitiesAjaxAction extends AjaxAction {

	@Override
	protected ActionForward basicExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		final String stateIdSt = request.getParameter("stateId");
		int stateId = Integer.parseInt(stateIdSt);

		Collection<City> states = PeugeotService.getCities(stateId);
		List<Map<String, String>> result = new ArrayList<Map<String,String>>();
		for (City bean : states) {
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
