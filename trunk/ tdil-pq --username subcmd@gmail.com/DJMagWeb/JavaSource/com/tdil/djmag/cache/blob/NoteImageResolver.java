package com.tdil.djmag.cache.blob;

import java.io.File;
import java.sql.SQLException;

import com.tdil.cache.blob.BlobResolver;
import com.tdil.djmag.dao.NoteImageDAO;
import com.tdil.djmag.daomanager.DAOManager;
import com.tdil.djmag.model.NoteImage;
import com.tdil.users.Role;

public class NoteImageResolver extends BlobResolver {

	public NoteImageResolver(Role[] permissions) {
		super(permissions);
	}

	@Override
	protected byte[] loadInTransaction(int id, String type, int version, File local) throws SQLException {
		NoteImageDAO noteImageDAO = DAOManager.getNoteImageDAO();
		NoteImage noteImage = noteImageDAO.selectNoteImageByPrimaryKey(id);
		return noteImage.getNoteimage();
	}
}
