package com.tdil.djmag.web.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;

import com.tdil.djmag.web.beans.PublicHomeBean;

public class SelectCountryServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8930267480565439177L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		service(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		service(req, resp);
	}
	
	@Override
	protected void service(HttpServletRequest arg0, HttpServletResponse arg1) throws ServletException, IOException {
		String iso_code_2 = arg0.getParameter("iso_code_2");
		String id = arg0.getParameter("id");
		arg0.setAttribute("fromSelection", "true");
		initForCountry(arg0, iso_code_2, id);
		arg0.getRequestDispatcher("./index.jsp").forward(arg0, arg1);
	}
	
	public static void initForCountry(HttpServletRequest arg0, String iso_code_2, String id) {
		HttpSession session = arg0.getSession(true);
		PublicHomeBean publicHomeBean = (PublicHomeBean)session.getAttribute(PublicHomeBean.PUBLIC_HOME_BEAN);
		if (publicHomeBean == null) {
			publicHomeBean = new PublicHomeBean();
			session.setAttribute(PublicHomeBean.PUBLIC_HOME_BEAN, publicHomeBean);
		}
		if (!StringUtils.isEmpty(iso_code_2)) {
			publicHomeBean.setCountryByIsoCode2(iso_code_2);
		} else {	
			if (StringUtils.isNumeric(id)) {
				int idcountry = Integer.parseInt(id);
				publicHomeBean.setCountryById(idcountry);
			}
		}
	}

}
