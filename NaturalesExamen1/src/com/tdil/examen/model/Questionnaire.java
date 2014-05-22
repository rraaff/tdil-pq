package com.tdil.examen.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Questionnaire implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4650460350481925165L;

	private List<Question> questions = new ArrayList<Question>();
	
	private int currentQuestion = -1;
	private int correct = 0;

	public void random() {
		long seed = System.nanoTime();
		Collections.shuffle(questions, new Random(seed));
		for (Question q : this.getQuestions()) {
			q.random();
		}
	}
	
	public void addCorrect() {
		correct = correct + 1;
	}
	
	public void advance() {
		currentQuestion = currentQuestion + 1;
	}
	
	public boolean isFinished() {
		return currentQuestion >= questions.size();
	}
	
	public boolean nextQuestionIsTrueFalse() {
		return questions.get(currentQuestion) instanceof TrueFalseQuestion;
	}
	
	public void addQuestion(Question question) {
		this.getQuestions().add(question);
	}
	
	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	public int getCorrect() {
		return correct;
	}

	public void setCorrect(int correct) {
		this.correct = correct;
	}

	public int getCurrentQuestion() {
		return currentQuestion;
	}

	public void setCurrentQuestion(int currentQuestion) {
		this.currentQuestion = currentQuestion;
	}

	public Question getQuestion() {
		return questions.get(currentQuestion);
	}
}
