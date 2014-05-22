package com.tdil.examen.model;

public class TrueFalseQuestion extends Question {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2076687171554111472L;
	private boolean correctResult;
	
	public TrueFalseQuestion(String questionText, boolean correctResult) {
		super(questionText);
		this.correctResult = correctResult;
	}

	public boolean correctResult() {
		return correctResult;
	}

	public void setCorrectResult(boolean correctResult) {
		this.correctResult = correctResult;
	}

	
}
