package com.tdil.naturalesexamen1.model;

public class TrueFalseQuestion extends Question {

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
