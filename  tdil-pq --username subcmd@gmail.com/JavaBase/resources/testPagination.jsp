<%@ taglib uri="http://displaytag.sf.net" prefix="display" %>
<html>
<head>
<style>
th.sorted a,th.sortable a {
	background-position: right;
	display: block;
	width: 100%;
}

th.sortable a {
	background-image: url(img/displaytag/arrow_off.png);
}

th.order1 a {
	background-image: url(img/displaytag/arrow_down.png);
}

th.order2 a {
	background-image: url(img/displaytag/arrow_up.png);
}

tr.odd {
	background-color: #fff
}

tr.tableRowEven,tr.even {
	background-color: #fea
}
th.sorted {
	background-color: orange;
}
</style>
</head>
<% 
java.util.ArrayList source=  new java.util.ArrayList(10);
for (int i= 0; i < 20; i++) {
source.add(new com.tdil.test.Email("pepe", "pepe@pepe"));
source.add(new com.tdil.test.Email("pipi", "pipi@pepe"));
}
source.add(new com.tdil.test.Email("pepe", "pepe@pepe"));
com.tdil.struts.pagination.PaginatedListImpl paginated = new com.tdil.struts.pagination.PaginatedListImpl(source, request, 10);
request.setAttribute( "test",  paginated); %>
<body>
<display:table name="test" sort="external" pagesize="10">
  <display:column title="name" sortable="true" sortName="name" headerClass="sortable" property="name"></display:column>
  <display:column title="address" sortable="true" sortName="address" headerClass="sortable" property="address"></display:column>
</display:table>
</body>
</html>