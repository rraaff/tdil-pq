package com.tdil.transaction;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.sql.SQLException;

import com.tdil.ibatis.TransactionProvider;
import com.tdil.struts.TransactionalActionWithResult;

public class TransactionInvocationHandler implements InvocationHandler {

	private static ThreadLocal<Boolean> transactionInProgress = new ThreadLocal<Boolean>();

	private Object proxied;

	public TransactionInvocationHandler(Object proxied) {
		this.proxied = proxied;
	}

	@Override
	public Object invoke(Object proxy, final Method method, final Object[] args)
			throws Throwable {
		Boolean transactionAlreadyStarted = transactionInProgress.get();
		if (transactionAlreadyStarted != null
				&& transactionAlreadyStarted.booleanValue()) {
			return method.invoke(proxied, args);
		} else {
			final Method m = proxied.getClass().getMethod(method.getName(),
					method.getParameterTypes());
			boolean isTransaccional = m
					.isAnnotationPresent(Transactional.class);
			if (isTransaccional) {
				try {
					transactionInProgress.set(Boolean.TRUE);
					return TransactionProvider
							.executeInTransactionWithResult(new TransactionalActionWithResult() {
								@Override
								public Object executeInTransaction()
										throws SQLException {
									try {
										return method.invoke(proxied, args);
									} catch (Exception e) {
										throw new RuntimeException(e);
									}
								}
							});
				} finally {
					transactionInProgress.remove();
				}
			} else {
				return method.invoke(proxied, args);
			}
		}
	}
}