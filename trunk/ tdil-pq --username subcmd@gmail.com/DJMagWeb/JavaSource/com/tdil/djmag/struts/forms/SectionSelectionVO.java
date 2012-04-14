package com.tdil.djmag.struts.forms;

import com.tdil.djmag.model.Section;

public class SectionSelectionVO {

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
