<%com.tdil.ljpeugeot.model.SystemUser su = (com.tdil.ljpeugeot.model.SystemUser)session.getAttribute("sysuser");
if (su == null) {%><%--
--%><jsp:forward page="./login.jsp"></jsp:forward><%--
--%><% return;
} %>