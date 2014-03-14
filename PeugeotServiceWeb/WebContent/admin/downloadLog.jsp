<%@ include file="includes/checklogin.jsp" %><%--
--%><%@ include file="includes/checkadmin.jspf" %><%--
--%><%
java.io.FileInputStream inputStream = null;
		try {
			String fileName = com.tdil.utils.SystemPropertyCache.getTempPath() + "/ljpeugeotlogs/server.log";
			response.setContentType("application/octet-stream");
			response.setHeader("Content-Disposition", "attachment; filename=".concat(fileName));
			inputStream = new java.io.FileInputStream(new java.io.File(fileName));
			int readByte;
			org.apache.commons.io.IOUtils.copy(inputStream, out);
		} catch (Exception e) {
			throw e;
		} finally {
			if (inputStream != null) {
				inputStream.close();
			}
		}
%>