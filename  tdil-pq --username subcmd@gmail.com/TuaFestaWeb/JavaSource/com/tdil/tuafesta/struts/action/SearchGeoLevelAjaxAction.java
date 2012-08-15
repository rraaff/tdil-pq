package com.tdil.tuafesta.struts.action;

import java.sql.SQLException;
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

import com.tdil.ibatis.TransactionProvider;
import com.tdil.struts.TransactionalActionWithResult;
import com.tdil.struts.actions.AjaxAction;
import com.tdil.tuafesta.daomanager.DAOManager;
import com.tdil.tuafesta.model.valueobjects.GeoLevelValueObject;

public class SearchGeoLevelAjaxAction extends AjaxAction {

	@Override
	protected ActionForward basicExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		final String term = request.getParameter("name");
		@SuppressWarnings("unchecked")
		List<String> result = (List<String>)TransactionProvider.executeInTransactionWithResult(new TransactionalActionWithResult() {
			public Object executeInTransaction() throws SQLException {
				List<GeoLevelValueObject> searchResult = DAOManager.getGeo4DAO().selectGeoLevelsByGeo4(term);
				
				List<Map<String, String>> result = new ArrayList<Map<String,String>>();
				for (GeoLevelValueObject geo : searchResult) {
					Map<String, String> row = new HashMap<String, String>();
					row.put("id", String.valueOf(geo.getId()));
					row.put("name", geo.getNombre());
					result.add(row);
				}
				return result;
			}
		});
		JSONArray json = JSONArray.fromObject(result);
		response.getOutputStream().write(json.toString().getBytes());
		return null;
	}
	
}
