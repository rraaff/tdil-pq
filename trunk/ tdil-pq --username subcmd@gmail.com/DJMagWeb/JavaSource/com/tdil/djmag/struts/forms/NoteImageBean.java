package com.tdil.djmag.struts.forms;

import com.tdil.djmag.model.NoteImage;
import com.tdil.struts.forms.UploadData;

/**
 * Esta clase se usara para el abm de imagenes a una nota
 * @author mgodoy
 *
 */
public class NoteImageBean {

	private int id;
	private UploadData uploadData;
	
	public NoteImageBean(UploadData uploadData, int id) {
		setUploadData(uploadData);
		setId(id);
	}
	
	public NoteImageBean(NoteImage noteImage, int id) {
		setUploadData(new UploadData(noteImage.getFilename(), noteImage.getNoteimage()));
		setId(id);
	}

	public UploadData getUploadData() {
		return uploadData;
	}

	public void setUploadData(UploadData uploadData) {
		this.uploadData = uploadData;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
