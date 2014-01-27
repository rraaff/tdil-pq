<%@ include file="includes/checklogin.jsp"%><%--
--%><%@ page import="java.io.*,java.util.*,javax.servlet.*"%><%--
--%><%@ page import="javax.servlet.http.*"%><%--
--%><%@ page import="org.apache.commons.fileupload.*"%><%--
--%><%@ page import="org.apache.commons.fileupload.disk.*"%><%--
--%><%@ page import="org.apache.commons.fileupload.servlet.*"%><%--
--%><%@ page import="org.apache.commons.io.output.*"%><%--
--%><%
	File file;
	int maxFileSize = 50000 * 1024;
	int maxMemSize = 5000 * 1024;
	ServletContext context = pageContext.getServletContext();
	String filePath = com.tdil.utils.SystemPropertyCache.getTempPath() + "/";

	// Verify the content type
	String contentType = request.getContentType();
	if ((contentType.indexOf("multipart/form-data") >= 0)) {

		DiskFileItemFactory factory = new DiskFileItemFactory();
		// maximum size that will be stored in memory
		factory.setSizeThreshold(maxMemSize);
		// Location to save data that is larger than maxMemSize.
		factory.setRepository(new File(com.tdil.utils.SystemPropertyCache.getTempPath()));

		// Create a new file upload handler
		ServletFileUpload upload = new ServletFileUpload(factory);
		// maximum file size to be uploaded.
		upload.setSizeMax(maxFileSize);
		try {
			// Parse the request to get file items.
			List fileItems = upload.parseRequest(request);

			// Process the uploaded file items
			Iterator i = fileItems.iterator();

			while (i.hasNext()) {
				FileItem fi = (FileItem) i.next();
				if (!fi.isFormField()) {
					// Get the uploaded file parameters
					String fieldName = fi.getFieldName();
					String fileName = fi.getName();
					boolean isInMemory = fi.isInMemory();
					long sizeInBytes = fi.getSize();
					// Write the file
					if (fileName.lastIndexOf("\\") >= 0) {
						file = new File(filePath + fileName.substring(fileName.lastIndexOf("\\")));
					} else {
						file = new File(filePath + fileName.substring(fileName.lastIndexOf("\\") + 1));
					}
					fi.write(file);
					com.tdil.lojack.vlu.VLUUtils.registerNewImport(file.getName());
				}
			}
		} catch (Exception ex) {
			com.tdil.log4j.LoggerProvider.getLogger(com.tdil.lojack.vlu.VLUUtils.class).error(ex.getMessage(), ex);
		}
	} 
	response.sendRedirect(request.getContextPath() + "/admin/vlu.jsp");
%>