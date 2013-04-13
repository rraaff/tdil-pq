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
import com.tdil.tuafesta.dao.CategoryDAO;
import com.tdil.tuafesta.daomanager.DAOManager;
import com.tdil.tuafesta.model.Category;
import com.tdil.tuafesta.model.CategoryExample;
import com.tdil.tuafesta.utils.TreeCategoryUtils;

/**
 * Esta clase se encarga de buscar categorias de productos y de servicios, y las retorna para la 
 * busqueda de te ayudamos a organizar tu fiesta
 * */
public class SearchProductAjaxAction extends AjaxAction {

	@Override
	protected ActionForward basicExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		final String term = request.getParameter("name");
		@SuppressWarnings("unchecked")
		List<Map<String, String>> result = (List<Map<String, String>>)TransactionProvider.executeInTransactionWithResult(new TransactionalActionWithResult() {
			public Object executeInTransaction() throws SQLException {
				CategoryDAO categoryDAO = DAOManager.getCategoryDAO();
				CategoryExample categoryExample = new CategoryExample();
				categoryExample.createCriteria().andNameLike("%" + term + "%").andDeletedEqualTo(0);
				categoryExample.setOrderByClause("name");
				List<Category> result = categoryDAO.selectCategoryByExample(categoryExample);
				List<Map<String, String>> jsonResult = new ArrayList<Map<String,String>>();
				for (Category category : result) {
					Map<String, String> rowResult = new HashMap<String, String>();
					rowResult.put("id", String.valueOf(category.getId()));
					rowResult.put("name", TreeCategoryUtils.getCategoryPath(category.getId()));
					jsonResult.add(rowResult);
				}
				return jsonResult;
			}
		});
		JSONArray json = JSONArray.fromObject(result);
		response.getOutputStream().write(json.toString().getBytes());
		return null;
	}
	
}
