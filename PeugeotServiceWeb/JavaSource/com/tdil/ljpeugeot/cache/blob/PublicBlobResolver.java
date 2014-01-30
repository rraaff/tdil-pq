package com.tdil.ljpeugeot.cache.blob;

import java.io.File;
import java.sql.SQLException;
import java.util.List;

import com.tdil.cache.blob.BlobResolver;
import com.tdil.ljpeugeot.dao.BlobDataDAO;
import com.tdil.ljpeugeot.daomanager.DAOManager;
import com.tdil.ljpeugeot.model.BlobData;
import com.tdil.ljpeugeot.model.BlobDataExample;
import com.tdil.users.Role;

public class PublicBlobResolver extends BlobResolver {

	public PublicBlobResolver(Role[] permissions) {
		super(permissions);
	}

	@Override
	protected byte[] loadInTransaction(int id, String type, int version, File local) throws SQLException {
		BlobDataDAO blobDataDAO = DAOManager.getBlobDataDAO();
		BlobDataExample blobDataExample = new BlobDataExample();
		blobDataExample.createCriteria().andIdEqualTo(id).andDatatypeEqualTo(type);
		List<BlobData> result = blobDataDAO.selectBlobDataByExampleWithBLOBs(blobDataExample);
		if (result.isEmpty()) {
			return null;
		} else {
			return result.get(0).getContent();
		}
	}
}
