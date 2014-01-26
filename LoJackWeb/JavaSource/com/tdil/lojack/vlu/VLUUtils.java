package com.tdil.lojack.vlu;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.tdil.ibatis.TransactionProvider;
import com.tdil.log4j.LoggerProvider;
import com.tdil.lojack.daomanager.DAOManager;
import com.tdil.lojack.model.VLUData;
import com.tdil.lojack.model.VLUDataExample;
import com.tdil.lojack.model.VLUImport;
import com.tdil.lojack.model.VLUImportErrorExample;
import com.tdil.lojack.model.VLUImportExample;
import com.tdil.struts.TransactionalAction;
import com.tdil.struts.TransactionalActionWithResult;
import com.tdil.struts.ValidationException;

public class VLUUtils {

	private static final class InsertVLUImport implements TransactionalAction {
		private String filename;
		
		public InsertVLUImport(String filename) {
			super();
			this.filename = filename;
		}
		public void executeInTransaction() throws SQLException {
			VLUImport vluImport = new VLUImport();
			vluImport.setFilename(this.filename);
			vluImport.setStatus("PENDING");
			vluImport.setProcessed(0);
			vluImport.setErrors(0);
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
			vluDataExample.createCriteria().andDniEqualTo(this.dni).andMessageIsNotNull();
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
			vluDataExample.createCriteria().andDniEqualTo(this.dni).andMessageIsNotNull();
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

	public static boolean registerNewImport(String fileName) {
		try {
			TransactionProvider.executeInTransaction(new InsertVLUImport(fileName));
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
	
	private static Logger getLog() {
		return LoggerProvider.getLogger(VLUUtils.class);
	}
	
}
