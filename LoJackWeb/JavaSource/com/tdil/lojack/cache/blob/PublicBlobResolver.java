package com.tdil.lojack.cache.blob;

import java.io.File;
import java.sql.SQLException;
import java.util.List;

import com.tdil.cache.blob.BlobResolver;
import com.tdil.lojack.dao.BlobDataDAO;
import com.tdil.lojack.daomanager.DAOManager;
import com.tdil.lojack.model.BlobData;
import com.tdil.lojack.model.BlobDataExample;
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
