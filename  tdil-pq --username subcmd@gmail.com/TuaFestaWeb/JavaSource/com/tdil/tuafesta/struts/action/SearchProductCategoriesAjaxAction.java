package com.tdil.tuafesta.struts.action;

import java.util.ArrayList;
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
import com.tdil.tuafesta.model.Category;
import com.tdil.tuafesta.model.SellType;
import com.tdil.tuafesta.utils.CategoryUtils;

public class SearchProductCategoriesAjaxAction extends AjaxAction {

	@Override
	protected ActionForward basicExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		final String parent = request.getParameter("parent");
		int parentId = Integer.parseInt(parent);
		@SuppressWarnings("unchecked")
		List<Map<String, String>> result = new ArrayList<Map<String,String>>();
		for (Category pc : CategoryUtils.getCategories(parentId, SellType.PRODUCT)) {
			Map<String, String> row = new HashMap<String, String>();
			row.put("id", String.valueOf(pc.getId()));
			row.put("name", pc.getName());
			result.add(row);
		}
		JSONArray json = JSONArray.fromObject(result);
		response.getOutputStream().write(json.toString().getBytes());
		return null;
	}
	
}
