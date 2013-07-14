package com.tdil.subsystem.generic;

import java.sql.SQLException;

import org.apache.struts.action.ActionForm;

import com.tdil.struts.TransactionalAction;
import com.tdil.struts.TransactionalActionWithResult;
import com.tdil.struts.TransactionalActionWithValue;
import com.tdil.struts.ValidationException;
import com.tdil.transaction.Transactional;
import com.tdil.transaction.TransactionalProxyGenerator;

public class GenericTransactionExecutionService implements IGenericTransactionExecutionService {
	
	private static IGenericTransactionExecutionService instance = (IGenericTransactionExecutionService) TransactionalProxyGenerator.getNewProxy(new GenericTransactionExecutionService(), IGenericTransactionExecutionService.class);

	private GenericTransactionExecutionService() {

	}
	
	public static IGenericTransactionExecutionService getInstance() {
		return instance;
	}

	/* (non-Javadoc)
	 * @see com.tdil.subsystem.generic.IGenericTransactionExecutionService#execute(com.tdil.struts.TransactionalAction)
	 */
	@Override
	@Transactional
	public void execute(TransactionalAction action) throws SQLException, ValidationException {
		action.executeInTransaction();
	}
	
	/* (non-Javadoc)
	 * @see com.tdil.subsystem.generic.IGenericTransactionExecutionService#execute(com.tdil.struts.TransactionalActionWithResult)
	 */
	@Override
	@Transactional
	public <T> T execute(TransactionalActionWithResult<T> action) throws SQLException, ValidationException {
		return action.executeInTransaction();
	}
	
	/* (non-Javadoc)
	 * @see com.tdil.subsystem.generic.IGenericTransactionExecutionService#executeInTransaction(com.tdil.struts.TransactionalActionWithValue, org.apache.struts.action.ActionForm)
	 */
	@Override
	@Transactional
	public <T> T  executeInTransaction(TransactionalActionWithValue<T> transactionalAction, ActionForm form) throws SQLException, ValidationException {
		return transactionalAction.executeInTransaction(form);
	}
}
