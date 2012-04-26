package com.tdil.djmag.model.valueobjects;

import java.util.ArrayList;
import java.util.List;

import com.tdil.djmag.model.Note;
import com.tdil.djmag.model.NoteImage;

public class NoteValueObject extends Note {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2395773035303132580L;

	private List<NoteImage> noteImages = new ArrayList<NoteImage>();

	public List<NoteImage> getNoteImages() {
		return noteImages;
	}

	public void setNoteImages(List<NoteImage> noteImages) {
		this.noteImages = noteImages;
	}

	public void addNoteImage(NoteImage ni) {
		getNoteImages().add(ni);
	}
	
	
}
