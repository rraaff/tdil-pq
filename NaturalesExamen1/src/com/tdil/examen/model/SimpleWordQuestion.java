package com.tdil.examen.model;

import java.util.HashSet;
import java.util.Set;

/**
 * Este tipo de preguntas muestra una sola caja de texto, y amite varias opciones correctas
 * */
public class SimpleWordQuestion extends Question {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4549311197912089733L;
	private Set<String> posibleCorrectAnswers;
	
	public SimpleWordQuestion(String questionText, Set<String> posibleCorrectAnswers) {
		super(questionText);
		setPosibleCorrectAnswers(posibleCorrectAnswers);
	}

	public Set<String> getPosibleCorrectAnswers() {
		return posibleCorrectAnswers;
	}

	public void setPosibleCorrectAnswers(Set<String> posibleCorrectAnswers) {
		this.posibleCorrectAnswers = new HashSet<String>();
		for (String s : posibleCorrectAnswers) {
			this.posibleCorrectAnswers.add(s.toLowerCase());
		}
	}
	
	public boolean correctResult(String result) {
		String test = result.trim();
		test = test.toLowerCase();
		return this.posibleCorrectAnswers.equals(test);
	}

}
