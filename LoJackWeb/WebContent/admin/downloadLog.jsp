<%@ include file="includes/checklogin.jsp" %><%--
--%><%@ include file="includes/checklogaccess.jspf" %><%--
--%><%
java.io.FileInputStream inputStream = null;
		try {
			String fileName = com.tdil.utils.SystemPropertyCache.getTempPath() + "/lojacklogs/server.log";
			
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition", "attachment; filename=".concat(fileName));
			
			inputStream = new java.io.FileInputStream(new java.io.File(fileName));
			int readByte;
			
			ServletOutputStream os = response.getOutputStream();
			org.apache.commons.io.IOUtils.copy(inputStream, os);
		} catch (Exception e) {
			throw e;
		} finally {
			if (inputStream != null) {
				inputStream.close();
			}
		}
%>