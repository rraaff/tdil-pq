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
import com.tdil.tuafesta.model.ProductCategory;
import com.tdil.tuafesta.model.ProductCategoryExample;
import com.tdil.tuafesta.model.ServiceCategory;
import com.tdil.tuafesta.model.ServiceCategoryExample;

public class SearchCategoryAction extends AjaxAction {

	@Override
	protected ActionForward basicExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.setContentType("Content-Type: text/html;charset=ISO-8859-1");
	    response.setHeader("cache-control", "no-cache");
		final String term = request.getParameter("name");
		final String type = request.getParameter("type");
		@SuppressWarnings("unchecked")
		List<String> result = (List<String>)TransactionProvider.executeInTransactionWithResult(new TransactionalActionWithResult() {
			public Object executeInTransaction() throws SQLException {
				List<Map<String, String>> result = new ArrayList<Map<String,String>>();
				// si es producto
				if ("p".equals(type)) {
					ProductCategoryExample productCategoryExample = new ProductCategoryExample();
					productCategoryExample.createCriteria().andNameLike("%" + term + "%").andDeletedEqualTo(0);
					productCategoryExample.setOrderByClause("name");
					for (ProductCategory t : DAOManager.getProductCategoryDAO().selectProductCategoryByExample(productCategoryExample)) {
						Map<String, String> row = new HashMap<String, String>();
						row.put("id", t.getId().toString());
						row.put("name", t.getName());
						result.add(row);
					}
				} else { // si es servicio
					ServiceCategoryExample serviceCategoryExample = new ServiceCategoryExample();
					serviceCategoryExample.createCriteria().andNameLike("%" + term + "%").andDeletedEqualTo(0);
					serviceCategoryExample.setOrderByClause("name");
					for (ServiceCategory t : DAOManager.getServiceCategoryDAO().selectServiceCategoryByExample(serviceCategoryExample)) {
						Map<String, String> row = new HashMap<String, String>();
						row.put("id", t.getId().toString());
						row.put("name", t.getName());
						result.add(row);
					}		
				}
				return result;
			}
		});
		JSONArray json = JSONArray.fromObject(result);
		response.getOutputStream().write(json.toString().getBytes());
		return null;
	}
	
}
