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
import com.tdil.tuafesta.model.ProfesionalService;
import com.tdil.tuafesta.model.ProfesionalServiceExample;
import com.tdil.tuafesta.utils.ServiceCategoryUtils;

public class SearchServiceAction extends AjaxAction {

	@Override
	protected ActionForward basicExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.setContentType("Content-Type: text/html;charset=ISO-8859-1");
	    response.setHeader("cache-control", "no-cache");
		final String term = request.getParameter("name");
		@SuppressWarnings("unchecked")
		List<String> result = (List<String>)TransactionProvider.executeInTransactionWithResult(new TransactionalActionWithResult() {
			public Object executeInTransaction() throws SQLException {
				// TODO esto deberia hacer la busqueda tambien por los nombres de las categorias
				ProfesionalServiceExample profesionalServiceExample = new ProfesionalServiceExample();
				profesionalServiceExample.createCriteria().andNameLike("%" + term + "%");
				List<Map<String, String>> result = new ArrayList<Map<String,String>>();
				for (ProfesionalService t : DAOManager.getProfesionalServiceDAO().selectProfesionalServiceByExample(profesionalServiceExample)) {
					Map<String, String> row = new HashMap<String, String>();
					row.put("id", t.getId().toString());
					row.put("name", t.getName());
					row.put("path", ServiceCategoryUtils.getCategoryPath(t.getIdCategory()));
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
