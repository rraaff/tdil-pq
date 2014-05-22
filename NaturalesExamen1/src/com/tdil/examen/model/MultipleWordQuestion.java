package com.tdil.examen.model;

import java.util.HashSet;
import java.util.Set;

/**
 * Este tipo de preguntas muestra una sola caja de texto, y amite varias opciones correctas
 * */
public class MultipleWordQuestion extends Question {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4549311197912089733L;
	private Set<String> answers;
	
	public MultipleWordQuestion(String questionText, Set<String> posibleCorrectAnswers) {
		super(questionText);
		setAnswers(posibleCorrectAnswers);
	}

	public Set<String> getAnswers() {
		return answers;
	}

	public void setAnswers(Set<String> posibleCorrectAnswers) {
		this.answers = new HashSet<String>();
		for (String s : posibleCorrectAnswers) {
			this.answers.add(s.toLowerCase());
		}
	}
	
	public boolean correctResult(Set<String> result) {
		return answers.containsAll(result) && result.containsAll(answers);
	}

}
