package com.tdil.cache.blob;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
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
	
	private static final class ExtractBlob implements TransactionalAction {
		private final String type;
		private final int version;
		private final File local;
		private final int id;
		private final BlobResolver blobResolver;

		private ExtractBlob(BlobResolver blobResolver, String type, int version, File local, int id) {
			this.blobResolver = blobResolver;
			this.type = type;
			this.version = version;
			this.local = local;
			this.id = id;
		}

		public void executeInTransaction() throws SQLException, ValidationException {
			byte data[] = this.blobResolver.loadInTransaction(id, type, version, local);
			OutputStream fout = null;
			try {
				fout = new BufferedOutputStream(new FileOutputStream(local));
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
	}

	private Role[] permissions;
	
	protected BlobResolver(Role[] permissions) {
		this.permissions = permissions;
	}

	public boolean canAccess(User user) {
		return Role.isValid(user, permissions);
	}

	public void load(final int id, final String type, final int version, final File local) throws SQLException, ValidationException {
		TransactionProvider.executeInTransaction(new ExtractBlob(this, type, version, local, id));
	}
	
	protected abstract byte[] loadInTransaction(int id, String type, int version, File local) throws SQLException;

	private static Logger getLog() {
		return LoggerProvider.getLogger(BlobResolver.class);
	}
}
