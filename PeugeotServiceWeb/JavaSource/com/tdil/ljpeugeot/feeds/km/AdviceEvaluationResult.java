package com.tdil.ljpeugeot.feeds.km;

import java.util.Date;

public class AdviceEvaluationResult {

	private boolean needsAdvice;
	private Date date;
	private int km;
	
	public AdviceEvaluationResult(boolean needsAdvice, Date date, int km) {
		super();
		this.needsAdvice = needsAdvice;
		this.date = date;
		this.km = km;
	}

	public boolean needsAdvice() {
		return needsAdvice;
	}
	public Date getDate() {
		return date;
	}
	public int getKm() {
		return km;
	}
}
