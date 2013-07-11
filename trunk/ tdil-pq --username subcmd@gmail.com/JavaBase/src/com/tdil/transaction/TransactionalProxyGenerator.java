package com.tdil.transaction;

import java.lang.reflect.Proxy;

public class TransactionalProxyGenerator {

	public static Object getNewProxy(Object proxied, Class<?> interfaze) {
		Object proxy = Proxy.newProxyInstance(
				TransactionInvocationHandler.class.getClassLoader(),
				new Class[] { interfaze }, new TransactionInvocationHandler(
						proxied));
		return proxy;
	}

}
