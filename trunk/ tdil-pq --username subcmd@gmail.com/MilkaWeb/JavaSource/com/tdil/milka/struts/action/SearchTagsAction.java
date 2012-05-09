package com.tdil.milka.struts.action;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tdil.ibatis.TransactionProvider;
import com.tdil.milka.daomanager.DAOManager;
import com.tdil.milka.model.Tag;
import com.tdil.milka.model.TagExample;
import com.tdil.struts.TransactionalActionWithResult;
import com.tdil.struts.actions.AjaxAction;

public class SearchTagsAction extends AjaxAction {


	@SuppressWarnings("unchecked")
	@Override
	protected ActionForward basicExecute(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		final String term = request.getParameter("term");
		List<String> result = (List<String>)TransactionProvider.executeInTransactionWithResult(new TransactionalActionWithResult() {
			public Object executeInTransaction() throws SQLException {
				TagExample tagExample = new TagExample();
				tagExample.createCriteria().andDescriptionLike(term + "%");
				List<String> result = new ArrayList<String>();
				for (Tag t : DAOManager.getTagDAO().selectTagByExample(tagExample)) {
					result.add(t.getDescription());
				}
				return result;
			}
		});
		JSONArray json = JSONArray.fromObject(result);
		response.getOutputStream().write(json.toString().getBytes());
		return null;
	}
}
