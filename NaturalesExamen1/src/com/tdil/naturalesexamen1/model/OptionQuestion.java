package com.tdil.naturalesexamen1.model;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class OptionQuestion extends Question {

	private List<String> options;
	
	private List<String> correctResult;

	public OptionQuestion(String questionText, List<String> options,
			List<String> correctResult) {
		super(questionText);
		this.options = options;
		this.correctResult = correctResult;
	}
	
	@Override
	public void random() {
		long seed = System.nanoTime();
		Collections.shuffle(options, new Random(seed));
	}

	public List<String> getOptions() {
		return options;
	}

	public void setOptions(List<String> options) {
		this.options = options;
	}

	public List<String> getCorrectResult() {
		return correctResult;
	}

	public void setCorrectResult(List<String> correctResult) {
		this.correctResult = correctResult;
	}

	public String getOpt1() {
		if (options.size() < 1) {
			return "";
		}
		return options.get(0);
	}
	
	public String getOpt2() {
		if (options.size() < 2) {
			return "";
		}
		return options.get(1);
	}
	
	public String getOpt3() {
		if (options.size() < 3) {
			return "";
		}
		return options.get(2);
	}
	
	public String getOpt4() {
		if (options.size() < 4) {
			return "";
		}
		return options.get(3);
	}
	
	public String getOpt5() {
		if (options.size() < 5) {
			return "";
		}
		return options.get(4);
	}

	public boolean correctResult(Set<String> result) {
		Set<String> answer = new HashSet<String>();
		answer.addAll(this.getCorrectResult());
		return answer.containsAll(result) && result.containsAll(answer);
	}
}
