<% if (publicHomeBean.hasRanking()) { %>
<div id="ranking" style="border: 1px Solid Black;">
Title<%= publicHomeBean.getRanking().getDescription()%>
<table>
<% int positionIndex = 1;
for (String position : publicHomeBean.getReducedRanking()) { %>
<tr>
	<td><%=positionIndex++ %>.</td>
	<td><%=position %></td>
</tr>
<% } %>
<tr><td colspan="2" align="right">Ver los 100</td></tr>
</table>
</div>
<% } else { %>
	No hay ranking
<% } %>