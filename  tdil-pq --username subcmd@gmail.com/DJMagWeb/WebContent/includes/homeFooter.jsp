<%@page import="com.tdil.djmag.model.Footer"%>
<%@page import="com.tdil.djmag.web.beans.PublicHomeBean"%>
<% if (publicHomeBean.hasFooter()) { 
	Footer footer = publicHomeBean.getFooter();
	%>
	<%=footer.getHtmlcontent() %></h1>
<% } else { %>
	No tienefooter
<% } %>
</div>