package com.tdil.lojack.vlu;

import java.io.Closeable;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpConnectionManager;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import com.tdil.ibatis.TransactionProvider;
import com.tdil.log4j.LoggerProvider;
import com.tdil.lojack.daomanager.DAOManager;
import com.tdil.lojack.model.ImportType;
import com.tdil.lojack.model.VLUData;
import com.tdil.lojack.model.VLUDataExample;
import com.tdil.lojack.model.VLUImport;
import com.tdil.lojack.model.VLUImportErrorExample;
import com.tdil.lojack.model.VLUImportExample;
import com.tdil.struts.TransactionalAction;
import com.tdil.struts.TransactionalActionWithResult;
import com.tdil.struts.ValidationException;
import com.tdil.thalamus.client.core.CommunicationException;
import com.tdil.thalamus.client.core.HttpStatusException;
import com.tdil.thalamus.client.core.InvalidResponseException;
import com.tdil.thalamus.client.core.UnauthorizedException;

public class VLUUtils {

	private static final class InsertVLUImport implements TransactionalAction {
		private String filename;
		private int type;
		
		public InsertVLUImport(String filename,int type) {
			super();
			this.filename = filename;
			this.type = type;
		}
		public void executeInTransaction() throws SQLException {
			VLUImport vluImport = new VLUImport();
			vluImport.setFilename(this.filename);
			vluImport.setStatus("PENDING");
			vluImport.setProcessed(0);
			vluImport.setErrors(0);
			vluImport.setImporttype(this.type);
			DAOManager.getVLUImportDAO().insertVLUImport(vluImport);
		}
	}
	private static final class DeleteVLUImport implements TransactionalAction {
		private String id;
		
		public DeleteVLUImport(String id) {
			super();
			this.id = id;
		}
		public void executeInTransaction() throws SQLException {
			Integer id = Integer.parseInt(this.id);
			VLUImportErrorExample vluImportErrorExample = new VLUImportErrorExample();
			vluImportErrorExample.createCriteria().andIdvluimportEqualTo(id);
			VLUDataExample vluDataExample = new VLUDataExample();
			vluDataExample.createCriteria().andIdvluimportEqualTo(id);
			DAOManager.getVLUImportErrorDAO().deleteVLUImportErrorByExample(vluImportErrorExample);
			DAOManager.getVLUDataDAO().deleteVLUDataByExample(vluDataExample);
			DAOManager.getVLUImportDAO().deleteVLUImportByPrimaryKey(id);
		}
	}
	private static final class GetVLUImport implements TransactionalActionWithResult<List<VLUImport>> {
		public GetVLUImport() {
			super();
		}
		public List<VLUImport> executeInTransaction() throws SQLException {
			VLUImportExample importExample = new VLUImportExample();
			importExample.setOrderByClause("id");
			return DAOManager.getVLUImportDAO().selectVLUImportByExample(importExample);
		}
	}
	
	private static final class HasVLUMessage implements TransactionalActionWithResult<Boolean> {
		private String dni;
		public HasVLUMessage(String dni) {
			super();
			this.dni = dni;
		}
		public Boolean executeInTransaction() throws SQLException {
			VLUDataExample vluDataExample = new VLUDataExample();
			vluDataExample.createCriteria().andDniEqualTo(this.dni);
			Integer count = DAOManager.getVLUDataDAO().countVLUDataByExample(vluDataExample);
			return count > 0;
		}
	}
	
	private static final class CountVLUMessages implements TransactionalActionWithResult<Integer> {
		private String dni;
		public CountVLUMessages(String dni) {
			super();
			this.dni = dni;
		}
		public Integer executeInTransaction() throws SQLException {
			VLUDataExample vluDataExample = new VLUDataExample();
			vluDataExample.createCriteria().andDniEqualTo(this.dni);
			Integer count = DAOManager.getVLUDataDAO().countVLUDataByExample(vluDataExample);
			return count;
		}
	}
	
	private static final class GetVLUData implements TransactionalActionWithResult<List<VLUData>> {
		private String dni;
		public GetVLUData(String dni) {
			super();
			this.dni = dni;
		}
		public List<VLUData> executeInTransaction() throws SQLException {
			VLUDataExample vluDataExample = new VLUDataExample();
			vluDataExample.createCriteria().andDniEqualTo(this.dni);
			return DAOManager.getVLUDataDAO().selectVLUDataByExample(vluDataExample);
		}
	}
	
	private static final class GetVLUImportPending implements TransactionalActionWithResult<VLUImport> {
		private String fileName;
		public GetVLUImportPending(String fileName) {
			super();
			this.fileName = fileName;
		}
		public VLUImport executeInTransaction() throws SQLException {
			VLUImportExample example = new VLUImportExample();
			example.createCriteria().andStatusEqualTo("PENDING").andFilenameEqualTo(fileName).andImporttypeEqualTo(ImportType.VLU_DELETE_REPAIRED.getType());
			example.setOrderByClause("id");
			List<VLUImport> result = DAOManager.getVLUImportDAO().selectVLUImportByExample(example);
			if (result.size() > 0) {
				return result.get(0);
			} else {
				return null;
			}
		}
	}

