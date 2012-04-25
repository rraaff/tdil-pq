package com.tdil.cache.blob;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import com.tdil.ibatis.TransactionProvider;
import com.tdil.log4j.LoggerProvider;
import com.tdil.struts.TransactionalAction;
import com.tdil.struts.ValidationException;
import com.tdil.users.Role;
import com.tdil.users.User;

public abstract class BlobResolver {
	
	private Role[] permissions;
	
	public BlobResolver(Role[] permissions) {
		super();
		this.permissions = permissions;
	}

	public boolean canAccess(User user) {
		return Role.isValid(user, permissions);
	}

	public void load(final int id, final int version, final File local) throws SQLException, ValidationException {
		TransactionProvider.executeInTransaction(new TransactionalAction() {
			public void executeInTransaction() throws SQLException, ValidationException {
				byte data[] = loadInTransaction(id, version, local);
				FileOutputStream fout = null;
				try {
					fout = new FileOutputStream(local);
					IOUtils.write(data, fout);
				} catch (Exception e) {
					getLog().error(e.getMessage(), e);
				} finally {
					if (fout != null) {
						try {
							fout.close();
						} catch (IOException e) {
							getLog().error(e.getMessage(), e);
						}
					}
				}
				
			}
		});
	}
	
	protected abstract byte[] loadInTransaction(int id, int version, File local) throws SQLException;

	private static Logger getLog() {
		return LoggerProvider.getLogger(BlobResolver.class);
	}
}
