<%@page import="com.tdil.djmag.model.Footer"%>
<%@page import="com.tdil.djmag.web.beans.PublicHomeBean"%>
<% if (publicHomeBean.hasFooter()) { 
	Footer footernote = publicHomeBean.getFooterNote();
	%>
	<%=footernote.getHtmlcontent() %>
<% } else { %>
	No tienefooter
<% } %>