	public static boolean registerNewImport(String fileName) {
		try {
			TransactionProvider.executeInTransaction(new InsertVLUImport(fileName, ImportType.VLU_COMPLETE.getType()));
			return true;
		} catch (SQLException e) {
			getLog().error(e.getMessage(), e);
			return false;
		} catch (ValidationException e) {
			getLog().error(e.getMessage(), e);
			return false;
		}
	}
	
	public static boolean registerNewImportRepairedDomains(String fileName) {
		try {
			TransactionProvider.executeInTransaction(new InsertVLUImport(fileName, ImportType.VLU_DELETE_REPAIRED.getType()));
			
			VLUImport importVlu =  TransactionProvider.executeInTransactionWithResult(new GetVLUImportPending(fileName));
			new DeleteRepairedDomainsThread(importVlu).start();
			return true;
		} catch (SQLException e) {
			getLog().error(e.getMessage(), e);
			return false;
		} catch (ValidationException e) {
			getLog().error(e.getMessage(), e);
			return false;
		}
	}
	
	public static void deleteVLUImport(String id) {
		try {
			TransactionProvider.executeInTransaction(new DeleteVLUImport(id));
		} catch (SQLException e) {
			getLog().error(e.getMessage(), e);
		} catch (ValidationException e) {
			getLog().error(e.getMessage(), e);
		}
	}
	
	public static boolean hasVLUMessage(String domain) {
		try {
			return TransactionProvider.executeInTransactionWithResult(new HasVLUMessage(domain));
		} catch (SQLException e) {
			getLog().error(e.getMessage(), e);
			return false;
		}
	}
	
	public static Integer countVLUMessages(String domain) {
		try {
			return TransactionProvider.executeInTransactionWithResult(new CountVLUMessages(domain));
		} catch (SQLException e) {
			getLog().error(e.getMessage(), e);
			return 0;
		}
	}
	public static List<VLUData> getVLUData(String domain) {
		try {
			return TransactionProvider.executeInTransactionWithResult(new GetVLUData(domain));
		} catch (SQLException e) {
			getLog().error(e.getMessage(), e);
			return new ArrayList<VLUData>();
		}
	}
	
	
	public static List<VLUImport> getImports() {
		try {
			return TransactionProvider.executeInTransactionWithResult(new GetVLUImport());
		} catch (SQLException e) {
			getLog().error(e.getMessage(), e);
			return new ArrayList<VLUImport>();
		}
	}
	
	public static void download(String url, String fileName) throws HttpStatusException, InvalidResponseException, CommunicationException, UnauthorizedException {
		long start = System.currentTimeMillis();
		if (getLog().isDebugEnabled()) {
			getLog().debug("Download: " + url + " as " + fileName);
		}
		HttpClient client = new HttpClient();
		configureTimeout(client);
		GetMethod httpMethod = new GetMethod(url);
		try {
			client.executeMethod(httpMethod);
			int statusCode = httpMethod.getStatusCode();
			if (getLog().isDebugEnabled()) {
				getLog().debug("Remote result status: " + statusCode);
			}
			InputStream inputStream = null;
			FileOutputStream outputStream = null;
			try {
				inputStream = httpMethod.getResponseBodyAsStream();
				outputStream = new FileOutputStream(fileName);
				IOUtils.copy(inputStream, outputStream);
				if (getLog().isDebugEnabled()) {
					getLog().debug("URL: " + url + " downloaded as " + fileName);
				}
			} finally {
				closeStream(inputStream);
				closeStream(outputStream);
			}
		} catch (HttpException e) {
			throw new CommunicationException(e);
		} catch (IOException e) {
			throw new CommunicationException(e);
		} finally {
			if (getLog().isInfoEnabled()) {
				long end = System.currentTimeMillis();
				getLog().info("Execute: " + url + " took " + (end - start) + " millis");
			}
		}
	}
	
	private static void closeStream(Closeable inputStream) {
		try {
			if (inputStream != null) {
				inputStream.close();
			}
		} catch (IOException e) {
			getLog().error(e.getMessage(), e);
		}
	}

	private static void configureTimeout(HttpClient client) {
		HttpConnectionManager connectionManager = client.getHttpConnectionManager();
		connectionManager.getParams().setConnectionTimeout(60000);
		connectionManager.getParams().setSoTimeout(60000);
		if (VLUImportThread.getPROXY() != null) {
			client.getHostConfiguration().setProxy(VLUImportThread.getPROXY().getServer(), VLUImportThread.getPROXY().getPort());
		}
	}
	
	private static Logger getLog() {
		return LoggerProvider.getLogger(VLUUtils.class);
	}
	
}
