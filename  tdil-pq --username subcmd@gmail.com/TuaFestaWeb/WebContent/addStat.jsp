<%@page import="com.tdil.tuafesta.stats.StatsManager"%>
<%@page import="com.tdil.tuafesta.model.Statistic"%>
<%@ page info="boHome"%>
<%@ page contentType="text/html; charset=ISO-8859-1" %>
<%
Statistic stat = new Statistic(); 
stat.setDeleted(0);
stat.setObjectid(0);
stat.setTextdata(String.valueOf(System.currentTimeMillis()));
stat.setStattype(1);
StatsManager.addStat(stat);
%>