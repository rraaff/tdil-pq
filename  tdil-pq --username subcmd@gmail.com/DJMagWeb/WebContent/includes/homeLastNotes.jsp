<%@page import="com.tdil.djmag.model.Note"%>
<% if (publicHomeBean.hasLastNotes()) { %>
<div id="lastnotes" style="border: 1px Solid Black;">
Last notes
<table>
<% 
for (Note note : publicHomeBean.getReducedLastNotes()) { %>
<tr>
	<td><%=note.getTitle() %></td>
</tr>
<% } %>
</table>
</div>
<% } else { %>
	No hay last notes
<% } %>