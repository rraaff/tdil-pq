<%@page import="com.tdil.djmag.model.Footer"%>
<%@page import="com.tdil.djmag.web.beans.PublicHomeBean"%>
<% if (publicHomeBean.hasFooter()) { 
	Footer footer = publicHomeBean.getFooter();
	%>
	<%=footer.getHtmlcontent() %>
<% } else { %>
	No tienefooter
<% } %>