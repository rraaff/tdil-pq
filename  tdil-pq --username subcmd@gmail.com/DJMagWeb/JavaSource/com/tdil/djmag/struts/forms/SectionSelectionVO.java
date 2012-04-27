package com.tdil.djmag.struts.forms;

import java.io.Serializable;

import com.tdil.djmag.model.Section;

public class SectionSelectionVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5454047414505641108L;
	private Section section;
	private boolean selected;
	
	public SectionSelectionVO(Section section) {
		this.setSection(section);
		this.setSelected(false);
	}
	
	public Section getSection() {
		return section;
	}
	public void setSection(Section section) {
		this.section = section;
	}
	public boolean isSelected() {
		return selected;
	}
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
}
