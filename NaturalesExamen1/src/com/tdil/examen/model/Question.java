package com.tdil.examen.model;

import java.io.Serializable;

public class Question implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6492908659198760597L;
	private String questionText;
	
	public Question(String questionText) {
		super();
		this.questionText = questionText;
	}

	public String getQuestionText() {
		return questionText;
	}

	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}

	public void random() {
		// TODO Auto-generated method stub
		
	}
	
}
