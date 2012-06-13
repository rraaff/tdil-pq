package com.tdil.tuafesta.utils;

import java.sql.SQLException;

import com.tdil.struts.forms.UploadData;
import com.tdil.tuafesta.cache.blob.BlobDataType;
import com.tdil.tuafesta.dao.BlobDataDAO;
import com.tdil.tuafesta.daomanager.DAOManager;
import com.tdil.tuafesta.model.BlobData;

public class BlobHelper {

	public static void deleteBlob(Integer id) throws SQLException {
		BlobDataDAO blobDataDAO = DAOManager.getBlobDataDAO();
		blobDataDAO.deleteBlobDataByPrimaryKey(id);
	}

	public static boolean shouldInsertBlob(UploadData frontCover2) {
		return frontCover2 != null && frontCover2.isModified();
	}

	public static boolean shouldDeleteBlob(UploadData frontCover2) {
		return frontCover2 == null || frontCover2.isModified();
	}

	public static int insertBlob(UploadData uploadData) throws SQLException {
		BlobDataDAO blobDataDAO = DAOManager.getBlobDataDAO();
		BlobData blobData = new BlobData();
		blobData.setDatatype(BlobDataType.PUBLIC);
		blobData.setFilename(uploadData.getFileName());
		blobData.setContent(uploadData.getData());
		blobData.setDeleted(0);
		return blobDataDAO.insertBlobData(blobData);
	}

}
