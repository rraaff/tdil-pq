<% 
com.tdil.lojack.model.SystemUser su = (com.tdil.lojack.model.SystemUser)session.getAttribute("sysuser");
if (su == null) { %>
<jsp:forward page="./login.jsp"></jsp:forward>
<% return;
} %>