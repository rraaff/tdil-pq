<%@page import="com.tdil.djmag.model.RankingPosition"%>
<% if (publicHomeBean.hasRanking()) { %>
	<div id="top100Home">
		<% int positionIndex = 1;
		for (RankingPosition position : publicHomeBean.getReducedRanking()) { %>
			<div id="position"><%=positionIndex++ %>.</div><div id="rankedHome"><%=position.getPosition()%></div>
		<% } %>
		<div id="linkMore"><a href="./notes/<%=publicHomeBean.getCountry().getIsoCode2()%>/viewRanking.html<%=PublicHomeBean.LIGTH_BOX_PARAMS%>" rel="prettyPhoto[ranking]">ver m&aacute;s</a></div>
	</div>
<% } else { %>
	No hay ranking
<% } %>