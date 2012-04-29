package com.tdil.milka.cache.blob;

import java.io.File;
import java.sql.SQLException;
import java.util.List;

import com.tdil.cache.blob.BlobResolver;
import com.tdil.milka.dao.BlobDataDAO;
import com.tdil.milka.daomanager.DAOManager;
import com.tdil.milka.model.BlobData;
import com.tdil.milka.model.BlobDataExample;
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
