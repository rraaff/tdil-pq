package com.tdil.tuafesta.model.valueobjects;

import com.tdil.tuafesta.model.WallWritting;

public class WallWrittingValueObject extends WallWritting {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7600127592238024343L;

	private String authorName;

	public String getAuthorName() {
		return authorName;
	}

	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
}
